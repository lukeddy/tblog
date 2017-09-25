<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class="panel">
            <div class="header">
                <ul class='breadcrumb'>
                    <li><a href='/'><i class="glyphicon glyphicon-home"></i>主页</a><span class='divider'></span></li>
                    <li><a href='javascript:;'>搜索结果</a><span class='divider'></span></li>
                </ul>
                <br>
            </div>
            <div class="inner no-padding">
                <form action="${contextPath}/search" method="get" role="form" style="margin-bottom: 2em;">
                    <div class="input-group">
                        <input type="text" name="keywords" class="form-control" value="${searchVo.keywords}">
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-primary">搜索</button>
                        </span>
                    </div>
                </form>
                <c:if test="${not empty pager.content}">
                    <div class="search-result">
                        <c:forEach items="${pager.content}" var="topic">
                          <div class="cell">
                            <a class="user_avatar pull-left" href="${contextPath}/user/${topic.authorName}">
                                <img src="//gravatar.com/avatar/d00d8e3461257418a62b1cb7abeea85a?size=48" title="${topic.authorName}">
                            </a>

                            <a class="last_time pull-right" href="javascript:;">
                                <span class="reply_count">
                                <span class="count_of_replies" title="回复数">
                                        ${topic.replyCount}
                                </span>
                                <span class="count_seperator">/</span>
                                <span class="count_of_visits" title="点击数">
                                        ${topic.visitCount}
                                </span>
                            </span>
                                <span class="last_active_time">${topic.friendlyTime}</span>
                            </a>
                            <div class="topic_title_wrapper">
                                <a href="${contextPath}/?tab=${topic.catDir}">
                                    <span class="topiclist-tab">${topic.catName}</span>
                                </a>
                                <a class="topic_title" href="${contextPath}/article/${topic.id}" title="${topic.title}">
                                   ${topic.title}
                                </a>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <!--分页开始-->
                    <jsp:include page="inc/pagination.jsp">
                        <jsp:param name="pager" value="${pager}"/>
                        <jsp:param name="baseURL" value="${contextPath}/search?keywords=${searchVo.keywords}"/>
                    </jsp:include>
                    <!--分页结束-->
                </c:if>
                <c:if test="${empty pager.content}">
                    <p class="text-center">没有搜索结果</p>
                </c:if>
        </div>
    </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
<script src="${contextPath}/js/mark/mark.js"></script>
<script>
    $(document).ready(function(){
        var instance = new Mark(document.querySelector("div.search-result"));
        instance.mark("${searchVo.keywords}", {
            "element": "span",
            "className": "highlight"
        });
    });
</script>
