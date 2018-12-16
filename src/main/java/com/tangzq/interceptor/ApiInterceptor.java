package com.tangzq.interceptor;

import com.tangzq.service.TokenService;
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
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        } else {
            request.setAttribute(Constants.API_LOGIN_USER_ID_KEY, tokenService.getUserIdFromToken(token));
            return true;
        }
    }

}