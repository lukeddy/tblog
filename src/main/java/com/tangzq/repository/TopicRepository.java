package com.tangzq.repository;

import com.tangzq.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 帖子操作类
 */
@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic,String> {

    Page<Topic> findByCatDir(String catDir, Pageable pageable);

}
