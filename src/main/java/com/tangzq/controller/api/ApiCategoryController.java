package com.tangzq.controller.api;

import com.tangzq.model.Category;
import com.tangzq.response.Result;
import com.tangzq.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
            return Result.ok("成功",catList);
        }
        return Result.fail("获取数据失败");
    }

    /**
     * 新增栏目
     * @param cat
     * @return
     */
    @ApiOperation(value="添加分类", notes="添加分类信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result addCategory(@RequestBody Category cat){
        if(null==cat|| StringUtils.isEmpty(cat.getCatName())||StringUtils.isEmpty(cat.getCatDir())){
            return Result.fail("栏目名称和目录名不能为空");
        }

        if(categoryService.isCategoryExisted(cat.getCatDir())){
            return Result.fail("目录名已经存在,请换成其他的");
        }

        cat.setCreateAt(new Date());
        cat.setUpdateAt(new Date());
        Category savedCat=categoryService.addCategory(cat);
        if(null!=savedCat&&savedCat.getId()!=null){
            return Result.ok("创建栏目成功");
        }else{
            return Result.fail("栏目创建失败");
        }
    }
}
