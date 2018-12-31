package com.tangzq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User extends BaseModel<String> {

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String email;

    private String avatarURL;
    private boolean avatarURLByUploaded;

    private String website;
    private String location;
    private String slogan;
    private String selfDesc;

    private SocialInfo socialInfo;


    public User() {
    }

    @Override
    public boolean isNew() {
        return getId()==null;
    }

    /**
     * 重写equals和hashcode方法，使得set中的对象引用可以自动去重
     * @return
     */
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return this.getId().equals(user.getId());
        }
        return super.equals(obj);
    }
}
