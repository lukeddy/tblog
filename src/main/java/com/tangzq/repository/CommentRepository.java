package com.tangzq.repository;

import com.tangzq.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论操作类
 * @author tangzhiqiang
 */
@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,String> {

    /**
     * 查找某个项目的相关评论
     * @param itemId
     * @return
     */
    List<Comment> findAllByItemId(String itemId, Sort sort);


    /**
     * 分页查找相关评论
     * @param itemId
     * @param pageable
     * @return
     */
    Page<Comment> findByItemId(String itemId, Pageable pageable);

}
