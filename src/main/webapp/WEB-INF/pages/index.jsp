<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class="panel">
            <div class="header">
                <a href="${contextPath}/?tab=all" class='topic-tab <c:if test="${indexVo.tab=='all'}">current-tab</c:if>'>全部</a>
                <c:forEach items="${catList}" var="cat">
                    <a href="${contextPath}/?tab=${cat.catDir}" class='topic-tab <c:if test="${indexVo.tab==cat.catDir}">current-tab</c:if>'>${cat.catName}</a>
                </c:forEach>
            </div>
            <div class="inner no-padding">
                <c:if test="${not empty pager.content}">
                    <div id="topic_list">
                        <c:forEach items="${pager.content}" var="topic">
                          <div class="cell">
                            <a class="user_avatar pull-left" href="${contextPath}/user/${topic.authorName}">
                                <img src="//gravatar.com/avatar/d00d8e3461257418a62b1cb7abeea85a?size=48" title="${topic.authorName}">
                            </a>
                            <span class="reply_count pull-left">
                                <span class="count_of_replies" title="回复数">
                                  ${topic.replyCount}
                                </span>
                                <span class="count_seperator">/</span>
                                <span class="count_of_visits" title="点击数">
                                  ${topic.visitCount}
                                </span>
                            </span>
                            <a class="last_time pull-right" href="javascript:;">
                                <span class="count_of_visits">${topic.visitCount}</span>
                                <span class="last_active_time">${topic.friendlyTime}</span>
                            </a>
                            <div class="topic_title_wrapper">
                                <c:if test="${'all'==indexVo.tab and topic.top}">
                                    <span class="put_top">置顶</span>
                                </c:if>
                                <c:if test="${!topic.top}">
                                    <a href="${contextPath}/?tab=${topic.catDir}">
                                        <span class="topiclist-tab">${topic.catName}</span>
                                    </a>
                                </c:if>
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
                        <jsp:param name="baseURL" value="${contextPath}/?tab=${indexVo.tab}"/>
                    </jsp:include>
                    <!--分页结束-->
                </c:if>
                <c:if test="${empty pager.content}">
                    <p class="text-center">还没有帖子</p>
                </c:if>
        </div>
    </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
