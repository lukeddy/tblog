package com.tangzq.service.impl;

import com.tangzq.model.Comment;
import com.tangzq.model.User;
import com.tangzq.model.type.CommentType;
import com.tangzq.repository.CommentRepository;
import com.tangzq.service.CommentService;
import com.tangzq.service.UserService;
import com.tangzq.vo.CommentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 评论业务逻辑实现
 * @author tangzhiqiang
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public List<Comment> getAllItemComments(String itemId) {
        Sort sort = new Sort(Sort.Direction.DESC,"thumbsUPCount","create_at");
        return repository.findAllByItemId(itemId,sort);
    }

    @Override
    public Page<Comment> getItemCommentsByPage(String itemId, int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return repository.findByItemId(itemId,pageable);
    }

    @Override
    public Comment getComment(String id) {
        Optional<Comment> optional=repository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Comment addComment(CommentVo vo, CommentType type) {
        return repository.save(convertVoToComment(vo,type));
    }

    private Comment convertVoToComment(CommentVo vo, CommentType type) {
        if (null == vo) {
            return null;
        }
        Comment comment = new Comment();
        comment.setItemId(vo.getItemId());
        comment.setType(type);
        comment.setCommentMD(vo.getCommentMD());
        comment.setCommentHTML(vo.getCommentHTML());

        comment.setAuthor(userService.getUser(vo.getAuthorId()));
        comment.setThumbsUPCount(0);

        comment.setDeleteStatus(Boolean.FALSE);
        comment.setReportStatus(Boolean.FALSE);
        comment.setCreateAt(new Date());
        comment.setUpdateAt(new Date());
        return comment;
    }

    @Override
    public void deleteComment(String commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public Comment replyComment(String commentId, CommentVo replyContent, CommentType type) {
       Comment parentComment=this.getComment(commentId);
       if(null==parentComment){
           return null;
       }
       Comment replyComment=convertVoToComment(replyContent,type);

       replyComment.setParentComment(parentComment);
       return repository.save(replyComment);
    }

    @Override
    public Comment updateRepotStatus(String commentId, boolean reportStatus) {
        Comment commentInDB=this.getComment(commentId);
        if(null==commentInDB){
            return null;
        }
        commentInDB.setReportStatus(reportStatus);
        return repository.save(commentInDB);
    }

    @Override
    public Comment updateThumbsUpData(String commentId, User thumbsUpUser) {
        Comment commentInDB=this.getComment(commentId);
        if(null==commentInDB){
            return null;
        }
        Set<User> thumbsUpUsers=commentInDB.getThumbsUpUsers();
        if(null==thumbsUpUsers){
            thumbsUpUsers=new HashSet<>();
        }
        if(thumbsUpUsers.contains(thumbsUpUser)){
            thumbsUpUsers.remove(thumbsUpUser);
        }else{
            thumbsUpUsers.add(thumbsUpUser);
        }
        commentInDB.setThumbsUpUsers(thumbsUpUsers);
        commentInDB.setThumbsUPCount(thumbsUpUsers.size());
        return repository.save(commentInDB);
    }

}
