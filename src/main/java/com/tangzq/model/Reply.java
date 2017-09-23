package com.tangzq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "replys")
@Getter
@Setter
@ToString
public class Reply extends BaseModel<String> {

    private String contentMD;
    private String contentHTML;
    private String topicId;
    private String authorId;
    private String replyId;
    private boolean contentIsHTML;
    private int thumbsUPCount;
    private boolean deleted;

    public boolean isNew() {
        return getId()==null;
    }
}
