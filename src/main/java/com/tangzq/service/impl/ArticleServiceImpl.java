package com.tangzq.service.impl;

import com.tangzq.model.Topic;
import com.tangzq.service.ArticleService;
import com.tangzq.service.TopicService;
import com.tangzq.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangzhiqiang
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private TopicService topicService;

    @Override
    public ArticleVo findArticleVo(String topicId) {
        Topic topic=topicService.findTopicById(topicId);
        ArticleVo vo=new ArticleVo();
        vo.setTopic(topic);
        return vo;
    }
}
