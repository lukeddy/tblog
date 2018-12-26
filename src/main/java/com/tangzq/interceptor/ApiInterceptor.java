package com.tangzq.interceptor;

import com.google.gson.Gson;
import com.tangzq.response.Result;
import com.tangzq.service.TokenService;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * API拦截器
 * @author tangzhiqiang
 */
@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * 拦截所有API请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        //允许这个OPTIONS请求通过,通过后就发现前端同一个请求发送了两次,
        //在第二条请求里你可以发现你的Authorization已经显示你面前了
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
            return true;
        }

        if (token == null || !tokenService.isTokenValid(token)) {
            Result result=Result.fail("没有权限");
            String json = new Gson().toJson(result);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,json);
            return false;
        } else {
            String uid=tokenService.getUserIdFromToken(token);
            if(userService.getUser(uid)==null){
                Result result=Result.fail("用户不存在");
                String json = new Gson().toJson(result);
                response.sendError(HttpServletResponse.SC_NOT_FOUND,json);
                return false;
            }
            request.setAttribute(Constants.API_LOGIN_USER_ID_KEY,uid);
            return true;
        }
    }

}