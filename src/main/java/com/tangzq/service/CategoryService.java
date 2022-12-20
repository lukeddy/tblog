package com.tangzq.service;

import com.tangzq.dto.CategoryDto;
import com.tangzq.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author luke
 */
public interface CategoryService {

    /**
     * 新增栏目
     * @param cat
     * @return
     */
    Category addCategory(Category cat);

    /**
     * 增加栏目
     * @param dto
     * @return
     */
    Category addCategory(CategoryDto dto);

    /**
     * 使用栏目ID找到栏目信息
     * @param id
     * @return
     */
    Category getCategory(String id);

    /**
     * 目录名查找目录
     * @param catDir
     * @return
     */
    Category getCategoryByCatDir(String catDir);

    /**
     * 分页查找
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Category> findByPage(int pageNo, int pageSize);

    /**
     * 找到所有栏目信息
     * @return
     */
    List<Category> findAll();

    /**
     * 判断栏目是否已经存在
     * @param catDir
     * @return
     */
    boolean isCategoryExisted(String catDir);

    /**
     * 更新栏目信息
     * @param cat
     * @param id
     * @return
     */
    Category updateById(Category cat, String id);

    /**
     * 更新栏目信息
     * @param id
     * @param dto
     * @return
     */
    Category updateById(String id,CategoryDto dto);

    /**
     * 删除指定栏目
     * @param id
     * //TODO 删除栏目同时也要删除该栏目下文章
     */
    void deleteCategory(String id);
}
