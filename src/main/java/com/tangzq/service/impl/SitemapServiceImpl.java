package com.tangzq.service.impl;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import com.tangzq.model.Topic;
import com.tangzq.service.CategoryService;
import com.tangzq.service.SitemapService;
import com.tangzq.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class SitemapServiceImpl implements SitemapService {

    @Value("${site.base.url}")
    private String baseURL;

    private static final String TOPIC_VISIT_ACTION="/article/";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TopicService topicService;

    /**
     * 生成sitemp格式为：
     * <url>
     *   <loc>https://dzone.com/refcardz/apache-kafka</loc>
     *   <lastmod>2018-01-16T00:00:00+00:00</lastmod>
     *   <changefreq>monthly</changefreq>
     *   <priority>0.1</priority>
     * </url>
     * @return
     * @throws MalformedURLException
     */
    @Override
    public String createSitemap() throws MalformedURLException {
        WebSitemapGenerator sitemapGenerator = new WebSitemapGenerator(baseURL);
        for (Topic topic : topicService.findAll()) {
            String topicURL=baseURL + TOPIC_VISIT_ACTION + topic.getId();
            WebSitemapUrl url=new WebSitemapUrl.Options(topicURL)
                    .lastMod(topic.getUpdateAt())
                    .priority(0.8)
                    .changeFreq(ChangeFreq.DAILY)
                    .build();
            sitemapGenerator.addUrl(url);
        }
        return String.join("", sitemapGenerator.writeAsStrings());
    }

    /**
     * 生成sitemp到指定目录
     * @return
     * @throws MalformedURLException
     */
    @Override
    public void createSitemapToFolder(String folderPath) throws MalformedURLException {
        //TODO 将sitemap生成到指定目录
    }


    @Override
    public String createCategorySitemap() throws MalformedURLException {
        //TODO 生成栏目sitemap
        return null;
    }
}
