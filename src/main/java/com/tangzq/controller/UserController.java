package com.tangzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户控制器
 */
@Controller
public class UserController {


    /**
     * 用户页面
     *
     * @return
     */
    @RequestMapping(value = "/user/{uid}",method = RequestMethod.GET)
    public String index(@PathVariable("uid") String uid,ModelMap model) {
        //TODO
        System.out.println(uid);
        return "user";
    }


}
