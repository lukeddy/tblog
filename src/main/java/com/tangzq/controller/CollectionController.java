package com.tangzq.controller;

import com.tangzq.model.User;
import com.tangzq.service.TopicService;
import com.tangzq.utils.CommonProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/collect")
public class CollectionController {


    private final Logger logger = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    private TopicService topicService;

    /**
     * 收藏帖子
     * @param topicId
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "/add/{topicId}",method = RequestMethod.GET)
    public String collect(@PathVariable("topicId") String topicId,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {
        User user =  (User) request.getSession().getAttribute(CommonProps.LOGIN_USER_SESSION_KEY);
        if(user == null){
            logger.info("用户没登陆：将跳转到login页面！");
            return "redirect:/article/"+topicId;
        }
        topicService.addCollection(topicId,user.getId());
        redirectAttributes.addFlashAttribute("messageSuc","收藏成功！");
        return "redirect:/article/"+topicId+"#collectLink";
    }

    /**
     * 删除收藏帖子
     * @param topicId
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "/remove/{topicId}",method = RequestMethod.GET)
    public String removeCollect(@PathVariable("topicId") String topicId,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {
        User user =  (User) request.getSession().getAttribute(CommonProps.LOGIN_USER_SESSION_KEY);
        if(user == null){
            logger.info("用户没有登陆：将跳转到login页面！");
            return "redirect:/article/"+topicId;
        }
        topicService.removeCollection(topicId,user.getId());
        redirectAttributes.addFlashAttribute("messageSuc","取消收藏成功！");
        return "redirect:/article/"+topicId+"#collectLink";
    }

}
