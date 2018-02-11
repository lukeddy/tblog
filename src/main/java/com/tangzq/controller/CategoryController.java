package com.tangzq.controller;

import com.tangzq.model.Category;
import com.tangzq.service.CategoryService;
import com.tangzq.vo.PageVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * 栏目控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/cat")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 栏目列表
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String listCategory(PageVo pageVo, ModelMap model){
        model.addAttribute("pager",categoryService.findByPage(pageVo.getPageNO(),pageVo.getPageSize()));
        return "category/cat_list";
    }


    /**
     * 新增栏目表单
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String createCategory(){
        return "category/cat_add";
    }

    /**
     * 新增栏目
     * @param cat
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String doCreateCategory(Category cat,
                                   ModelMap model,
                                   RedirectAttributes redirectAttributes){
        if(null==cat||StringUtils.isEmpty(cat.getCatName())||StringUtils.isEmpty(cat.getCatDir())){
            model.addAttribute("messageErr","栏目名称和目录名不能为空");
            model.addAttribute("cat",cat);
            return "category/cat_add";
        }

        if(categoryService.isCategoryExisted(cat.getCatDir())){
            model.addAttribute("messageErr","目录名已经存在,请换成其他的");
            model.addAttribute("cat",cat);
            return "category/cat_add";
        }

        cat.setCreateAt(new Date());
        cat.setUpdateAt(new Date());
        Category savedCat=categoryService.addCategory(cat);
        if(null!=savedCat&&savedCat.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","创建栏目成功");
            return "redirect:/cat/list";
        }else{
            redirectAttributes.addFlashAttribute("messageErr","栏目创建失败");
            model.addAttribute("cat",cat);
            return "category/cat_add";
        }
    }


    /**
     * 修改栏目表单
     * @param catID
     * @param model
     * @return
     */
    @RequestMapping(value="/edit/{catID}",method = RequestMethod.GET)
    public String editCategory(@PathVariable("catID") String catID, ModelMap model){
        model.addAttribute("cat",categoryService.findById(catID));
        return "category/cat_edit";
    }


    /**
     * 修改栏目
     * @param cat
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit/{catID}",method = RequestMethod.POST)
    public String doEditCategory(@PathVariable("catID") String catID, Category cat,
                                 ModelMap model,
                                 RedirectAttributes redirectAttributes){
        if(null==cat||StringUtils.isEmpty(cat.getCatName())||StringUtils.isEmpty(cat.getCatDir())){
            model.addAttribute("messageErr","栏目名称和目录名不能为空");
            model.addAttribute("cat",cat);
            return "category/cat_edit";
        }

        cat.setUpdateAt(new Date());
        Category savedCat=categoryService.updateById(cat,catID);
        if(null!=savedCat&&savedCat.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","更新栏目成功");
            return "redirect:/cat/list";
        }else{
            redirectAttributes.addFlashAttribute("messageErr","栏目更新失败");
            model.addAttribute("cat",cat);
            return "category/cat_edit";
        }
    }

}
