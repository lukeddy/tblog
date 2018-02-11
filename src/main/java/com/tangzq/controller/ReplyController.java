package com.tangzq.controller;

import com.tangzq.model.Reply;
import com.tangzq.service.ReplyService;
import com.tangzq.vo.ReplyVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/add/{topicID}",method = RequestMethod.POST)
    public String addReply(@PathVariable("topicID")String topicID, ReplyVo vo,
                           RedirectAttributes redirectAttributes){
        if(null==vo|| StringUtils.isEmpty(vo.getContentMD())){
            redirectAttributes.addFlashAttribute("messageSuc","评论内容不能为空!");
            return "redirect:/article/"+topicID;
        }
        Reply reply=replyService.addReply(vo);
        if(null!=reply&&reply.getId()!=null){
            return "redirect:/article/"+topicID+"#"+reply.getId();
        }else{
            redirectAttributes.addFlashAttribute("messageSuc","评论添加失败！");
            return "redirect:/article/"+topicID;
        }
    }

}
