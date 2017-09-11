package com.tangzq.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Getter
@Setter
@ToString
public class Category extends BaseModel<String>{

    private String catName;
    private String catDir;
    private String catDesc;

    @Transient
    private String createAtFormatted;

    @Transient
    private String updateAtFormatted;

    public boolean isNew() {
        return getId()==null;
    }


    public String getCreateAtFormatted() {
        return null==getCreateAt()?null: DateFormatUtils.format(getCreateAt(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getUpdateAtFormatted() {
        return null==getUpdateAt()?null:DateFormatUtils.format(getUpdateAt(),"yyyy-MM-dd HH:mm:ss");
    }
}
