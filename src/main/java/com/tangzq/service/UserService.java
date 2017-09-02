package com.tangzq.service;

public interface UserService {

    /**
     * 判断用户是否有效
     * @param username
     * @param password
     * @return
     */
    boolean isUserValid(String username,String password);



}
