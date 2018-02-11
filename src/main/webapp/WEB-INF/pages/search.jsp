<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'><i class="glyphicon glyphicon-home"></i>主页</a><span class='divider'></span></li>
            <li><a href='javascript:;'>搜索结果</a><span class='divider'></span></li>
        </ul>

        <div class="panel">
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
                                                            <a href="${contextPath}/pub/user/${topic.authorId}">${topic.authorName}.${topic.friendlyTime}</a>
                                                        </li>
                                                        <li class="item category">
                                                            <a href="${contextPath}/?tab=${topic.catDir}">
                                                                <span class="topiclist-tab">${topic.catName}</span>
                                                            </a>
                                                        </li>
                                                        <c:if test="${not empty topic.tags}">
                                                            <li class="item tag">
                                                                <c:forEach items="${topic.tags}" var="tag">
                                                                    <a class="tag" href="${contextPath}/tag/${tag};">${tag}</a>
                                                                </c:forEach>
                                                            </li>
                                                        </c:if>
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
                                                            <span class="title">${topic.visitCount}</span>
                                                        </a>
                                                        <a class="action comment" href="javascript:;" >
                                                            <span class="icon"></span>
                                                            <span class="title">${topic.replyCount}</span>
                                                        </a>
                                                        <a  class="action collect hover" href="javascript:;">
                                                            <span class="icon"></span>
                                                            <span class="title">收藏</span>
                                                        </a>
                                                        <a class="action share hover" href="javascript:;">
                                                            <span class="icon"></span>
                                                            <span class="title">分享</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${not empty topic.thumbURL}">
                                                <div class="thumb-box" style='background-image: url("${topic.thumbURL}"); background-size: cover;'></div>
                                            </c:if>
                                        </div>
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                    <!--分页开始-->
                    <jsp:include page="inc/pagination.jsp">
                        <jsp:param name="pager" value="${pager}"/>
                        <jsp:param name="baseURL" value="${contextPath}/search"/>
                        <jsp:param name="otherParams" value="&keywords=${searchVo.keywords}"/>
                    </jsp:include>
                    <!--分页结束-->
                </c:if>
                <c:if test="${empty pager.content}">
                    <p class="text-center">没有搜索结果</p>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
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
