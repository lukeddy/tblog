package com.tangzq.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分类页面数据封装
 * @author luke
 */
@Getter
@Setter
@ToString
public class CategoryDto{

    private String catName;
    private String catDir;
    private String catDesc;

}
