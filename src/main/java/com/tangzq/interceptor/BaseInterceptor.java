package com.tangzq.interceptor;

import com.tangzq.common.ConfigTools;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取Git构建信息拦截器，方便跟踪
 */
@Component
public class BaseInterceptor extends WebContentInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("gitCommitMessage", ConfigTools.getConfig("git.commit.message.short"));
        request.setAttribute("gitBranch",ConfigTools.getConfig("git.branch"));
        request.setAttribute("gitCommitId",ConfigTools.getConfig("git.commit.id"));
        request.setAttribute("gitTagName", ConfigTools.getConfig("git.closest.tag.name"));
        request.setAttribute("gitBuildTime",ConfigTools.getConfig("git.build.time"));
    }
}
