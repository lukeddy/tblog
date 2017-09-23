package com.tangzq.service;

import com.tangzq.model.Reply;
import com.tangzq.vo.ReplyVo;

import java.util.List;

public interface ReplyService {


    /**
     * 新增评论
     * @param vo
     */
    Reply addReply(ReplyVo vo);


    /**
     * 查找指定帖子的评论
     * @param topicId
     * @return
     */
    List<Reply> findReplyByTopicId(String topicId);


    /**
     * 删除评论
     * @param replyId
     * @param topicId
     */
    void deleteReply(String replyId,String topicId);
}
