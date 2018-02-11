package com.tangzq.controller;

import com.tangzq.model.Topic;
import com.tangzq.model.User;
import com.tangzq.service.CategoryService;
import com.tangzq.service.TopicService;
import com.tangzq.utils.CommonProps;
import com.tangzq.vo.PageVo;
import com.tangzq.vo.TopicVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/topic")
public class TopicController {


    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 帖子列表
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String listTopics(PageVo pageVo, ModelMap model, HttpServletRequest request){
        model.addAttribute("pager", topicService.findByUsernameAndPage(
                ((User)(WebUtils.getSessionAttribute(request, CommonProps.LOGIN_USER_SESSION_KEY))).getUsername(),
                 pageVo.getPageNO(),
                 pageVo.getPageSize()));
        return "topic/topic_list";
    }

    /**
     * 新增帖子表单
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String createTopic(ModelMap model){
        model.addAttribute("catList",categoryService.findAll());
        return "topic/topic_add";
    }


    /**
     * 新增帖子
     * @param vo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String doCreateTopic(TopicVo vo,
                                   ModelMap model,
                                   RedirectAttributes redirectAttributes){
        if(null==vo|| StringUtils.isEmpty(vo.getCatId())
                ||StringUtils.isEmpty(vo.getTitle())
                ||StringUtils.isEmpty(vo.getContentMD())){
            model.addAttribute("messageErr","帖子栏目，标题，内容不能为空");
            model.addAttribute("catList",categoryService.findAll());
            model.addAttribute("topicVo",vo);
            return "topic/topic_add";
        }

        Topic savedTopic=topicService.addTopic(vo);
        if(null!=savedTopic&&savedTopic.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","帖子创建成功");
            return "redirect:/topic/list";
        }else{
            redirectAttributes.addFlashAttribute("messageErr","帖子创建失败");
            model.addAttribute("catList",categoryService.findAll());
            model.addAttribute("topicVo",vo);
            return "topic/topic_add";
        }
        
    }


    /**
     * 修改帖子表单
     * @param topicID
     * @param model
     * @return
     */
    @RequestMapping(value="/edit/{topicID}",method = RequestMethod.GET)
    public String editTopic(@PathVariable("topicID") String topicID, ModelMap model){
        model.addAttribute("topicVo",topicService.findTopicVoById(topicID));
        model.addAttribute("catList",categoryService.findAll());
        return "topic/topic_edit";
    }


    /**
     * 修改帖子
     * @param vo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit/{topicID}",method = RequestMethod.POST)
    public String doEditCategory(@PathVariable("topicID") String topicID, TopicVo vo,
                                 ModelMap model,
                                 RedirectAttributes redirectAttributes){

        if(null==vo|| StringUtils.isEmpty(vo.getCatId())
                ||StringUtils.isEmpty(vo.getTitle())
                ||StringUtils.isEmpty(vo.getContentMD())){
            model.addAttribute("messageErr","帖子栏目，标题，内容不能为空");
            model.addAttribute("topicVo",vo);
            return "topic/topic_edit";
        }

        Topic savedTopic=topicService.updateById(vo,topicID);
        if(null!=savedTopic&&savedTopic.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","帖子更新成功");
            return "redirect:/topic/edit/"+savedTopic.getId();
        }else{
            redirectAttributes.addFlashAttribute("messageErr","帖子更新失败");
            model.addAttribute("topicVo",vo);
            return "topic/topic_edit";
        }
    }



    /**
     * 删除帖子
     * @param topicID
     * @return
     */
    @RequestMapping(value="/del/{topicID}",method = RequestMethod.GET)
    public String delTopic(@PathVariable("topicID") String topicID, RedirectAttributes redirectAttributes){
        topicService.deleteById(topicID);
        redirectAttributes.addFlashAttribute("messageSuc","帖子删除成功");
        return "redirect:/topic/list";
    }

}
