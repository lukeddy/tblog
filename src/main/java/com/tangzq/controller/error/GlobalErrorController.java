package com.tangzq.controller.error;

import com.tangzq.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
public class GlobalErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(
            value = {ERROR_PATH},
            produces = {"text/html"}
    )
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (code == 404) {
            return new ModelAndView("error/404");
        } else if (code == 403) {
            return new ModelAndView("error/403");
        } else if (code == 401) {
            return new ModelAndView("redirect:/login");
        } else {
            return new ModelAndView("error/500");
        }
    }

    @RequestMapping(value = ERROR_PATH)
    public Result handleError(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        int code = response.getStatus();
        if (404 == code) {
            return Result.fail( "未找到资源",404);
        } else if (403 == code) {
            return Result.fail( "没有访问权限",403);
        } else if (401 == code) {
            return Result.fail( "登录过期",401);
        } else {
            return Result.fail( "服务器错误",500);
        }
    }
}
