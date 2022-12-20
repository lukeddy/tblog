package com.tangzq.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * @author luke
 */
@Document(collection = "topics")
@Getter
@Setter
@ToString
public class Topic extends BaseModel<String> {

    private String title;
    private String desc;
    private String thumbURL;
    private List<String> tags;
    private String contentMD;
    private String contentHTML;

    @DBRef
    private User author;
    /**
     * 置顶帖
     */
    private boolean top=false;

    /**
     * 精华帖
     */
    private boolean good=false;

    /**
     * 被锁定主题
     */
    private boolean lock=false;
    private int replyCount=0;
    private int visitCount=0;
    private int collectCount=0;
    private String lastReplyId;
    private String lastReplyAt;
    private boolean contentIsHTML;
    private boolean deleted=false;
    @DBRef
    private Category category;

    @DBRef
    private Set<User> collectedUsers;

    @DBRef
    private Set<User> likedUsers;

    @Override
    public boolean isNew() {
        return getId()==null;
    }

}
