package com.tangzq.controller;

import com.tangzq.model.User;
import com.tangzq.service.TopicService;
import com.tangzq.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 收藏控制器
 * @author luke
 */
@Controller
@RequestMapping("/like")
public class LikeController {


    private final Logger logger = LoggerFactory.getLogger(LikeController.class);

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
        User user =  (User) request.getSession().getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        if(user == null){
            logger.info("用户没登陆：将跳转到login页面！");
            return "redirect:/login";
        }
        topicService.like(topicId,user.getId());
        redirectAttributes.addFlashAttribute("messageSuc","Like成功！");
        return "redirect:/topic/"+topicId+"#collectLink";
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
        User user =  (User) request.getSession().getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        if(user == null){
            logger.info("用户没有登陆：将跳转到login页面！");
            return "redirect:/login";
        }
        topicService.unLike(topicId,user.getId());
        redirectAttributes.addFlashAttribute("messageSuc","UNLIKE成功！");
        return "redirect:/topic/"+topicId+"#collectLink";
    }

}
