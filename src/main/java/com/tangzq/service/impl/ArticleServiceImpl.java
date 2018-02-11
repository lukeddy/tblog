package com.tangzq.service.impl;

import com.tangzq.model.Reply;
import com.tangzq.model.Topic;
import com.tangzq.service.ArticleService;
import com.tangzq.service.ReplyService;
import com.tangzq.service.TopicService;
import com.tangzq.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangzhiqiang
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    @Override
    public ArticleVo findArticleVo(String topicId) {
        Topic topic=topicService.findTopicById(topicId);
        List<Reply> replyList=replyService.findReplyByTopicId(topicId);
        ArticleVo vo=new ArticleVo();
        vo.setTopic(topic);
        vo.setReplyList(replyList);
        return vo;
    }
}
