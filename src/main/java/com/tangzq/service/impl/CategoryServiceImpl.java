package com.tangzq.service.impl;

import com.tangzq.model.Category;
import com.tangzq.repository.CategoryRepository;
import com.tangzq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    public boolean isCategoryExisted(String catDir) {
        List<Category> catList=categoryRepository.findByCatDir(catDir);
        if(null!=catList&&!catList.isEmpty()){
            return true;
        }
        return false;
    }

    public Category addCategory(Category cat) {
      return  categoryRepository.save(cat);
    }

    public Category findById(String id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> findAll() {
        return (List<Category>)categoryRepository.findAll();
    }

    public Category updateById(Category cat, String id) {
        Category catInDB=findById(id);
        if(null==catInDB){
            return null;
        }
        cat.setId(id);
        cat.setCreateAt(catInDB.getCreateAt()==null?new Date():catInDB.getCreateAt());
        return categoryRepository.save(cat);
    }

    public void deleteCategory(String id) {
        categoryRepository.delete(id);
    }
}
