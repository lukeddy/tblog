package com.tangzq.service.impl;

import com.tangzq.model.Category;
import com.tangzq.model.Topic;
import com.tangzq.model.User;
import com.tangzq.repository.TopicRepository;
import com.tangzq.service.CategoryService;
import com.tangzq.service.TopicService;
import com.tangzq.service.UserService;
import com.tangzq.vo.IndexVo;
import com.tangzq.vo.SearchVo;
import com.tangzq.vo.TopicVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author tangzhiqiang
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    private static final String TAB_ALL="all";

    @Override
    public Page<Topic> findByPage(int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return topicRepository.findAll(pageable);
    }

    @Override
    public Page<Topic> findByUserIdAndPage(String userId, int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return topicRepository.findByAuthorId(userId,pageable);
    }

    @Override
    public Page<Topic> findByUsernameAndPage(String username, int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return topicRepository.findByAuthor(userService.findByUsername(username),pageable);
    }

    @Override
    public Page<Topic> findByTagAndPage(String tagName, int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return topicRepository.findByTagsContains(tagName,pageable);
    }

    @Override
    public Page<Topic> findByPage(IndexVo vo) {
        if(TAB_ALL.equals(vo.getTab())){
            Sort sort = Sort.by(Sort.Direction.DESC, "top","create_at");
            Pageable pageable = PageRequest.of(vo.getPageNO()-1, vo.getPageSize(), sort);
            return topicRepository.findAll(pageable);
        }else{
            Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
            Pageable pageable = PageRequest.of(vo.getPageNO()-1, vo.getPageSize(), sort);

            Category category=categoryService.getCategoryByCatDir(vo.getTab());
            return topicRepository.findByCategory(category,pageable);
        }
    }

    @Override
    public Page<Topic> search(SearchVo searchVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable =  PageRequest.of(searchVo.getPageNO()-1, searchVo.getPageSize(), sort);

        if(StringUtils.isEmpty(searchVo.getKeywords())){
            return topicRepository.findAll(pageable);
        }else{
            return topicRepository.findByTitleLikeOrDescLike(searchVo.getKeywords(),searchVo.getKeywords(),pageable);
        }
    }

    @Override
    public Topic addTopic(TopicVo vo) {
        return topicRepository.save(convertToTopic(vo));
    }


    private Topic convertToTopic(TopicVo vo){
        if(null==vo){
            return null;
        }
        Topic topic=new Topic();
        topic.setAuthor(userService.getUser(vo.getAuthorId()));
        Category cat=categoryService.getCategory(vo.getCatId());
        if(null!=cat){
            topic.setCategory(cat);
        }
        topic.setTitle(vo.getTitle());
        topic.setDesc(vo.getDesc());
        topic.setThumbURL(vo.getThumbURL());
        if(StringUtils.isNotEmpty(vo.getTags())){
            topic.setTags(Arrays.asList(vo.getTags().split(",")));
        }
        topic.setContentMD(vo.getContentMD());
        topic.setContentHTML(vo.getContentHTML());
        topic.setContentIsHTML(vo.isContentIsHTML());
        topic.setTop(vo.isTop());
        topic.setGood(vo.isGood());
        topic.setCreateAt(new Date());
        topic.setUpdateAt(new Date());
        return topic;
    }

    @Override
    public Topic findTopicById(String topicId) {
        Optional<Topic> optional=topicRepository.findById(topicId);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public TopicVo findTopicVoById(String topicId) {
        return convertToVo(findTopicById(topicId));
    }

    private TopicVo convertToVo(Topic topic){
        if(null==topic){
            return null;
        }
        TopicVo vo=new TopicVo();
        vo.setTopicId(topic.getId());
        vo.setAuthorId(topic.getAuthor().getId());
        vo.setCatId(topic.getCategory().getId());
        vo.setTitle(topic.getTitle());
        vo.setDesc(topic.getDesc());
        vo.setThumbURL(topic.getThumbURL());
        if(null!=topic.getTags()){
            vo.setTags(StringUtils.join(topic.getTags(),","));
        }
        vo.setContentMD(topic.getContentMD());
        vo.setContentHTML(topic.getContentHTML());
        vo.setContentIsHTML(topic.isContentIsHTML());
        vo.setTop(topic.isTop());
        vo.setGood(topic.isGood());
        return vo;
    }


    @Override
    public Topic updateById(TopicVo vo, String id) {
        Topic topicInDB=findTopicById(id);
        if(null==topicInDB){
            return null;
        }
        Category cat=categoryService.getCategory(vo.getCatId());
        if(null!=cat){
            topicInDB.setCategory(cat);
        }
        topicInDB.setTitle(vo.getTitle());
        topicInDB.setDesc(vo.getDesc());
        topicInDB.setThumbURL(vo.getThumbURL());
        if(null!=vo.getTags()){
            topicInDB.setTags(Arrays.asList(vo.getTags().split(",")));
        }
        topicInDB.setAuthor(userService.getUser(vo.getAuthorId()));
        topicInDB.setContentMD(vo.getContentMD());
        topicInDB.setContentHTML(vo.getContentHTML());
        topicInDB.setContentIsHTML(vo.isContentIsHTML());
        topicInDB.setTop(vo.isTop());
        topicInDB.setGood(vo.isGood());
        topicInDB.setUpdateAt(new Date());
        return topicRepository.save(topicInDB);
    }

    @Override
    public void deleteById(String topicId) {
        topicRepository.deleteById(topicId);
    }

    @Override
    public void increaseVisitCount(String topicId) {
        Topic topicInDB=findTopicById(topicId);
        if(null==topicInDB){
            return;
        }
        topicInDB.setVisitCount(topicInDB.getVisitCount()+1);
        topicRepository.save(topicInDB);
    }

    @Override
    public void increaseReplyCount(String topicId) {
        Topic topicInDB=findTopicById(topicId);
        if(null==topicInDB){
            return;
        }
        topicInDB.setReplyCount(topicInDB.getReplyCount()+1);
        topicRepository.save(topicInDB);
    }

    @Override
    public void decreaseReplyCount(String topicId) {
        Topic topicInDB=findTopicById(topicId);
        if(null==topicInDB){
            return;
        }
        int replyCount=topicInDB.getReplyCount()>1?topicInDB.getReplyCount()-1:0;
        topicInDB.setReplyCount(replyCount);
        topicRepository.save(topicInDB);
    }

    @Override
    public Topic addCollection(String topicId, String userId) {
        Topic topicInDb=topicRepository.findById(topicId).get();
        if(null==topicInDb){
            return null;
        }
        Set<User> collectedUsers=topicInDb.getCollectedUsers();
        if(null==collectedUsers){
            collectedUsers=new HashSet<User>();
        }
        collectedUsers.add(userService.getUser(userId));
        topicInDb.setCollectedUsers(collectedUsers);
        topicInDb.setCollectCount(collectedUsers.size());
        return topicRepository.save(topicInDb);
    }

    @Override
    public Topic removeCollection(String topicId, String userId) {
        Topic topicInDb=topicRepository.findById(topicId).get();
        if(null==topicInDb){
            return null;
        }
        Set<User> collectedUsers=topicInDb.getCollectedUsers();
        User user=userService.getUser(userId);
        if(null!=collectedUsers&&collectedUsers.contains(user)){
            collectedUsers.remove(user);
            topicInDb.setCollectedUsers(collectedUsers);
            topicInDb.setCollectCount(collectedUsers.size());
        }
        return topicRepository.save(topicInDb);
    }

    @Override
    public Topic like(String topicId, String userId) {
        Topic topicInDb=topicRepository.findById(topicId).get();
        if(null==topicInDb){
            return null;
        }
        Set<User> likedUsers=topicInDb.getCollectedUsers();
        if(null==likedUsers){
            likedUsers=new HashSet<User>();
        }
        likedUsers.add(userService.getUser(userId));
        topicInDb.setLikedUsers(likedUsers);
        return topicRepository.save(topicInDb);
    }

    @Override
    public Topic unLike(String topicId, String userId) {
        Topic topicInDb=topicRepository.findById(topicId).get();
        if(null==topicInDb){
            return null;
        }
        Set<User> likedUsers=topicInDb.getCollectedUsers();
        User user=userService.getUser(userId);
        if(null!=likedUsers&&likedUsers.contains(user)){
            likedUsers.remove(user);
            topicInDb.setLikedUsers(likedUsers);
        }
        return topicRepository.save(topicInDb);
    }

    @Override
    public Page<Topic> findCollectedTopicsByUidAndPage(String userId, int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return topicRepository.findByCollectedUsersContains(userId,pageable);
    }

    @Override
    public List<Topic> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "create_at");
        return (List<Topic>)topicRepository.findAll(sort);
    }

    @Override
    public Page<Topic> getTopVisitedTopics(int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "visitCount");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return topicRepository.findAll(pageable);
    }


}
