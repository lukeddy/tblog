package com.tangzq.service;

import com.tangzq.model.Category;
import com.tangzq.model.Topic;
import com.tangzq.vo.TopicVo;
import org.springframework.data.domain.Page;

public interface TopicService {

    /**
     * 分页查找
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByPage(int pageNo, int pageSize);

    /**
     * 新增帖子
     * @param vo
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
}
