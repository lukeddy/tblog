package com.tangzq.controller.api;

import com.tangzq.model.User;
import com.tangzq.response.Result;
import com.tangzq.service.TokenService;
import com.tangzq.service.UserService;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private TokenService tokenService;


    @ApiOperation(value="登录", notes="账号登陆")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@Valid @ModelAttribute("loginForm")LoginUserVo user, BindingResult result){

        if(result.hasErrors()){
            Result.fail(getErrorMessages(result));
        }

        if(!userService.isUserValid(user.getUsername(),user.getPassword())){
            return Result.fail("用户名或者密码错误");
        }

        HashMap data=new HashMap(1);
        data.put("token",tokenService.createToken(userService.findUser(user.getUsername(),user.getPassword()).getId()));
        return Result.ok(data);
    }




    @ApiOperation(value="注册", notes="用户注册")
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result doRegister(@Valid @ModelAttribute("registerForm") RegisterUserVo registerUser,
                             BindingResult result){

        if(result.hasErrors()){
            return Result.fail(getErrorMessages(result));
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
}
