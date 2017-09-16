package com.tangzq.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "topics")
@Getter
@Setter
@ToString
public class Topic extends BaseModel<String>{

    private String title;
    private String content;
    private String authorId;
    private boolean top=false;// 置顶帖
    private boolean good=false;// 精华帖
    private boolean lock=false;// 被锁定主题
    private int replyCount=0;
    private int visitCount=0;
    private int collectCount=0;
    private String lastReplyId;
    private String lastReplyAt;
    private boolean contentIsHTML;
    private boolean deleted=false;
    private String catId; //栏目ID

    public boolean isNew() {
        return getId()==null;
    }
}
