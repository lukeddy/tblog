<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'>主页</a><span class='divider'></span></li>
            <li class='active'>${user.username}主页</li>
        </ul>
        <div class="panel">
            <div class="inner user-info-block">
                <c:if test="${user.avatarURLByUploaded}">
                    <div class="avatar" style='background-image: url("${contextPath}/upload/getImage/${user.avatarURL}");background-size:cover;'></div>
                </c:if>
                <c:if test="${!user.avatarURLByUploaded}">
                    <div class="avatar" style='background-image: url("${user.avatarURL}");background-size:cover;'></div>
                </c:if>

                <div class="info-box">
                    <h1 class="username">${user.username}</h1>
                    <div class="position">
                        <svg width="21" height="18" viewBox="0 0 21 18" class="icon position-icon">
                            <g fill="none" fill-rule="evenodd">
                                <path fill="#72777B" d="M3 8.909V6.947a1 1 0 0 1 1-1h13a1 1 0 0 1 1 1V8.92l-6 2.184v-.42c0-.436-.336-.79-.75-.79h-1.5c-.414 0-.75.354-.75.79v.409L3 8.909zm0 .7l6 2.184v.47c0 .436.336.79.75.79h1.5c.414 0 .75-.354.75-.79v-.46l6-2.183V16a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V9.609zm6.75 1.075h1.5v1.58h-1.5v-1.58z"></path>
                                <path stroke="#72777B" d="M7.5 5.213V4A1.5 1.5 0 0 1 9 2.5h3A1.5 1.5 0 0 1 13.5 4v1.213"></path>
                            </g>
                        </svg>
                        <span class="content">
                            <a href="javascript:;" target="_blank">${user.website}</a>
                        </span>
                    </div>
                    <div class="intro">
                        <svg width="21" height="18" viewBox="0 0 21 18" class="icon intro-icon">
                            <path fill="#72777B" fill-rule="evenodd" d="M4 4h13a1 1 0 0 1 1 1v9a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V5a1 1 0 0 1 1-1zm9 6a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3 3a3 3 0 0 0-6 0h6zM5 7v1h4V7H5zm0 2.5v1h4v-1H5zM5 12v1h4v-1H5z"></path>
                        </svg>
                        <span class="content">${user.selfDesc}</span>
                    </div>
                </div>
                <div class="action-box">
                    <div class="link-box link-box"></div>
                    <button class="follow-btn btn">关注</button>
                </div>
            </div>
        </div>
        <div class="panel">
            <div class="inner no-padding">
                <div class="header">
                    <a href="${contextPath}/pub/user/${user.id}/?tab=new" class='topic-tab <c:if test="${empty tab or tab=='new'}">current-tab</c:if>'>新建帖子</a>
                    <a href="${contextPath}/pub/user/${user.id}/?tab=collect" class='topic-tab <c:if test="${tab=='collect'}">current-tab</c:if>'>收藏帖子</a>
                </div>
                <c:if test="${not empty pager.content}">
                    <div class="search-result">
                        <ul class="post-list">
                            <c:forEach items="${pager.content}" var="topic">
                                <li class="post-item">
                                    <div class="entry">
                                        <a class="entry-link" href="${contextPath}/article/${topic.id}">
                                            <div class="content-box">
                                                <div class="info-box">
                                                    <div class="title-row">
                                                        <a class="title" href="${contextPath}/article/${topic.id}" target="_self">${topic.title}</a>
                                                    </div>
                                                    <div class="desc-row">${topic.desc}</div>
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
                    <jsp:include page="../inc/pagination.jsp">
                        <jsp:param name="pager" value="${pager}"/>
                        <jsp:param name="baseURL" value="${contextPath}/pub/user/${user.id}"/>
                        <jsp:param name="otherParams" value="&tab=${tab}"/>
                    </jsp:include>
                    <!--分页结束-->
                </c:if>
                <c:if test="${empty pager.content}">
                    <p class="text-center">没有搜索结果</p>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>