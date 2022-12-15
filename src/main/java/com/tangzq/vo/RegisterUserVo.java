package com.tangzq.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * @author tangzhiqiang
 */
@Getter
@Setter
public class RegisterUserVo {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 15, message = "密码必须在6到15个字符")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "验证码不能为空")
    private String validateCode;
}
