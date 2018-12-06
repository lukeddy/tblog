package com.tangzq.interceptor;

import com.tangzq.service.TokenService;
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

        if (token == null || !tokenService.isTokenValid(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        } else {
            ObjectId userId = new ObjectId(tokenService.getUserIdFromToken(token));
            request.setAttribute("userId", userId);
            return true;
        }
    }

}