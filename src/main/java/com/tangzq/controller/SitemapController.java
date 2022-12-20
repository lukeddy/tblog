package com.tangzq.controller;

import com.tangzq.service.SitemapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

/**
 * Sitemap控制器
 * @author luke
 */
@Slf4j
@Controller
public class SitemapController {

    @Autowired
    private SitemapService sitemapService;

    @RequestMapping(value = "/sitemap.xml", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String sitemap() {
        String sitemap=null;
        try {
            sitemap= sitemapService.createSitemap();
        } catch (MalformedURLException e) {
            log.error("生成sitemap出错",e);
        }
        return  sitemap;
    }

}
