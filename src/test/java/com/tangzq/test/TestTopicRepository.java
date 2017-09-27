package com.tangzq.test;


import com.tangzq.model.Topic;
import com.tangzq.repository.TopicRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

public class TestTopicRepository extends TestBase {


    @Autowired
    private TopicRepository topicRepository;


    @Test
    public void testLikeQuery(){
        Sort sort = new Sort(Sort.Direction.DESC, "create_at");
        Pageable pageable = new PageRequest(1-1, 5, sort);

        String keyword="这个行业";
        Page<Topic> page=topicRepository.findByTitleLikeOrDescLike(keyword,keyword,pageable);
        //Page<Topic> page=topicRepository.likeQuery(keyword,pageable);
        Assert.isTrue(page.hasContent(),"没有结果");
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }
}
