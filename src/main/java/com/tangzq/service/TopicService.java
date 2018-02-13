package com.tangzq.service;

import com.tangzq.model.Topic;
import com.tangzq.vo.IndexVo;
import com.tangzq.vo.SearchVo;
import com.tangzq.vo.TopicVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author tangzhiqiang
 */
public interface TopicService {

    /**
     * 分页查找
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByPage(int pageNo, int pageSize);

    /**
     * 指定用户帖子
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByUserIdAndPage(String userId, int pageNo, int pageSize);

    /**
     * 指定用户创建的帖子
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByUsernameAndPage(String username, int pageNo, int pageSize);

    /**
     * 含有指定标签的帖子
     * @param tagName
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByTagAndPage(String tagName, int pageNo, int pageSize);

    /**
     * 首页分页查找
     * @param vo
     * @return
     */
    Page<Topic> findByPage(IndexVo vo);


    /**
     * 关键字分页查找
     * @param searchVo
     * @return
     */
    Page<Topic> search(SearchVo searchVo);


    /**
     * 新增帖子
     * @param vo
     * @return
     */
    Topic addTopic(TopicVo vo);


    /**
     * 查找指定帖子
     * @param topicId
     * @return
     */
    Topic findTopicById(String topicId);


    /**
     * 查找指定帖子,并转换成vo类
     * @param topicId
     * @return
     */
    TopicVo findTopicVoById(String topicId);


    /**
     * 更新帖子信息
     * @param vo
     * @param id
     * @return
     */
    Topic updateById(TopicVo vo, String id);


    /**
     * 删除帖子
     * @param topicId
     */
    void deleteById(String topicId);

    /**
     * 更新访问次数
     * @param topicId
     */
    void increaseVisitCount(String topicId);

    /**
     * 更新评论次数
     * @param topicId
     */
    void increaseReplyCount(String topicId);

    /**
     * 减少评论次数
     * @param topicId
     */
    void decreaseReplyCount(String topicId);

    /**
     * 收藏文章
     * @param topicId
     * @param userId
     * @return
     */
    Topic addCollection(String topicId, String userId);


    /**
     * 取消收藏
     * @param topicId
     * @param userId
     * @return
     */
    Topic removeCollection(String topicId, String userId);

    /**
     * 喜欢文章
     * @param topicId
     * @param userId
     * @return
     */
    Topic like(String topicId, String userId);


    /**
     * 不喜欢收藏
     * @param topicId
     * @param userId
     * @return
     */
    Topic unLike(String topicId, String userId);


    /**
     * 用户收藏的所有文章
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findCollectedTopicsByUidAndPage(String userId, int pageNo, int pageSize);

    /**
     * 查找所有帖子
     * @return
     */
    List<Topic> findAll();
}
