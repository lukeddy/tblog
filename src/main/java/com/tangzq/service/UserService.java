package com.tangzq.service;

import com.tangzq.model.User;

public interface UserService {


    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User findUser(String username,String password);

    /**
     * 判断用户是否有效
     * @param username
     * @param password
     * @return
     */
    boolean isUserValid(String username,String password);



}
