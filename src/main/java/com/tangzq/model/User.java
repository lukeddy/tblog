package com.tangzq.model;

import com.tangzq.model.embed.SocialInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author tangzhiqiang
 */
@Document(collection = "users")
@Getter
@Setter
@ToString
public class User extends BaseModel<String>{

    private String username;
    private String password;
    private String email;
    private String avatarURL;

    private String website;
    private String location;
    private String slogan;
    private String selfDesc;

    private SocialInfo socialInfo;


    public User() {
    }

    public boolean isNew() {
        return getId()==null;
    }
}
