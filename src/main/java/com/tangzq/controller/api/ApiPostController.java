package com.tangzq.controller.api;


import com.tangzq.model.Topic;
import com.tangzq.response.Result;
import com.tangzq.service.TopicService;
import com.tangzq.vo.PageVo;
import com.tangzq.vo.TopicVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/post")
@Tag(name = "帖子API", description = "博客帖子接口")
public class ApiPostController {

    @Autowired
    private TopicService topicService;

    @Operation(summary="获取帖子列表", description="获取所有帖子")
    @RequestMapping(value={"/list"}, method= RequestMethod.POST)
    public Result getTopics(@RequestBody PageVo pageVo) {
        return Result.ok("成功",topicService.findByPage(pageVo.getPageNO(),pageVo.getPageSize()));
    }

    @Operation(summary="创建帖子", description="使用API创建帖子")
    @Parameter(name = "topicVo", description = "帖子详细VO实体对象", required = true)
    @RequestMapping(value="", method=RequestMethod.POST)
    public Result addTopic(@RequestBody TopicVo topicVo) {
        if(StringUtils.isEmpty(topicVo.getCatId())){
            return Result.fail("栏目不能为空");
        }
        if(StringUtils.isEmpty(topicVo.getTitle())){
            return Result.fail("标题不能为空");
        }
        if(StringUtils.isEmpty(topicVo.getContentMD())){
            return Result.fail("内容不能为空");
        }
        topicService.addTopic(topicVo);
        return Result.ok("帖子添加成功");
    }

    @Operation(summary="获取帖子详细信息", description="根据url的id来获取帖子详细信息")
    @Parameter(name = "id", description = "帖子ID", required = true)
    @RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
    public Result detail(@PathVariable String id) {
        Topic topic=topicService.findTopicById(id);
        if(null==topic){
            return Result.fail("帖子不存在");
        }
        return Result.ok("成功",topic);
    }

    @Operation(summary="更新帖子详细信息", description="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Result updateTopic(@PathVariable String id, @RequestBody TopicVo topicVo) {
        Topic topic=topicService.findTopicById(id);
        if(null==topic){
            return Result.fail("帖子不存在");
        }
        if(StringUtils.isEmpty(topicVo.getCatId())){
            return Result.fail("栏目不能为空");
        }
        if(StringUtils.isEmpty(topicVo.getTitle())){
            return Result.fail("标题不能为空");
        }
        if(StringUtils.isEmpty(topicVo.getContentMD())){
            return Result.fail("内容不能为空");
        }
        topicService.updateById(topicVo,id);
        return Result.ok("更新帖子成功");
    }

    @Operation(summary="删除帖子", description="根据url的id来指定删除帖子")
    @Parameter(name = "id", description = "帖子ID", required = true)
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result deleteTopic(@PathVariable String id) {
        topicService.deleteById(id);
        return Result.ok("删除帖子成功");
    }
}
