package com.tangzq.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class AvatarVo {

    private String uid;

    @NotEmpty(message = "头像文件不能为空")
    private MultipartFile file;

}
