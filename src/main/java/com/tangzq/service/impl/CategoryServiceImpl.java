package com.tangzq.service.impl;

import com.tangzq.model.Category;
import com.tangzq.repository.CategoryRepository;
import com.tangzq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /**
     * 注意：这里Page的页码是从0开始，所以取值需要传递过来的页码减去1
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Category> findByPage(int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "create_at");
        Pageable pageable = new PageRequest(pageNo-1, pageSize, sort);
        return categoryRepository.findAll(pageable);
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
