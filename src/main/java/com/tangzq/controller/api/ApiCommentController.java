package com.tangzq.controller.api;

import com.tangzq.dto.ReplyDto;
import com.tangzq.model.Comment;
import com.tangzq.model.User;
import com.tangzq.model.embed.ReportInfo;
import com.tangzq.model.type.CommentType;
import com.tangzq.response.Result;
import com.tangzq.service.CommentService;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import com.tangzq.vo.CommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 评论接口
 */
@RestController
@RequestMapping("/api/comment")
@Tag(name = "评论API", description = "博客评论接口" )
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Operation(summary="获取帖子相关的评论", description="根据url的id来获取帖子的所有评论")
    @Parameter(name = "topicId", description = "帖子ID", required = true, in = ParameterIn.PATH)
    @RequestMapping(value="/public/{topicId}", method=RequestMethod.GET)
    public Result detail(@PathVariable String topicId) {
        return Result.ok("成功",commentService.getAllItemComments(topicId));
    }

    @Operation(summary="添加评论内容", description="增加评论信息")
    @Parameter(name = "topicId", description = "评论内容", required = true, in = ParameterIn.PATH)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result addReply(@RequestBody CommentVo vo, HttpServletRequest request){

        User loginUser=userService.getUser((String)request.getAttribute(Constants.API_LOGIN_USER_ID_KEY));
        if(null==loginUser){
            return Result.fail("请先登录");
        }

        if(null==vo|| StringUtils.isEmpty(vo.getCommentMD())||StringUtils.isEmpty(vo.getCommentHTML())){
            return Result.fail("评论内容不能为空");
        }

        vo.setAuthorId(loginUser.getId());
        Comment comment= commentService.addComment(vo, CommentType.ARTICLE);

        if(null!=comment&&comment.getId()!=null){
           return Result.ok("评论成功");
        }else{
           return Result.fail("评论添加失败");
        }
    }

    @Operation(summary="评论点赞", description="评论点赞，每个用户只能点赞一次")
    @Parameter(name = "commentId", description = "评论ID", required = true)
    @RequestMapping(value="/thumbsup/{commentId}",method = RequestMethod.PUT)
    public Result thumbsupComment(
            @PathVariable("commentId")String commentId, HttpServletRequest request){

        User loginUser=userService.getUser((String)request.getAttribute(Constants.API_LOGIN_USER_ID_KEY));
        if(null==loginUser){
            return Result.fail("用户还未登陆");
        }

        Comment comment= commentService.getComment(commentId);
        if(null==comment){
            Result.fail("评论不存在");
        }

        commentService.updateThumbsUpData(commentId, loginUser);
        return Result.ok("点赞成功");
    }

    @Operation(summary="删除评论", description="根据url的id来指定删除评论")
    @Parameter(name = "commentId", description = "评论ID", required = true)
    @RequestMapping(value="/{commentId}", method=RequestMethod.DELETE)
    public Result deleteTopic(@PathVariable String commentId) {
        Comment comment= commentService.getComment(commentId);
        if(null==comment){
            Result.fail("评论不存在");
        }
        commentService.deleteComment(commentId);
        return Result.ok("删除评论成功");
    }


    @Operation(summary="回复评论", description="回复指定的评论")
    @RequestMapping(value="/reply",method = RequestMethod.POST)
    public Result reply(@RequestBody ReplyDto dto,
                          HttpServletRequest request){

        User loginUser=userService.getUser((String)request.getAttribute(Constants.API_LOGIN_USER_ID_KEY));
        if(null==loginUser){
            return Result.fail("请先登录");
        }

        Comment comment= commentService.getComment(dto.getCommentId());
        if(null==comment){
            return Result.fail("您要回复的评论内容已经不存在");
        }

        if(StringUtils.isEmpty(dto.getReplyMD()) && StringUtils.isEmpty(dto.getReplyHtml())){
            return Result.fail("回复内容不能为空");
        }

        CommentVo replyContent=new CommentVo();
        replyContent.setItemId(comment.getItemId());
        replyContent.setCommentMD(dto.getReplyMD());
        replyContent.setCommentHTML(dto.getReplyHtml());
        replyContent.setAuthorId(loginUser.getId());

        Comment reply= commentService.replyComment(dto.getCommentId(),replyContent,CommentType.ARTICLE);
        if(null==reply||reply.getId()==null){
            return Result.fail("回复评论失败, 请稍后再试");
        }else{
            return Result.ok("回复成功");
        }
    }


    @Operation(summary="举报评论", description="举报违规的评论")
    @RequestMapping(value="/ban/{commentId}",method = RequestMethod.PUT)
    public Result banComment(@PathVariable("commentId") String commentId,
            @RequestBody ReportInfo reportInfo,
            HttpServletRequest request){

        User loginUser=userService.getUser((String)request.getAttribute(Constants.API_LOGIN_USER_ID_KEY));
        if(null==loginUser){
            return Result.fail("您还未登陆");
        }
        Comment comment=commentService.getComment(commentId);
        if(null==comment){
            return Result.fail("评论已经不存在");
        }

        Comment banedComment= commentService.reportComment(commentId,loginUser,reportInfo);
        if(null==banedComment){
            return Result.fail("举报评论失败");
        }
        return Result.ok("举报成功！");
    }
}
