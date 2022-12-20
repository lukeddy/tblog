package com.tangzq.model.embed;

import com.tangzq.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 举报理由封装类
 * @author luke
 */
@Getter
@Setter
@ToString
public class ReportInfo {
    /**
     * 举报理由：1-营销诈骗，2-淫秽色情，3-地域攻击，4-其他理由，
     */
    private int reason;
    private String memo;

    @ApiModelProperty(required = false, hidden = true)
    @DBRef(lazy = true)
    private User reportUser;


    /**
     * 重写equals和hashcode方法，使得set中的对象引用可以自动去重
     * 同一个用户只保留一条举报信息
     * @return
     */
    @Override
    public int hashCode() {
        return reportUser.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReportInfo) {
            ReportInfo info = (ReportInfo) obj;
            return reportUser.getId().equals(info.getReportUser().getId());
        }
        return super.equals(obj);
    }
}
