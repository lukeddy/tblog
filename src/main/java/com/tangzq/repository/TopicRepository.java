package com.tangzq.repository;

import com.tangzq.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 帖子操作类
 * @author tangzhiqiang
 */
@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic,String> {

    /**
     * 查找指定用户的帖子
     * @param authorId
     * @param pageable
     * @return
     */
    Page<Topic> findByAuthorId(String authorId, Pageable pageable);

    /**
     * 查找指定用户的帖子
     * @param authorName
     * @param pageable
     * @return
     */
    Page<Topic> findByAuthorName(String authorName, Pageable pageable);

    /**
     * 查找含有指定标签的帖子
     * @param tagName
     * @param pageable
     * @return
     */
    Page<Topic> findByTagsContains(String tagName, Pageable pageable);

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
    Page<Topic> findByTitleLike(String title, Pageable pageable);


    /**
     * 在标题标题或者摘要模糊查询
     * @param title
     * @param desc
     * @param pageable
     * @return
     */
    Page<Topic> findByTitleLikeOrDescLike(String title, String desc, Pageable pageable);


    /**
     * 指定用户收藏的所有文章
     * @param userId
     * @param pageable
     * @return
     */
    Page<Topic> findByCollectedUsersContains(String userId, Pageable pageable);

    /**
     * 在标题标题或者摘要模糊查询
     * @param words 关键字
     * @param page
     * @return
     */
    @Query(value = "{ $or: [ { 'title' : {$regex:?0,$options:'i'} }, { 'desc' : {$regex:?0,$options:'i'} } ] }")
    Page<Topic> likeQuery(String words, Pageable page);

}
