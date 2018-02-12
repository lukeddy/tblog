<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <div class="panel">
            <div class="header">
                <a href="${contextPath}/?tab=all" class='topic-tab <c:if test="${indexVo.tab=='all'}">current-tab</c:if>'>全部</a>
                <c:choose>
                    <c:when test="${catList.size()<=8}">
                        <c:forEach items="${catList}" var="cat">
                            <a href="${contextPath}/?tab=${cat.catDir}" class='topic-tab <c:if test="${indexVo.tab==cat.catDir}">current-tab</c:if>'>${cat.catName}</a>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${catList}" var="cat" begin="0" end="7">
                            <a href="${contextPath}/?tab=${cat.catDir}" class='topic-tab <c:if test="${indexVo.tab==cat.catDir}">current-tab</c:if>'>${cat.catName}</a>
                        </c:forEach>
                        <span class="dropdown">
                                <a href="#" class="dropdown-toggle topic-tab" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${catList}" var="cat" begin="8" end="${catList.size()-1}">
                                        <li class="topic-tab">
                                            <a href="${contextPath}/?tab=${cat.catDir}" class='topic-tab <c:if test="${indexVo.tab==cat.catDir}">current-tab</c:if>'>${cat.catName}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                           </span>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="inner no-padding">
                <c:if test="${not empty pager.content}">
                    <ul class="post-list">
                        <c:forEach items="${pager.content}" var="topic">
                            <li class="post-item">
                                <div class="entry">
                                    <a class="entry-link" href="${contextPath}/article/${topic.id}">
                                        <div class="content-box">
                                            <div class="info-box">
                                                <div class="meta-row">
                                                    <ul class="meta-list">
                                                        <c:if test="${'all'==indexVo.tab and topic.top}">
                                                            <li class="item recommended">置顶</li>
                                                        </c:if>
                                                        <li class="item username">
                                                            <a href="${contextPath}/pub/user/${topic.authorId}">${topic.authorName}</a>.${topic.friendlyTime}
                                                        </li>
                                                        <li class="item category">
                                                            <a href="${contextPath}/?tab=${topic.catDir}">
                                                                <span class="topiclist-tab">${topic.catName}</span>
                                                            </a>
                                                        </li>
                                                        <c:if test="${not empty topic.tags}">
                                                            <li class="item tag">
                                                                <c:forEach items="${topic.tags}" var="tag">
                                                                    <a class="tag" href="${contextPath}/tag/${tag}">${tag}</a>
                                                                </c:forEach>
                                                            </li>
                                                        </c:if>
                                                        <li class="item">${topic.visitCount}次阅读</li>
                                                    </ul>
                                                </div>
                                                <div class="title-row">
                                                    <a class="title" href="${contextPath}/article/${topic.id}" target="_blank">${topic.title}</a>
                                                </div>
                                                <div class="desc-row">${topic.desc}</div>
                                                <div class="action-row">
                                                    <div class="action-list">
                                                        <a class="action like" href="javascript:;" >
                                                            <span class="icon"></span>
                                                            <span class="title">${empty topic.likedUsers?0:topic.likedUsers.size()}</span>
                                                        </a>
                                                        <a class="action comment" href="${contextPath}/article/${topic.id}#comment" >
                                                            <span class="icon"></span>
                                                            <span class="title">${topic.replyCount}评论</span>
                                                        </a>
                                                        <a  class="action collect hover" href="javascript:;">
                                                            <span class="icon"></span>
                                                            <span class="title">${topic.collectCount}收藏</span>
                                                        </a>
                                                        <a class="action share hover" href="javascript:;">
                                                            <span class="icon"></span>
                                                            <span class="title">分享</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${not empty topic.thumbURL}">
                                                <div class="thumb-box" style='background-image: url("${contextPath}/upload/getImage/${topic.thumbURL}"); background-size: cover;'></div>
                                            </c:if>
                                        </div>
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <!--分页开始-->
                    <jsp:include page="inc/pagination.jsp">
                        <jsp:param name="pager" value="${pager}"/>
                        <jsp:param name="baseURL" value="${contextPath}/"/>
                        <jsp:param name="otherParams" value="tab=${indexVo.tab}"/>
                    </jsp:include>
                    <!--分页结束-->
                </c:if>
                <c:if test="${empty pager.content}">
                    <p class="text-center">还没有帖子</p>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
