package com.tangzq.interceptor;

import com.tangzq.model.User;
import com.tangzq.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.io.IOException;

/**
 * 登陆拦截器
 * @author tangzhiqiang
 */
@Component
public class LoginInterceptor extends WebContentInterceptor {
    private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 拦截所有请求，并验证是否登陆
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)  {

        User user =  (User) request.getSession().getAttribute(Constants.LOGIN_USER_SESSION_KEY);
        if(user == null){
            log.info("用户没有登陆：将跳转到login页面！");
            try {
                response.sendRedirect(request.getContextPath()+"/login");
            } catch (IOException e) {
                log.error("跳转异常：",e);
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}