package com.tangzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文章控制器
 */
@Controller
public class ArticleController {



    /**
     * 文章页面
     *
     * @return
     */
    @RequestMapping(value = "/article/{aid}",method = RequestMethod.GET)
    public String index(@PathVariable("aid") String aid,ModelMap model) {
        //TODO 获取当前文章信息
        System.out.println(aid);
        model.addAttribute("atitle","我是文章标题");
        return "article";
    }


}
