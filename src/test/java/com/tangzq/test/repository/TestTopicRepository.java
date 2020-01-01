package com.tangzq.test.repository;


import com.tangzq.model.Topic;
import com.tangzq.repository.TopicRepository;
import com.tangzq.test.TestBase;
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
    public void testLikeQuery() {
        Sort sort =Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(1 - 1, 5, sort);

        String keyword = "这个行业";
        Page<Topic> page = topicRepository.findByTitleLikeOrDescLike(keyword, keyword, pageable);
        Assert.isTrue(page.hasContent(), "没有结果");
    }
}
