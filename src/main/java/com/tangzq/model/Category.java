package com.tangzq.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Getter
@Setter
@ToString
public class Category extends BaseModel<String>{

    private String catName;
    private String catDir;
    private String catDesc;


    public boolean isNew() {
        return getId()==null;
    }

}
