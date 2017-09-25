package com.tangzq.controller;

import com.tangzq.model.User;
import com.tangzq.service.UserService;
import com.tangzq.utils.UploadUtil;
import com.tangzq.vo.AvatarVo;
import com.tangzq.vo.UserPwdVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {


    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户主页
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping(value = "/{uid}",method = RequestMethod.GET)
    public String index(@PathVariable("uid") String uid,ModelMap model) {
        model.addAttribute("user",userService.getUser(uid));
        return "user/user_main";
    }


    /**
     * 跳转到修改密码页面
     * @param model
     * @return
     */
   @RequestMapping(value = "/changePwd",method = RequestMethod.GET)
   public  String changePwd(ModelMap model){
        model.addAttribute("changePwdForm",new UserPwdVo());
        return "user/user_pwd";
   }


   @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    public String doChangePwd(@Valid @ModelAttribute("changePwdForm")UserPwdVo vo,BindingResult result,
                              ModelMap model,
                              RedirectAttributes redirectAttributes){

       if(result.hasErrors()){
           return "user/user_pwd";
       }
       if(!vo.getNewPwd().equals(vo.getRepeatNewPwd())){
           result.rejectValue("repeatNewPwd",null,"两次输入密码不一致");
           return "user/user_pwd";
       }
       if(userService.isUserValid(vo.getUsername(),vo.getOldPwd())){
           User updatedUser=userService.updatePwd(vo.getUid(),vo.getNewPwd());
           if(null!=updatedUser&&updatedUser.getId()!=null){
               redirectAttributes.addFlashAttribute("messageSuc","密码修改成功");
               return "redirect:/user/changePwd";
           }else{
               model.addAttribute("messageErr","密码修改失败");
               return "user/user_pwd";
           }
       }else{
           model.addAttribute("messageErr","原始密码错误");
           return "user/user_pwd";
       }
   }


    /**
     * 修改头像页面
     * @return
     */
    @RequestMapping(value = "/changeAvatar",method = RequestMethod.GET)
    public  String changeAvatar(){
        return "user/user_avatar";
    }

    @RequestMapping(value="/changeAvatar",method = RequestMethod.POST)
    public String doChangeAvatar(@RequestParam("file")MultipartFile file,
                                 @RequestParam("uid")String uid,
                                 ModelMap model,
                                 RedirectAttributes redirectAttributes){

        if(null==file||file.isEmpty()){
            model.addAttribute("messageErr","头像文件不能为空！");
            return "user/user_avatar";
        }

        try {
            String rootPath= ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(".");
            String relativePath = UploadUtil.getRelativeAvatarPath(file.getOriginalFilename());
            String absolutePath = UploadUtil.uploadImage(rootPath,relativePath, file.getInputStream());
            logger.info("头像保存成功，全路径为："+absolutePath);
            User user=userService.updateAvatar(uid,relativePath);
            if(null!=user&&user.getId()!=null){
                redirectAttributes.addFlashAttribute("messageSuc","头像修改成功");
                return "redirect:/user/changeAvatar";
            }else{
                model.addAttribute("messageErr","头像修改失败");
                return "user/user_avatar";
            }
        } catch (IOException e) {
            logger.error("change avatar error",e);
            model.addAttribute("messageErr","修改头像时出错了");
            return "user/user_avatar";
        }
    }

}
