package com.tangzq.service;

import com.tangzq.model.Reply;
import com.tangzq.vo.ReplyVo;

import java.util.List;

/**
 * @author tangzhiqiang
 */
public interface ReplyService {


    /**
     * 取得指定评论内容
     * @param replyID
     * @return
     */
    Reply getReply(String replyID);


    /**
     * 新增评论
     * @param vo
     * @return
     */
    Reply addReply(ReplyVo vo);


    /**
     * 更新评论内容
     * @param replyId
     * @param contentMD
     * @param contentHTML
     * @return
     */
    Reply updateReplyContent(String replyId, String contentMD, String contentHTML);


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
    void deleteReply(String replyId, String topicId);
}
