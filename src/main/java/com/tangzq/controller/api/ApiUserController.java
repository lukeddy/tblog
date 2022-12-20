package com.tangzq.controller.api;

import com.tangzq.response.Result;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import com.tangzq.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "获取用户信息API", description = "用户信息接口")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Operation(summary="用户信息", description="获取登陆用户信息")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Result getUserInfo(HttpServletRequest request) {
        UserInfoVo userInfo=userService.getUserInfo((String)request.getAttribute(Constants.API_LOGIN_USER_ID_KEY));
        if(null==userInfo){
            return Result.fail("用户不存在");
        }
        return Result.ok("成功",userInfo);
    }
}
