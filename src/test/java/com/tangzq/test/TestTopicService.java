package com.tangzq.test;


import com.tangzq.model.Topic;
import com.tangzq.service.TopicService;
import com.tangzq.vo.IndexVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

public class TestTopicService extends TestBase {

    @Autowired
    private TopicService topicService;


    @Test
    public void testFindByCat(){
        IndexVo vo=new IndexVo();
        vo.setTab("hcash");
        Page<Topic> page= topicService.findByPage(vo);
        Assert.isTrue(page.getTotalElements()>0,"内容不存在");
        System.out.println(page.getContent());

        vo.setTab("notab");
        Page<Topic> page2= topicService.findByPage(vo);
        Assert.isTrue(page2.getTotalElements()<=0,"内容存在");

    }
}
