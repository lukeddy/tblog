package com.tangzq.controller;

import com.tangzq.model.Category;
import com.tangzq.response.Result;
import com.tangzq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tangzhiqiang
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/catlist")
    public Result catList(){
        List<Category> catList=categoryService.findAll();
        if(null!=catList){
            return Result.ok(catList);
        }
        return Result.fail("获取数据失败");
    }
}
