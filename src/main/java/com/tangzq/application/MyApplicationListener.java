package com.tangzq.application;

import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.utils.CommonProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author tangzhiqiang
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Autowired
    private UserRepository userRepository;


    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("当前环境："+activeProfiles);
        System.out.println("系统开始初始化...");
        initSystem();
        System.out.printf("系统初始化完毕！");
    }

    /**
     * 初始化系统
     */
    public void initSystem(){
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



}
