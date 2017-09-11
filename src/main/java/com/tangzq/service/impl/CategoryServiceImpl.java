package com.tangzq.service.impl;

import com.tangzq.model.Category;
import com.tangzq.repository.CategoryRepository;
import com.tangzq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(null==findById(id)){
            return null;
        }
        cat.setId(id);
        return categoryRepository.save(cat);
    }

    public void deleteCategory(String id) {
        categoryRepository.delete(id);
    }
}
