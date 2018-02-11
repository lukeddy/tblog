package com.tangzq.test.service;


import com.tangzq.model.Topic;
import com.tangzq.service.TopicService;
import com.tangzq.test.TestBase;
import com.tangzq.vo.IndexVo;
import com.tangzq.vo.SearchVo;
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

    @Test
    public void testSearch(){
        SearchVo vo=new SearchVo();
        vo.setKeywords(null);
        Page<Topic> page=topicService.search(vo);
        Assert.isTrue(page.getTotalElements()>0,"没有内容");

        vo.setKeywords("测试aaa");
        page=topicService.search(vo);
        Assert.isTrue(page.getTotalElements()<=0,"关键字'"+vo.getKeywords()+"'有内容");

        vo.setKeywords("Web");
        page=topicService.search(vo);
        Assert.isTrue(page.getTotalElements()>0,"关键字'"+vo.getKeywords()+"'没有内容");
    }
}
