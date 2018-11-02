package com.tangzq.controller.api;

import com.tangzq.model.Category;
import com.tangzq.response.Result;
import com.tangzq.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试api
 * @author tangzhiqiang
 */
@RestController
@RequestMapping("/api/category")
@Api(value = "分类API", description = "博客分类接口",tags = "Category",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value="获取分类列表", notes="将取得所有的博客分类数据")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result catList(){
        List<Category> catList=categoryService.findAll();
        if(null!=catList){
            return Result.ok(catList);
        }
        return Result.fail("获取数据失败");
    }
}
