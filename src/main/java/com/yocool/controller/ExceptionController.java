package com.yocool.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理控制器
 * Author tangzq.
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 自定义异常处理
     * @return
     */
//    @ExceptionHandler({ProductNotFoundException.class})
//    public String prodNotExist(Exception exception) {
//        logger.error("出错了",exception);
//        return "error/prod_not_exist";
//    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        logger.error("请求: " + req.getRequestURL() + " 发生了错误 ",exception);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error/server_error");
        return mav;
    }
}
