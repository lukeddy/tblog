package com.tangzq.service.impl;

import com.tangzq.model.Topic;
import com.tangzq.repository.TopicRepository;
import com.tangzq.service.TopicService;
import com.tangzq.vo.TopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;


    public Page<Topic> findByPage(int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "create_at");
        Pageable pageable = new PageRequest(pageNo-1, pageSize, sort);
        return topicRepository.findAll(pageable);
    }

    public Topic addTopic(TopicVo vo) {
        return topicRepository.save(convertToTopic(vo));
    }


    private Topic convertToTopic(TopicVo vo){
        if(null==vo){
            return null;
        }
        Topic topic=new Topic();
        topic.setAuthorId(vo.getAuthorId());
        topic.setAuthorName(vo.getAuthorName());
        topic.setCatId(vo.getCatId());
        topic.setTitle(vo.getTitle());
        topic.setContentMD(vo.getContentMD());
        topic.setContentHTML(vo.getContentHTML());
        topic.setContentIsHTML(vo.isContentIsHTML());
        topic.setTop(vo.isTop());
        topic.setGood(vo.isGood());
        topic.setCreateAt(new Date());
        topic.setUpdateAt(new Date());
        return topic;
    }

    public Topic findTopicById(String topicId) {
        return topicRepository.findOne(topicId);
    }

    public TopicVo findTopicVoById(String topicId) {
        return convertToVo(findTopicById(topicId));
    }

    private TopicVo convertToVo(Topic topic){
        if(null==topic){
            return null;
        }
        TopicVo vo=new TopicVo();
        vo.setTopicId(topic.getId());
        vo.setAuthorName(topic.getAuthorName());
        vo.setAuthorId(topic.getAuthorId());
        vo.setCatId(topic.getCatId());
        vo.setTitle(topic.getTitle());
        vo.setContentMD(topic.getContentMD());
        vo.setContentHTML(topic.getContentHTML());
        vo.setContentIsHTML(topic.isContentIsHTML());
        vo.setTop(topic.isTop());
        vo.setGood(topic.isGood());
        return vo;
    }


    public Topic updateById(TopicVo vo, String id) {
        Topic topicInDB=findTopicById(id);
        if(null==topicInDB){
            return null;
        }
        topicInDB.setCatId(vo.getCatId());
        topicInDB.setTitle(vo.getTitle());
        topicInDB.setAuthorId(vo.getAuthorId());
        topicInDB.setAuthorName(vo.getAuthorName());
        topicInDB.setContentMD(vo.getContentMD());
        topicInDB.setContentHTML(vo.getContentHTML());
        topicInDB.setContentIsHTML(vo.isContentIsHTML());
        topicInDB.setTop(vo.isTop());
        topicInDB.setGood(vo.isGood());
        topicInDB.setUpdateAt(new Date());
        return topicRepository.save(topicInDB);
    }

    public void deleteById(String topicId) {
        topicRepository.delete(topicId);
    }

    public void increaseVisitCount(String topicId) {
        Topic topicInDB=findTopicById(topicId);
        if(null==topicInDB){
            return;
        }
        topicInDB.setVisitCount(topicInDB.getVisitCount()+1);
        topicRepository.save(topicInDB);
    }

    public void increaseReplyCount(String topicId) {
        Topic topicInDB=findTopicById(topicId);
        if(null==topicInDB){
            return;
        }
        topicInDB.setReplyCount(topicInDB.getReplyCount()+1);
        topicRepository.save(topicInDB);
    }


}