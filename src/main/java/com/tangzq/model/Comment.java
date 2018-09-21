package com.tangzq.model;

import com.tangzq.model.type.CommentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * 评论Model
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
@Document(collection = "global-comments")
public class Comment extends BaseModel<String> {

    /**
     * 评论关联的项目ID,如插件、文章、工具等
     */
    private String itemId;
    private CommentType type;

    /**
     * 评论内容
     */
    private String commentMD;
    private String commentHTML;

    /**
     * 评论用户
     */
    @DBRef
    private User author;

    /**
     * 点赞次数
     */
    private int thumbsUPCount;

    @DBRef(lazy = true)
    private Set<User> thumbsUpUsers;


    @DBRef
    private Comment parentComment;

    /**
     * 删除状态
     */
    private boolean deleteStatus=false;

    /**
     * 举报状态
     */
    private boolean reportStatus=false;



    @Override
    public boolean isNew() {
        return getId()==null;
    }
}
