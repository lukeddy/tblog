package com.tangzq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@ToString
public class User extends BaseModel<String>{

    private String username;
    private String password;
    private String email;
    private String avatarURL;

    public User() {
    }

    public boolean isNew() {
        return getId()==null;
    }
}
