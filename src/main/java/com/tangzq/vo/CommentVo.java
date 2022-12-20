package com.tangzq.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Comment VO类
 * @author luke
 */
@Getter
@Setter
@ToString
public class CommentVo {

    private String itemId;
    private String commentMD;
    private String commentHTML;
    private String authorId;

    private String vcode;

}
