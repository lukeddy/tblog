package com.tangzq.controller;

import com.tangzq.service.TopicService;
import com.tangzq.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangzhiqiang
 */
@Controller
public class TagController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value="/tag/{tagName}")
    public String showArticle(PageVo pageVo, @PathVariable("tagName")String tagName, ModelMap model){
        model.addAttribute("pager",
                topicService.findByTagAndPage(tagName,pageVo.getPageNO(),pageVo.getPageSize()));
        model.addAttribute("tagName",tagName);
        return "article/tag_list";

    }

}
