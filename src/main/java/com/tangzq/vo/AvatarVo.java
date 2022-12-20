package com.tangzq.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author luke
 */
@Getter
@Setter
@ToString
public class AvatarVo {

    private String uid;

    @NotEmpty(message = "头像文件不能为空")
    private MultipartFile file;

}
