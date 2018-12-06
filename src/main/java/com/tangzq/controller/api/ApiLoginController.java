package com.tangzq.controller.api;

import com.tangzq.model.User;
import com.tangzq.service.TokenService;
import com.tangzq.service.UserService;
import com.tangzq.vo.LoginUserVo;
import com.tangzq.vo.RegisterUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<String> login(@RequestBody LoginUserVo user){

        if(!userService.isUserValid(user.getUsername(),user.getPassword())){
            return new ResponseEntity<>("用户名或者密码错误", HttpStatus.BAD_REQUEST);
        }

        String token=tokenService.createToken(userService.findUser(user.getUsername(),user.getPassword()).getId());
        return new ResponseEntity<>(token,HttpStatus.OK);
    }


    @ApiOperation(value="注册", notes="用户注册")
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseEntity<String> doRegister(@RequestBody RegisterUserVo registerUser){

        if(null!=userService.findByUsername(registerUser.getUsername())){
            return new ResponseEntity<>("该用户名已经存在", HttpStatus.BAD_REQUEST);
        }
        if(null!=userService.findUserByEmail(registerUser.getEmail())){
            return new ResponseEntity<>("该邮箱已经被注册", HttpStatus.BAD_REQUEST);
        }

        User savedUser=userService.createUser(registerUser);
        if(null!=savedUser&&savedUser.getId()!=null){
            return new ResponseEntity<>("注册成功", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("注册失败", HttpStatus.BAD_REQUEST);
        }
    }

}
