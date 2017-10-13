package com.tangzq.application;

import com.tangzq.gitinfo.GitCommitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Autowired
    GitCommitInfo gitCommitInfo;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("当前环境："+activeProfiles);
        System.out.println("当前构建信息：");
        System.out.println(gitCommitInfo.toString());
    }


}
