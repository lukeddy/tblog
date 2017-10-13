package com.tangzq.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("当前环境："+activeProfiles);
    }


}
