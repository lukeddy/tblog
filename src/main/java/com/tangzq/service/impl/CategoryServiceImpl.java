package com.tangzq.service.impl;

import com.tangzq.dto.CategoryDto;
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
import java.util.Optional;


/**
 * @author tangzhiqiang
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public boolean isCategoryExisted(String catDir) {
        List<Category> catList=categoryRepository.findByCatDir(catDir);
        if(null!=catList&&!catList.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public Category addCategory(Category cat) {
      return  categoryRepository.save(cat);
    }

    @Override
    public Category addCategory(CategoryDto dto) {
        if(null==dto){
            return null;
        }
        Category cat=new Category();
        cat.setCatName(dto.getCatName());
        cat.setCatDir(dto.getCatDir());
        cat.setCatDesc(dto.getCatDesc());
        cat.setCreateAt(new Date());
        cat.setUpdateAt(new Date());
        return categoryRepository.save(cat);
    }

    @Override
    public Category getCategory(String id) {
        Optional<Category> optional=categoryRepository.findById(id);
        return optional.isPresent()? optional.get():null;
    }

    @Override
    public Category getCategoryByCatDir(String catDir) {
        List<Category> categoryList=categoryRepository.findByCatDir(catDir);
        if(categoryList!=null&&categoryList.size()>0){
            return categoryList.get(0);
        }
        return null;
    }

    /**
     * 注意：这里Page的页码是从0开始，所以取值需要传递过来的页码减去1
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Category> findByPage(int pageNo, int pageSize) {
        Sort sort =Sort.by(Sort.Direction.DESC, "create_at");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Override
    public Category updateById(Category cat, String id) {
        Category catInDB=getCategory(id);
        if(null==catInDB){
            return null;
        }
        cat.setId(id);
        cat.setCreateAt(catInDB.getCreateAt()==null?new Date():catInDB.getCreateAt());
        return categoryRepository.save(cat);
    }

    @Override
    public Category updateById(String id, CategoryDto dto) {
        Category catInDB=this.getCategory(id);
        if(null==dto||catInDB==null){
            return null;
        }
        catInDB.setCatName(dto.getCatName());
        catInDB.setCatDir(dto.getCatDir());
        catInDB.setCatDesc(dto.getCatDesc());
        catInDB.setUpdateAt(new Date());
        return categoryRepository.save(catInDB);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
