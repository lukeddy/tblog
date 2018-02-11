package com.tangzq.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class TopicVo{

    private String topicId;
    private String catId;
    private String title;
    private String desc;
    private String thumbURL;
    private String tags;
    private String contentMD;
    private String contentHTML;
    private String authorId;
    private String authorName;
    private boolean top;
    private boolean good;
    private boolean contentIsHTML;


}
