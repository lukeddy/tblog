package com.tangzq.service.impl;

import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.service.UserService;
import com.tangzq.utils.CommonProps;
import com.tangzq.utils.GravatarUtils;
import com.tangzq.vo.RegisterUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(RegisterUserVo vo) {
        if(null==vo){
            return null;
        }
        User user=new User();
        user.setUsername(vo.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(vo.getPassword().getBytes()));
        user.setEmail(vo.getEmail());
        user.setAvatarURL(GravatarUtils.makeGravatar(vo.getEmail()));
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

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

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
