package com.tangzq.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author luke
 */
@Getter
@Setter
@ToString
public class UserPwdVo {

    private String uid;
    private String username;

    @NotEmpty(message = "旧密码不能为空")
    private String oldPwd;

    @NotEmpty(message = "新密码不能为空")
    @Size(min = 6, max = 15, message = "新密码必须在6到15个字符")
    private String newPwd;

    @NotEmpty(message = "新密码不能为空")
    private String repeatNewPwd;
}
