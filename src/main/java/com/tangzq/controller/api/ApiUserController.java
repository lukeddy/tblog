package com.tangzq.controller.api;

import com.tangzq.response.Result;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import com.tangzq.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户API
 * @author luke
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "获取用户信息API", description = "用户信息接口",tags = "User")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户信息", notes="获取登陆用户信息")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Result getUserInfo(HttpServletRequest request) {
        UserInfoVo userInfo=userService.getUserInfo((String)request.getAttribute(Constants.API_LOGIN_USER_ID_KEY));
        if(null==userInfo){
            return Result.fail("用户不存在");
        }
        return Result.ok("成功",userInfo);
    }
}
