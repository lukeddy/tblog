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

    /**
     * 按栏目分类查找
     * @param catDir
     * @param pageable
     * @return
     */
    Page<Topic> findByCatDir(String catDir, Pageable pageable);

    /**
     * 标题模糊查询
     * @param title
     * @param pageable
     * @return
     */
    Page<Topic> findByTitleLike(String title,Pageable pageable);

}
