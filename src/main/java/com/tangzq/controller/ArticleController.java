package com.tangzq.controller;

import com.tangzq.service.ArticleService;
import com.tangzq.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TopicService topicService;

    @RequestMapping(value="/article/{topicID}")
    public String showArticle(@PathVariable("topicID")String topicID, ModelMap model){
        topicService.increaseVisitCount(topicID);
        model.addAttribute("article",articleService.findArticleVo(topicID));
        return "article/show";
    }
}
