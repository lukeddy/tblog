package com.tangzq.controller.api;

import com.tangzq.model.User;
import com.tangzq.response.Result;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import com.tangzq.utils.ValidateCode;
import com.tangzq.vo.LoginUserVo;
import com.tangzq.vo.RegisterUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * API登陆控制器
 * @author tangzhiqiang
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "注册登录API", description = "博客登录、注册接口",tags = "Login")
public class ApiLoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="验证码", notes="生成验证码")
    @RequestMapping(value = "/validateCode",method = RequestMethod.GET)
    public void validateCode(@ApiIgnore HttpServletResponse response, @ApiIgnore HttpSession session) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIXED, 4, null);
        log.info(verifyCode);
        session.setAttribute(Constants.VCODE_SESSION_KEY, verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }

    @ApiOperation(value="登录", notes="账号登陆")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@Valid @ModelAttribute("loginForm")LoginUserVo user, BindingResult result,
                        @ApiIgnore HttpSession session){

        if(result.hasErrors()){
            Result.fail(getErrorMessages(result));
        }

        String vcodeInSession = (String) session.getAttribute(Constants.VCODE_SESSION_KEY);
        String submitCode = user.getValidateCode();

        if (!StringUtils.equals(vcodeInSession,submitCode)) {
            return Result.fail("验证码错误");
        }

        if(!userService.isUserValid(user.getUsername(),user.getPassword())){
            return Result.fail("用户名或者密码错误");
        }

        session.setAttribute(Constants.LOGIN_USER_SESSION_KEY,userService.findUser(user.getUsername(),user.getPassword()));
        return Result.ok("登录成功！");
    }




    @ApiOperation(value="注册", notes="用户注册")
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result doRegister(@Valid @ModelAttribute("registerForm") RegisterUserVo registerUser,
                             BindingResult result,
                             @ApiIgnore HttpSession session){

        if(result.hasErrors()){
            return Result.fail(getErrorMessages(result));
        }

        String vcodeInSession = (String) session.getAttribute(Constants.VCODE_SESSION_KEY);
        String submitCode = registerUser.getValidateCode();
        if (!StringUtils.equals(vcodeInSession,submitCode)) {
            return Result.fail("验证码错误！");
        }
        if(null!=userService.findByUsername(registerUser.getUsername())){
            return Result.fail("该用户名已经存在！");
        }
        if(null!=userService.findUserByEmail(registerUser.getEmail())){
            return Result.fail("该邮箱已经被注册！");
        }

        User savedUser=userService.createUser(registerUser);
        if(null!=savedUser&&savedUser.getId()!=null){
            return Result.ok("注册成功！");
        }else{
            return Result.ok("注册失败！");
        }
    }

    private List<String> getErrorMessages(BindingResult result){
        List<FieldError> errors = result.getFieldErrors();
        List<String> errorStrs=new ArrayList<>();
        for (FieldError error : errors ) {
            if(StringUtils.isNotEmpty(error.getDefaultMessage())){
                errorStrs.add(error.getDefaultMessage());
            }
        }
        return errorStrs;
    }

    @ApiOperation(value="退出", notes="登出系统")
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public Result logout(@ApiIgnore HttpSession session){
        session.invalidate();
        return Result.ok("退出成功！");
    }
}
