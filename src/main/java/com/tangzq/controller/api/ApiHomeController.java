package com.tangzq.controller.api;

import com.tangzq.response.Result;
import com.tangzq.service.CategoryService;
import com.tangzq.service.TopicService;
import com.tangzq.vo.IndexVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 首页API
 * @author luke
 */
@RestController
@RequestMapping("/api/home")
@Tag(name = "网站首页API", description = "博客首页接口")
public class ApiHomeController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;

    @Operation(summary="网站首页", description="首页内容，包括分类，文章等")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result home(@RequestBody IndexVo vo) {
        HashMap<String,Object> map=new HashMap<>(3);
        map.put("pager",topicService.findByPage(vo));
        map.put("catList",categoryService.findAll());
        map.put("indexVo",vo);
        return Result.ok("成功",map);
    }

    @RequestMapping(value = "/testError")
    public String about() {
        throw new RuntimeException();
    }
}
