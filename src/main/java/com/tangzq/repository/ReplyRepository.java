package com.tangzq.repository;

import com.tangzq.model.Reply;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论操作类
 * @author tangzhiqiang
 */
@Repository
public interface ReplyRepository extends PagingAndSortingRepository<Reply,String> {

    /**
     * 查找文章的所有评论
     * @param topicId
     * @return
     */
    List<Reply> findAllByTopicId(String topicId);
}
