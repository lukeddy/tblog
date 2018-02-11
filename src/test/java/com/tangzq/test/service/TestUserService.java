package com.tangzq.test.service;


import com.tangzq.service.UserService;
import com.tangzq.test.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class TestUserService extends TestBase {

    @Autowired
    private UserService userService;

    @Test
    public void testValidateUser(){
        Assert.isTrue(userService.isUserValid("admin","123456"),"用户名或者密码错误");
        Assert.isTrue(!userService.isUserValid("admin","test"),"用户名和密码正确");
    }
}
