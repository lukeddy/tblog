package com.tangzq.controller;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedOutput;
import com.tangzq.model.Topic;
import com.tangzq.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RSS控制器
 * @author tangzhiqiang
 */
@Controller
public class RssController {


    private static final String RSS_CHANNEL_TYPE="rss_2.0";

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/rss", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String rss(HttpServletRequest request) {

        String serverURL=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        Channel channel=buildChannelMeta(serverURL);

        List<Topic> allTopics = topicService.findAll();
        List<Item> items = new ArrayList<Item>();
        if (null != allTopics) {
            for (Topic t : allTopics) {
                Item item = new Item();
                item.setTitle(t.getTitle());
                item.setLink(serverURL+"/article/"+t.getId());

                Description description = new Description();
                description.setType("text/plain");
                description.setValue(t.getDesc());
                item.setDescription(description);

                item.setAuthor(t.getAuthorName());
                item.setPubDate(t.getCreateAt());

                items.add(item);
            }
        }

        channel.setItems(items);

        WireFeedOutput out = new WireFeedOutput();
        String xml =null;
        try {
            xml= out.outputString(channel);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
        return xml;
    }

    /**
     * 构建RSS文件头信息
     * @return
     */
    private Channel buildChannelMeta(String serverURL){
        Channel channel = new Channel(RSS_CHANNEL_TYPE);
        channel.setTitle("tBlog:个人博客系统");
        channel.setDescription("简单，轻巧的博客系统");
        channel.setLink(serverURL);
        channel.setEncoding("utf-8");
        channel.setLanguage("zh-cn");
        channel.setPubDate(new Date());
        return channel;
    }

}
