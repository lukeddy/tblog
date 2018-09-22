package com.tangzq.controller;

import com.tangzq.model.Comment;
import com.tangzq.model.Topic;
import com.tangzq.model.User;
import com.tangzq.model.type.CommentType;
import com.tangzq.service.CategoryService;
import com.tangzq.service.CommentService;
import com.tangzq.service.TopicService;
import com.tangzq.utils.Constants;
import com.tangzq.vo.CommentVo;
import com.tangzq.vo.PageVo;
import com.tangzq.vo.TopicVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Autowired
    private CommentService commentService;

    /**
     * 帖子列表
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String listTopics(PageVo pageVo, ModelMap model, HttpServletRequest request){
        model.addAttribute("pager", topicService.findByUsernameAndPage(
                ((User)(WebUtils.getSessionAttribute(request, Constants.LOGIN_USER_SESSION_KEY))).getUsername(),
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
        model.addAttribute("topicVo",new TopicVo());
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


    @RequestMapping(value="/{topicID}")
    public String showTopic(@PathVariable("topicID")String topicID, ModelMap model){
        topicService.increaseVisitCount(topicID);
        model.addAttribute("topic",topicService.findTopicById(topicID));
        model.addAttribute("commentList", commentService.getAllItemComments(topicID));
        return "topic/show";
    }

    @RequestMapping(value = "/comment/add",method = RequestMethod.POST)
    public String addReply(@RequestParam("add-comment-markdown-doc")String commentMD,
                           @RequestParam("add-comment-html-code")String commentHTML,
                           CommentVo vo,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){

        if(ObjectUtils.isEmpty(session.getAttribute(Constants.LOGIN_USER_SESSION_KEY))){
            redirectAttributes.addFlashAttribute("messageErr","登录了才能评论，请先登录!");
            return "redirect:/topic/"+vo.getItemId()+"#comment-form";
        }

        if( StringUtils.isEmpty(vo.getVcode())){
            redirectAttributes.addFlashAttribute("messageErr","评论验证码不能为空!");
            redirectAttributes.addFlashAttribute("commentMD",commentMD);
            return "redirect:/topic/"+vo.getItemId()+"#comment-form";
        }

        String commentVCodeInSession = (String) session.getAttribute(Constants.VCODE_SESSION_KEY);
        String submitCode = vo.getVcode();
        if (!StringUtils.equals(commentVCodeInSession,submitCode)) {
            redirectAttributes.addFlashAttribute("messageErr","评论验证码错误!");
            redirectAttributes.addFlashAttribute("commentMD",commentMD);
            return "redirect:/topic/"+vo.getItemId()+"#comment-form";
        }

        if(null==vo|| StringUtils.isEmpty(commentMD)||StringUtils.isEmpty(commentHTML)){
            redirectAttributes.addFlashAttribute("messageErr","评论内容不能为空!");
            return "redirect:/topic/"+vo.getItemId();
        }
        vo.setCommentMD(commentMD);
        vo.setCommentHTML(commentHTML);
        Comment comment= commentService.addComment(vo, CommentType.ARTICLE);
        if(null!=comment&&comment.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","评论成功！");
            return "redirect:/topic/"+vo.getItemId()+"#"+comment.getId();
        }else{
            redirectAttributes.addFlashAttribute("messageErr","评论添加失败！");
            redirectAttributes.addFlashAttribute("commentMD",commentMD);
            return "redirect:/topic/"+vo.getItemId();
        }
    }

    @RequestMapping(value="/comment/replyTo",method = RequestMethod.POST)
    public String replyTo(@RequestParam("commentId")String commentId,
                          @RequestParam("topicID")String topicID,
                          @RequestParam("reply-content-markdown-doc")String replyContentMD,
                          @RequestParam("reply-content-html-code")String replyContentHTML,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) throws Exception {
        if(StringUtils.isEmpty(replyContentMD)&&StringUtils.isEmpty(replyContentHTML)){
            redirectAttributes.addFlashAttribute("messageErr","评论内容不能为空！");
            return "redirect:/topic/"+topicID+"#"+commentId;
        }
        Comment commentInDb= commentService.getComment(commentId);
        if(null==commentInDb){
            redirectAttributes.addFlashAttribute("messageErr","您要回复的评论内容已经不存在！");
            return "redirect:/topic/"+topicID+"#"+commentId;
        }

        Topic topic=topicService.findTopicById(topicID);
        if(null==topic){
            throw new Exception("文章已经不存在");
        }
        User loginUser=(User) session.getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        CommentVo replyContent=new CommentVo();
        replyContent.setItemId(topicID);
        replyContent.setCommentMD(replyContentMD);
        replyContent.setCommentHTML(replyContentHTML);
        replyContent.setAuthorId(loginUser.getId());

        Comment updatedComment= commentService.replyComment(commentId,replyContent,CommentType.ARTICLE);
        if(null==updatedComment||updatedComment.getId()==null){
            redirectAttributes.addFlashAttribute("messageErr","回复评论失败, 请稍后再试");
            return "redirect:/topic/"+topicID+"#"+commentId;
        }else{
            redirectAttributes.addFlashAttribute("messageSuc","回复评论成功！");
            return "redirect:/topic/"+topicID+"#"+commentId;
        }
    }

    /**
     * 删除评论
     * @param topicID
     * @param commentId
     * @param session
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/{topicID}/comment/del/{commentId}")
    public String deleteComment(
            @PathVariable("topicID")String topicID,
            @PathVariable("commentId")String commentId,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws Exception {

        User loginUser=(User) session.getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        if(null==loginUser){
            return "redirect:/login";
        }

        if(StringUtils.isEmpty(topicID)||StringUtils.isEmpty(commentId)){
            throw new Exception("参数异常");
        }
        Topic topic=topicService.findTopicById(topicID);
        if(null==topic){
            throw new Exception("文章已经不存在");
        }
        Comment comment= commentService.getComment(commentId);
        if(null==comment){
            throw new Exception("评论已经不存在");
        }

        User author=comment.getAuthor();
        if(!StringUtils.equalsIgnoreCase(author.getId(),loginUser.getId())){
            throw new Exception("无权删除评论");
        }

        commentService.deleteComment(commentId);
        redirectAttributes.addFlashAttribute("messageSuc","评论删除成功！");
        return "redirect:/topic/"+topicID+"#comment-form";
    }

    /**
     * 举报评论
     * @param topicID
     * @param commentId
     * @param session
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/{topicID}/comment/ban/{commentId}")
    public String banComment(
            @PathVariable("topicID")String topicID,
            @PathVariable("commentId")String commentId,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws Exception {

        User loginUser=(User) session.getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        if(null==loginUser){
            return "redirect:/login";
        }

        if(StringUtils.isEmpty(topicID)||StringUtils.isEmpty(commentId)){
            throw new Exception("参数异常");
        }
        Topic topic=topicService.findTopicById(topicID);
        if(null==topic){
            throw new Exception("文章已经不存在");
        }
        Comment comment= commentService.getComment(commentId);
        if(null==comment){
            throw new Exception("评论已经不存在");
        }

        commentService.updateRepotStatus(commentId, Boolean.TRUE);
        redirectAttributes.addFlashAttribute("messageSuc","评论举报成功！");
        return "redirect:/topic/"+topicID+"#comment-form";
    }

    /**
     * 点赞评论
     * @param topicID
     * @param commentId
     * @param session
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/{topicID}/comment/thumbsup/{commentId}")
    public String thumbsupComment(
            @PathVariable("topicID")String topicID,
            @PathVariable("commentId")String commentId,
            HttpSession session,
            RedirectAttributes redirectAttributes) throws Exception {

        User loginUser=(User) session.getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        if(null==loginUser){
            return "redirect:/login";
        }

        if(StringUtils.isEmpty(topicID)||StringUtils.isEmpty(commentId)){
            throw new Exception("参数异常");
        }
        Topic topic=topicService.findTopicById(topicID);
        if(null==topic){
            throw new Exception("文章已经不存在");
        }
        Comment comment= commentService.getComment(commentId);
        if(null==comment){
            throw new Exception("评论已经不存在");
        }

        commentService.updateThumbsUpData(commentId, loginUser);
        redirectAttributes.addFlashAttribute("messageSuc","更新点赞数据成功！");
        return "redirect:/topic/"+topicID+"#comment-form";
    }
}
