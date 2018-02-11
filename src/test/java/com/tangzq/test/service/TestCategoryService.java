package com.tangzq.test.service;

import com.tangzq.model.Category;
import com.tangzq.service.CategoryService;
import com.tangzq.test.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

public class TestCategoryService extends TestBase {

    @Autowired
    private CategoryService categoryService;

    @Before
    public void init(){
        for(int i=0;i<3;i++){
            Category cat=new Category();
            String catDir="catdir"+i;
            cat.setCatName("栏目"+i);
            cat.setCatDir(catDir);
            cat.setCatDesc("栏目"+i+"描述");
            cat.setCreateAt(new Date());
            cat.setUpdateAt(new Date());
            if(categoryService.isCategoryExisted(catDir)){
                System.out.println("相同栏目名称已经存在，不能保存");
            }else{
               Category savedCat=categoryService.addCategory(cat);
                System.out.println(savedCat.getId());
            }
        }
    }

    @Test
    public void testIsCategoryExisted(){
        Assert.isTrue(categoryService.isCategoryExisted("catdir1"),"catdir1栏目不存在");
        Assert.isTrue(!categoryService.isCategoryExisted("aaa"),"aaa栏目已经存在");
    }

    @Test
    public void testFindAll(){
        List<Category> catList=categoryService.findAll();
        Assert.isTrue(!catList.isEmpty(),"没有找到栏目");
        for(Category cat:catList){
            System.out.println(cat);
        }
    }

    @Test
    public void testUpdateCategory(){
        Category cat=new Category();
        cat.setCatName("测试栏目faf");
        cat.setCatDir("test");
        cat.setCatDesc("测试栏目描述信息sdf");
        cat.setCreateAt(new Date());
        cat.setUpdateAt(new Date());
        Category updatedCat=categoryService.updateById(cat,"59b67c3b77c8698b908553dc");
        Assert.notNull(updatedCat,"更新失败");
        System.out.println(updatedCat);
    }

    @Test
    public void testDeleteCategory(){
        String deleteCatId="59b67c3b77c8698b908553dc";
        categoryService.deleteCategory(deleteCatId);
    }

    @Test
    public void testFindByPage(){
        Page<Category> page=categoryService.findByPage(2,6);
        Assert.notNull(page,"");
        System.out.println(page.hasPrevious());
        System.out.println(page.getNumber());
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }
}
