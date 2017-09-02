package com.tangzq.service.impl;

import com.tangzq.service.MyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MyServiceImpl implements MyService {

    @Value("${appname}")
    private String configAppName;

    public String getAppName() {
        return configAppName;
    }
}
