package com.tangzq.service;

import com.tangzq.model.Topic;
import com.tangzq.vo.IndexVo;
import com.tangzq.vo.SearchVo;
import com.tangzq.vo.TopicVo;
import org.springframework.data.domain.Page;

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
}
