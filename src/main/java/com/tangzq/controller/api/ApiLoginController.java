package com.tangzq.controller.api;

import com.tangzq.model.User;
import com.tangzq.response.Result;
import com.tangzq.service.TokenService;
import com.tangzq.service.UserService;
import com.tangzq.vo.LoginUserVo;
import com.tangzq.vo.RegisterUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * API登陆控制器
 * @author luke
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "注册登录API", description = "博客登录、注册接口")
public class ApiLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @Operation(summary="登录", description="账号登陆")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@RequestBody LoginUserVo userVo){

        if(!userService.isUserValid(userVo.getUsername(),userVo.getPassword())){
            return Result.fail("用户名或者密码错误");
        }

        User user=userService.findUser(userVo.getUsername(),userVo.getPassword());
        String token=tokenService.createToken(user.getId());
        Map<String,Object> data=new HashMap<>(2);
        data.put("token",token);
        data.put("userInfo",userService.convert2UserInfo(user));
        return Result.ok("成功", data);
    }

    @Operation(summary="注册", description="用户注册")
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public Result doRegister(@RequestBody RegisterUserVo registerUser){

        if(null!=userService.findByUsername(registerUser.getUsername())){
            return Result.fail("该用户名已经存在");
        }
        if(null!=userService.findUserByEmail(registerUser.getEmail())){
            return Result.fail("该邮箱已经被注册");
        }

        User savedUser=userService.createUser(registerUser);
        if(null!=savedUser&&savedUser.getId()!=null){
            return Result.ok("注册成功");
        }else{
            return Result.fail("注册失败");
        }
    }

    @Operation(summary="退出", description="登出系统")
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public Result logout(){
        //TODO 使得Token失效
        return Result.ok("退出成功");
    }

}
