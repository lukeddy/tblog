package com.tangzq.exception;

/**
 * 系统级异常
 */
public class SysException extends RuntimeException {

    public SysException() {
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

}
