package com.tangzq.interceptor;

import com.tangzq.common.ConfigTools;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取Git构建信息拦截器，方便跟踪
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("gitCommitMessage", ConfigTools.getConfig("git.commit.message.short"));
        request.setAttribute("gitBranch",ConfigTools.getConfig("git.branch"));
        request.setAttribute("gitCommitId",ConfigTools.getConfig("git.commit.id"));
        request.setAttribute("gitTagName", ConfigTools.getConfig("git.closest.tag.name"));
        request.setAttribute("gitBuildTime",ConfigTools.getConfig("git.build.time"));
    }
}
