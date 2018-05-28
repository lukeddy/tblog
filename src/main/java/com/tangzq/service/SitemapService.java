package com.tangzq.service;

import java.net.MalformedURLException;

/**
 * @author tangzhiqiang sitemap生成器
 */
public interface SitemapService {
     String createSitemap() throws MalformedURLException;

     void createSitemapToFolder(String folderPath) throws MalformedURLException;

     String createCategorySitemap() throws MalformedURLException;
}
