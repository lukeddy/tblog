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
                <style>
                    .post-list{
                        width: 100%;
                        margin:0;
                        padding:0;
                    }

                    .post-list .post-item{
                        list-style: none;
                        border-bottom: 1px solid rgba(178,186,194,.15);
                    }

                    .post-item .entry:hover{
                        background-color: #f9fafd;
                    }

                    .post-item .entry-link{
                        display: block;
                        height: 100%;
                        text-decoration: none;
                        cursor: pointer;
                        color: #909090;
                    }

                    .post-item .content-box{
                        -webkit-box-align: center;
                        -ms-flex-align: center;
                        align-items: center;
                        padding: 1.7rem 2rem 1.4rem;
                        display: -webkit-box;
                        display: -ms-flexbox;
                        display: flex;
                    }
                    .content-box .info-box{
                        -webkit-box-flex: 1;
                        -ms-flex: 1 1 auto;
                        flex: 1 1 auto;
                        -webkit-box-orient: vertical;
                        -webkit-box-direction: normal;
                        -ms-flex-direction: column;
                        flex-direction: column;
                        -webkit-box-pack: center;
                        -ms-flex-pack: center;
                        justify-content: center;
                        min-width: 0;
                    }
                    .content-box .thumb-box{
                        background-position: 50%;
                        background-size: cover;
                        background-repeat: no-repeat;
                        -webkit-box-flex: 0;
                        -ms-flex: 0 0 auto;
                        flex: 0 0 auto;
                        width: 6.25rem;
                        height: 6.25rem;
                        margin-left: 2rem;
                        border-radius: 2px;
                    }
                    .info-box .meta-row{
                        font-size: 1rem;
                        color: #8a9aa9;
                    }
                    .meta-row .meta-list{
                        margin:0;
                        padding:0;
                        display: -webkit-box;
                        display: -ms-flexbox;
                        display: flex;
                        -webkit-box-align: baseline;
                        -ms-flex-align: baseline;
                        align-items: baseline;
                        white-space: nowrap;
                    }

                    .meta-list .item{
                        margin:0 .4em;
                    }

                    .meta-list .item:not(:last-child):after {
                        content: "\B7";
                        /*margin: 0 .1em;*/
                        color: #8a9aa9;
                    }

                    .meta-list .item.recommended {
                        color: #f70;
                    }

                    .meta-list .item.tag, .meta-list .item.username {
                        text-overflow: ellipsis;
                        overflow: hidden;
                    }

                    .meta-list .item.username{
                        display: -webkit-box;
                        display: -ms-flexbox;
                        display: flex;
                        -webkit-box-align: baseline;
                        -ms-flex-align: baseline;
                        align-items: baseline;
                        max-width: 12em;
                    }

                    .meta-list .item.username>a{
                        font-weight: 700;
                        color: #3b76c5;
                    }

                    .meta-list .item.tag{
                        min-width: 0;
                        white-space: nowrap;
                    }

                    .meta-list .item .tag:not(:last-child):after {
                        content: "/";
                        margin: 0 .5em;
                        color: #b2bac2;
                    }

                    .info-box .title-row{
                        margin-top: .9rem;
                        color: #2e3135;
                    }

                    .title-row .title{
                        font-size: 1.46rem;
                        font-weight: 600;
                        line-height: 1.5;
                    }

                    .info-box .abstract-row{
                        display: -webkit-box;
                        margin-top: .5rem;
                        max-height: 3.2em;
                        font-size: 1.25rem;
                        line-height: 1.6;
                        letter-spacing: .01em;
                        color: #2e3135;
                        -webkit-line-clamp: 2;
                        -webkit-box-orient: vertical;
                        overflow: hidden;
                    }

                    .info-box .action-row{
                        margin-top: 1.1rem;
                    }

                    .action-row .action-list{
                        display: -webkit-box;
                        display: -ms-flexbox;
                        display: flex;
                        -webkit-box-align: center;
                        -ms-flex-align: center;
                        align-items: center;
                        white-space: nowrap;
                    }

                    .action-list .action{
                        display: -webkit-box;
                        display: -ms-flexbox;
                        display: flex;
                        -webkit-box-align: center;
                        -ms-flex-align: center;
                        align-items: center;
                        position: relative;
                        font-size: 1.167rem;
                        color: #b7c5d3;
                        text-decoration: none;
                    }

                    .action-list .action:not(:last-child) {
                        margin-right: 2.2rem;
                    }

                    .action-list .action>.icon {
                        width: 21px;
                        height: 20px;
                        background-position: 50%;
                        background-repeat: no-repeat;
                        background-size: contain;
                    }

                    .action-list .action:first-child>.icon{
                        width: 17px;
                        background-position-x: left;
                    }

                    .action-list .action.like>.icon{
                        background-image: url(http://gold-cdn.xitu.io/v3/static/img/like.b9bcdb5.svg);
                    }

                    .action-list .action.comment>.icon{
                        background-image: url(http://gold-cdn.xitu.io/v3/static/img/comment.bbbf39d.svg);
                    }
                    .action-list .action.collect>.icon {
                        background-image: url(http://gold-cdn.xitu.io/v3/static/img/collect.906ad32.svg);
                    }

                    .action-list .action.share>.icon{
                        background-image: url(http://gold-cdn.xitu.io/v3/static/img/share.bac0dc8.svg);
                    }

                    .action-list .action>.title{
                        margin-left: .3rem;
                        height: 20px;
                        line-height: 20px;
                    }

                    .action-list:not(.active)>.action.hover{
                        visibility: visible;
                    }

                    .action-list .action.open, .action-list .action:hover {
                        color: #9faebd;
                    }

                </style>
                <ul class="post-list">
                    <li class="post-item">
                        <div class="entry">
                            <a class="entry-link" href="http://www.baidu.com">
                                <div class="content-box">
                                    <div class="info-box">
                                        <div class="meta-row">
                                            <ul class="meta-list">
                                                <li class="item recommended">荐</li>
                                                <li class="item username">
                                                    <a href="javascript:;">由于自己平时涉猎还算广泛,由于自己平时涉猎还算广泛,大家的欢迎</a>
                                                </li>
                                                <li class="item tag">
                                                    <a class="tag" href="javascript:;">Android</a>
                                                    <a class="tag" href="javascript:;">CSS</a>
                                                    <a class="tag" href="javascript:;">前端技术</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="title-row">
                                            <a class="title" href="javascript:;" target="_blank">CSS 黑魔法小技巧，让你少写不必要的JS，代码更优雅</a>
                                        </div>
                                        <div class="abstract-row">之前不久，由于自己平时涉猎还算广泛，总结了一篇博客</div>
                                        <div class="action-row">
                                            <div class="action-list">
                                                <a class="action like" href="javascript:;" >
                                                    <span class="icon"></span>
                                                    <span class="title">199</span>
                                                </a>
                                                <a class="action comment" href="javascript:;" >
                                                    <span class="icon"></span>
                                                    <span class="title">29</span>
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
                                    <div class="thumb-box" style='background-image: url("http://localhost:8080/upload/20170924/1734579642540944.jpg"); background-size: cover;'></div>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="post-item">
                        <div class="entry">
                            <a class="entry-link" href="http://www.baidu.com">
                                <div class="content-box">
                                    <div class="info-box">
                                        <div class="meta-row">
                                            <ul class="meta-list">
                                                <li class="item recommended">荐</li>
                                                <li class="item username">
                                                    <a href="javascript:;">由于自己平时涉猎还算广泛,大家的欢迎</a>
                                                </li>
                                                <li class="item tag">
                                                    <a class="tag" href="javascript:;">Android</a>
                                                    <a class="tag" href="javascript:;">CSS</a>
                                                    <a class="tag" href="javascript:;">前端技术</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="title-row">
                                            <a class="title" href="javascript:;" target="_blank">CSS 黑魔法小技巧，让你少写不必要的JS，代码更优雅</a>
                                        </div>
                                        <div class="abstract-row">之前不久，由于自己平时涉猎还算广泛，总结了一篇博客：，没想到受到了大家的欢迎，有人希望能博主还能整理个 的一些黑魔法小技巧，无奈我 一直很渣，没什么干货，最近写了一个 插件 ，算是把 的样式审查了个变，在写的过程中，也收获了很多关于 的小技巧，尤其是开始的第一个技巧，学习到了很多，于是再加上一波搜</div>
                                        <div class="action-row">
                                            <div class="action-list">
                                                <a class="action like" href="javascript:;" >
                                                    <span class="icon"></span>
                                                    <span class="title">199</span>
                                                </a>
                                                <a class="action comment" href="javascript:;" >
                                                    <span class="icon"></span>
                                                    <span class="title">29</span>
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
                                    <div class="thumb-box" style='background-image: url("https://user-gold-cdn.xitu.io/2017/9/25/f3abf1ed9ec6283d5e705987abaa1986?imageView2/1/w/150/h/150/q/85/interlace/1"); background-size: cover;'></div>
                                </div>
                            </a>
                        </div>
                    </li>
                </ul>
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
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
