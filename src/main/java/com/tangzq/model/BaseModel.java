package com.tangzq.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public abstract class BaseModel<ID extends Serializable> implements Persistable<ID> {
    private static final long serialVersionUID = 1L;

    @Id
    private ID id;

    @Field("create_at")
    @CreatedDate
    private Date createAt;


    @Field("update_at")
    @LastModifiedDate
    private Date updateAt;


}
