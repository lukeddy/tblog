package com.tangzq.service;

import com.tangzq.vo.ArticleVo;

/**
 * @author tangzhiqiang
 */
public interface ArticleService {


    /**
     * 找到指定文章
     * @param topicId
     * @return
     */
    ArticleVo findArticleVo(String topicId);



}
