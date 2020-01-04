package com.tangzq.controller.error;

import com.tangzq.exception.NotFoundException;
import com.tangzq.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 * @author tangzhiqiang
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SysException.class)
    public String sysException(Exception e) {
        log.error("exception occur:e={}",e.getMessage());
        e.printStackTrace();
        return "error/500";
    }


    @ExceptionHandler(value = NotFoundException.class)
    public String exception(NotFoundException e){
        log.error("exception occur:e={}",e.getMessage());
        e.printStackTrace();
        return "error/404";
    }
}
