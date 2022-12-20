package com.tangzq.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 评论回复数据封装
 * @author luke
 */
@Getter
@Setter
@ToString
public class ReplyDto {

    private String commentId;
    private String replyMD;
    private String replyHtml;

}
