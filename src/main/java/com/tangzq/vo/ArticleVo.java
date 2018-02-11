package com.tangzq.vo;

import com.tangzq.model.Reply;
import com.tangzq.model.Topic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class ArticleVo {
    private Topic topic;
    private List<Reply> replyList;
}
