package com.tangzq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Getter
@Setter
@ToString
public class Comment extends BaseModel<String> {

    private String commentContent;
    private ObjectId topicId;
    private ObjectId authorId;
    private ObjectId replyCommentId;
    private boolean contentIsHTML;
    private int thumbsUPCount;
    private boolean deleted;

    public boolean isNew() {
        return getId()==null;
    }
}
