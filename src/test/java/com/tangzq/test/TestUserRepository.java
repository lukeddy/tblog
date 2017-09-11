package com.tangzq.test;


import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.utils.CommonProps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

public class TestUserRepository extends TestBase {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        User u=userRepository.findByUsername(CommonProps.ADMIN_NAME);
        if(null==u){
            u=new User();
            u.setUsername(CommonProps.ADMIN_NAME);
            u.setPassword(DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes()));
            u.setEmail(CommonProps.ADMIN_EMAIL);
            u.setCreateAt(new Date());
            u.setUpdateAt(new Date());
            userRepository.save(u);
            System.out.println("初始化管理员账号成功！");
        }else{
            System.out.println("管理员账号已经存在");
        }
    }


    @Test
    public void testFindAll(){
        List<User> users=(List<User>)userRepository.findAll();
        Assert.isTrue(!users.isEmpty(),"没有用户信息");
        for(User u:users){
            System.out.println(u);
        }
    }

    @Test
    public void testFindByUsername(){
        User user=userRepository.findByUsername(CommonProps.ADMIN_NAME);
        Assert.notNull(user,"");
        System.out.println(user);
    }

    @Test
    public void testFindById(){
        User user=userRepository.findOne("59b50c1777c8f880f5d5207d");
        Assert.notNull(user,"");
        System.out.println(user);
    }

    @Test
    public void testFindByEmail(){
        User user=userRepository.findByEmail(CommonProps.ADMIN_EMAIL);
        Assert.notNull(user,"");
        System.out.println(user);
    }

    @Test
    public void testFindByUsernameAndPwd(){
        User u=userRepository.findByUsernameAndPassword(CommonProps.ADMIN_NAME,
                DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes()));
        Assert.notNull(u,"");
        System.out.println(u);

        u=userRepository.findByUsernameAndPassword("test",
                DigestUtils.md5DigestAsHex("test".getBytes()));
        Assert.isNull(u,"");
    }


    @Test
    public void testLogin(){
        Assert.isTrue(!isLogin("test","test"),"");
        Assert.isTrue(isLogin(CommonProps.ADMIN_NAME,CommonProps.ADMIN_PWD),"");

    }

    private boolean isLogin(String uname,String pwd){
        User user=userRepository.findByUsernameAndPassword(uname,DigestUtils.md5DigestAsHex(pwd.getBytes()));
        if(null==user){
            System.out.println("账号或者密码错误！");
            return false;
        }else{
            System.out.println("登陆成功！");
            return true;
        }
    }

    @Test
    public void testUpdate(){
        User tmpUser=userRepository.findOne("59b5132777c8f22ff6b0da91");
        Assert.notNull(tmpUser,"");
        tmpUser.setEmail("abcdef@gmail.com");
        userRepository.save(tmpUser);
    }


    @Test
    public void testDelete(){
        userRepository.delete("59b5132777c8f22ff6b0da91");
    }
}
