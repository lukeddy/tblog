package com.tangzq.repository;

import com.tangzq.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 栏目文档操作类
 * @author tangzhiqiang
 */
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,String> {

    /**
     * 目录名字必须唯一
     * @param catDir 栏目英文目录名称
     * @return
     */
    List<Category> findByCatDir(String catDir);

}
