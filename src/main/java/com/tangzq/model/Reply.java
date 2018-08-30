package com.tangzq.model;

import com.tangzq.model.embed.ReplyAuthorInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author tangzhiqiang
 */
@Document(collection = "replys")
@Getter
@Setter
@ToString
public class Reply extends BaseModel<String> {

    private String contentMD;
    private String contentHTML;
    private String topicId;
    @DBRef
    private User replyUser;
    private String replyId;
    private boolean contentIsHTML;
    private int thumbsUPCount;
    private boolean deleted;

    @Override
    public boolean isNew() {
        return getId()==null;
    }
}
