package com.tangzq.controller.api;


import com.tangzq.model.Topic;
import com.tangzq.response.Result;
import com.tangzq.service.TopicService;
import com.tangzq.vo.TopicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/post")
@Api(value = "帖子API", description = "博客帖子接口",tags = "Topic")
public class ApiTopicController {

    @Autowired
    private TopicService topicService;

    @ApiOperation(value="获取帖子列表", notes="获取所有帖子")
    @RequestMapping(value={"/list"}, method= RequestMethod.GET)
    public Result getTopics() {
        List<Topic> topicList=topicService.findAll();
        return Result.ok("成功",topicList);
    }

    @ApiOperation(value="创建帖子", notes="使用API创建帖子")
    @ApiImplicitParam(name = "topicVo", value = "帖子详细VO实体对象", required = true, dataType = "TopicVo")
    @RequestMapping(value="", method=RequestMethod.POST)
    public Result addTopic(@RequestBody TopicVo topicVo) {
        //TODO vo验证
        topicService.addTopic(topicVo);
        return Result.ok("添加成功");
    }

    @ApiOperation(value="获取帖子详细信息", notes="根据url的id来获取帖子详细信息")
    @ApiImplicitParam(name = "id", value = "帖子ID", required = true, dataType = "String")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Result getTopic(@PathVariable String id) {
        return Result.ok("成功",topicService.findTopicById(id));
    }

    @ApiOperation(value="更新帖子详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "帖子ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "topicVo", value = "帖子详细实体topicVo", required = true, dataType = "TopicVo")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Result updateTopic(@PathVariable String id, @RequestBody TopicVo topicVo) {
        topicService.updateById(topicVo,id);
        return Result.ok("更新帖子成功");
    }

    @ApiOperation(value="删除帖子", notes="根据url的id来指定删除帖子")
    @ApiImplicitParam(name = "id", value = "帖子ID", required = true, dataType = "String")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result deleteTopic(@PathVariable String id) {
        topicService.deleteById(id);
        return Result.ok("删除帖子成功");
    }
}
