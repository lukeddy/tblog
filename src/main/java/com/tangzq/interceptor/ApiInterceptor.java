package com.tangzq.interceptor;

import com.google.gson.Gson;
import com.tangzq.response.Result;
import com.tangzq.service.TokenService;
import com.tangzq.service.UserService;
import com.tangzq.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.io.IOException;

/**
 * API拦截器
 * @author luke
 */
@Component
public class ApiInterceptor extends WebContentInterceptor {

    private final Logger log = LoggerFactory.getLogger(ApiInterceptor.class);

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
                             HttpServletResponse response, Object handler)  {

        //允许浏览器的第一次跨域OPTIONS请求
        if (OPTIONS_HEADER.equalsIgnoreCase(request.getMethod())) {
            try {
                response.sendError(HttpServletResponse.SC_OK, "success");
            } catch (IOException e) {
                log.error("跳转异常：",e);
            }
            return true;
         }

        //拦截器也需要允许跨域的配置，不然跨域请求会拿不到响应结果
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        String token = request.getHeader("Authorization");

        if (token == null || !tokenService.isTokenValid(token)) {
            Result result=Result.fail("没有权限");
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,gson.toJson(result));
            } catch (IOException e) {
                log.error("跳转异常：",e);
            }
            return false;
        } else {
            //TODO 如果这里每次请求都进行查询的话会造成数据库压力，这种方式有待改善
            String uid=tokenService.getUserIdFromToken(token);
            if(userService.getUser(uid)==null){
                Result result=Result.fail("用户不存在");
                String json = new Gson().toJson(result);
                try {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND,json);
                } catch (IOException e) {
                    log.error("跳转异常：",e);
                }
                return false;
            }
            request.setAttribute(Constants.API_LOGIN_USER_ID_KEY,uid);
            return true;
        }
    }

}