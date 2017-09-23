package com.tangzq.repository;

import com.tangzq.model.Reply;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论操作类
 */
@Repository
public interface ReplyRepository extends PagingAndSortingRepository<Reply,String> {

    List<Reply> findAllByTopicId(String topicId);
}
