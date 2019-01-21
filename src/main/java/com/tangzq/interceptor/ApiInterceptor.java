package com.tangzq.interceptor;

import com.google.gson.Gson;
import com.tangzq.response.Result;
import com.tangzq.service.TokenService;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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

    @Autowired
    private Gson gson;

    private static final String OPTIONS_HEADER="OPTIONS";

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

        //允许这个OPTIONS请求通过,通过后就发现前端同一个请求发送了两次,
        //在第二条请求里你可以发现你的Authorization已经显示你面前了
        if (OPTIONS_HEADER.equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_OK, "success");
           return true;
         }

        String token = request.getHeader("Authorization");

        if (token == null || !tokenService.isTokenValid(token)) {
            Result result=Result.fail("没有权限");
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,json);

            //TODO 查找为什么始终无法返回JSON数据
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = response.getWriter();
            writer.print(gson.toJson(result));
            writer.close();
            response.flushBuffer();

            return false;
        } else {
            //TODO 如果这里每次请求都进行查询的话会造成数据库压力，这种方式有待改善
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