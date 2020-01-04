package com.tangzq.controller.error;

import com.tangzq.exception.NotFoundException;
import com.tangzq.exception.SysException;
import com.tangzq.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * API全局异常处理器
 * @author tangzhiqiang
 */
@RestControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {

    @ExceptionHandler(value = SysException.class)
    public Result sysException(Exception e) {
        log.error("exception occur:e={}",e);
        return Result.fail("服务端内部错误");
    }


    @ExceptionHandler(value = {NotFoundException.class, NoHandlerFoundException.class})
    public Result exception(NotFoundException e){
        log.error("exception occur:e={}",e);
        return Result.fail("资源不存在");
    }
}
