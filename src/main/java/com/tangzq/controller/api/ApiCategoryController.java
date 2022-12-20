package com.tangzq.controller.api;

import com.tangzq.dto.CategoryDto;
import com.tangzq.model.Category;
import com.tangzq.response.Result;
import com.tangzq.service.CategoryService;
import com.tangzq.vo.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试api
 * @author luke
 */
@RestController
@RequestMapping("/api/category")
@Tag(name = "分类API")
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary="获取所有的分类", description="将取得所有的博客分类数据")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result allCategory(){
        List<Category> catList=categoryService.findAll();
        if(null!=catList){
            return Result.ok("成功",catList);
        }
        return Result.fail("获取数据失败");
    }

    /**
     * 分页加载栏目列表
     * @return
     */
    @Operation(summary="分页获取分类列表", description="将取得所有的博客分类数据")
    @RequestMapping(value="/list",method = RequestMethod.POST)
    public Result listCategory(@RequestBody PageVo pageVo){
        return Result.ok("分页获取栏目成功",categoryService.findByPage(pageVo.getPageNO(),pageVo.getPageSize()));
    }

    /**
     * 新增栏目
     * @param dto
     * @return
     */
    @Operation(summary="添加分类", description="添加分类信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result addCategory(@RequestBody CategoryDto dto){
        if(null==dto|| StringUtils.isEmpty(dto.getCatName())||StringUtils.isEmpty(dto.getCatDir())){
            return Result.fail("栏目名称和目录名不能为空");
        }

        if(categoryService.isCategoryExisted(dto.getCatDir())){
            return Result.fail("目录名已经存在,请换成其他的");
        }

        Category savedCat=categoryService.addCategory(dto);
        if(null!=savedCat&&savedCat.getId()!=null){
            return Result.ok("创建栏目成功");
        }else{
            return Result.fail("栏目创建失败");
        }
    }

    @Operation(summary="获取分类详细信息", description="根据url的id来获取分类详细信息")
    @Parameter(name = "id",description = "分类ID",in = ParameterIn.PATH)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Result getCategory(@PathVariable String id) {
        return Result.ok("成功",categoryService.getCategory(id));
    }


    @Operation(summary="更新分类详细信息", description="根据url的id来指定更新对象，并根据传过来的分类信息更新")
    @Parameter(name = "id",description = "分类ID",in = ParameterIn.PATH)
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Result updateCategory(@PathVariable String id, @RequestBody CategoryDto dto) {
        if(StringUtils.isEmpty(dto.getCatName())||StringUtils.isEmpty(dto.getCatDir())){
            return Result.fail("栏目名称和目录名不能为空");
        }
        Category category=categoryService.getCategoryByCatDir(dto.getCatDir());
        if(null!=category&&!StringUtils.equals(category.getId(),id)){
            return Result.fail("栏目名称已经存在");
        }

        Category updatedCat=categoryService.updateById(id,dto);
        if(null==updatedCat){
            return Result.fail("更新失败");
        }
        return Result.ok("更新分类成功",updatedCat);
    }

    @Operation(summary="删除分类", description="根据url的id来指定删除分类")
    @Parameter(name = "id",description = "分类ID",in = ParameterIn.PATH)
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Result deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return Result.ok("删除分类成功");
    }
}
