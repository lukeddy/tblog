package com.tangzq.service;

import com.tangzq.model.Comment;
import com.tangzq.model.User;
import com.tangzq.model.embed.ReportInfo;
import com.tangzq.model.type.CommentType;
import com.tangzq.vo.CommentVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 评论业务逻辑
 * @author tangzhiqiang
 */
public interface CommentService {


    /**
     * 找到指定项目的所有相关评论
     * @param itemId
     * @return
     */
    List<Comment> getAllItemComments(String itemId);


    /**
     * 分页找到指定项目的相关评论
     * @param itemId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Comment> getItemCommentsByPage(String itemId, int pageNo, int pageSize);

    /**
     * 获取指定评论
     * @param id
     * @return
     */
    Comment getComment(String id);

    /**
     * 新增评论
     * @param vo
     * @return
     */
    Comment addComment(CommentVo vo, CommentType type);


    /**
     * 删除评论
     * @param commentId
     */
    void deleteComment(String commentId);

    /**
     * 回复评论
     * @param commentId
     * @return
     */
    Comment replyComment(String commentId, CommentVo replyContent, CommentType type);

    /**
     * 更新评论举报状态
     * @param commentId
     * @param reportStatus
     * @return
     */
    Comment updateRepotStatus(String commentId, boolean reportStatus);

    /**
     * 举报评论
     * @param commentId
     * @param reportUser 举报用户
     * @param reportInfo 举报理由
     * @return
     */
    Comment reportComment(String commentId, User reportUser, ReportInfo reportInfo);

    /**
     * 点赞数据更新
     * @param commentId
     * @param loginUser
     * @return
     */
    Comment updateThumbsUpData(String commentId, User loginUser);
}
