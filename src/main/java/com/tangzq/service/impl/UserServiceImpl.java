package com.tangzq.service.impl;

import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.service.UserService;
import com.tangzq.utils.CommonProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String username, String password) {
        String encrypedPwd= DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes());
        return userRepository.findByUsernameAndPassword(username,encrypedPwd);
    }

    public boolean isUserValid(String username, String password) {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return false;
        }

        User user=findUser(username,password);
        if(null!=user){
            return true;
        }
        return false;
    }
}
