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

    public User getUser(String uid) {
        return userRepository.findOne(uid);
    }

    public User findUser(String username, String password) {
        String encrypedPwd= DigestUtils.md5DigestAsHex(password.getBytes());
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

    public User updateUserInfo(User user) {
        if(null==user||user.getId()==null){
            return null;
        }
        User userInDB=userRepository.findOne(user.getId());
        if(null==userInDB){
            return null;
        }
        user.setUsername(userInDB.getUsername());
        user.setPassword(userInDB.getPassword());
        user.setAvatarURL(userInDB.getAvatarURL());
        if(null==user.getEmail()){
            user.setEmail(userInDB.getEmail());
        }
        user.setCreateAt(userInDB.getCreateAt());
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

    public User updatePwd(String userId, String newPwd) {
        User userInDb=getUser(userId);
        if(null==userInDb){
            return null;
        }
        String encrypedNewPwd= DigestUtils.md5DigestAsHex(newPwd.getBytes());
        userInDb.setPassword(encrypedNewPwd);
        return userRepository.save(userInDb);
    }

    public User updateAvatar(String userId, String avatarURL) {
        User userInDb=getUser(userId);
        if(null==userInDb){
            return null;
        }
        userInDb.setAvatarURL(avatarURL);
        return userRepository.save(userInDb);
    }
}
