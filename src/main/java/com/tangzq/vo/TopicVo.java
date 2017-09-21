package com.tangzq.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopicVo{

    private String topicId;
    private String catId; //栏目ID
    private String title;
    private String contentMD; //Markdown格式内容
    private String contentHTML; //HTML格式内容
    private String authorId;
    private String authorName;
    private boolean top;// 置顶帖
    private boolean good;// 精华帖
    private boolean contentIsHTML;


}
