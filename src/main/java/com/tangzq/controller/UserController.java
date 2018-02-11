package com.yocool.controller;

import com.yocool.model.User;
import com.yocool.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody Map<String, Object> userMap){
        User user = new User(userMap.get("username").toString(),
                userMap.get("password").toString(),
                userMap.get("email").toString());

        userRepository.save(user);
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", "User created successfully");
        response.put("user", user);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{uid}")
    public User getUserDetail(@PathVariable("uid") String uid){
        return userRepository.findOne(uid);
    }
}
