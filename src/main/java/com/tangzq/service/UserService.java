package com.tangzq.service;

import com.tangzq.model.User;
import com.tangzq.vo.RegisterUserVo;

public interface UserService {


    /**
     * 创建用户
     * @param vo
     * @return
     */
    User createUser(RegisterUserVo vo);


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


    /**
     * 使用用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 使用邮箱查找用户
     * @param email
     * @return
     */
    User findUserByEmail(String email);

}
