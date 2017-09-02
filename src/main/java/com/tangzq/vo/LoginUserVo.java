package com.tangzq.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserVo {
    private String username;
    private String password;
    private String validateCode;
}
