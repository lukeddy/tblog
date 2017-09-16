<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class='panel'>
            <div class='header'>
                <ul class='breadcrumb'>
                    <li><a href='/'>主页</a><span class='divider'></span></li>
                    <li><a href='${contextPath}/topic/list'>帖子管理</a><span class='divider'></span></li>
                    <li class='active'>新建帖子</li>
                </ul>
            </div>
            <div class='inner'>
                <div class="topic_content">
                    <div class="markdown-text">
                        <h3>新建帖子</h3>
                        <div class="row">
                            <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                            <form method="post" action="${contextPath}/topic/create">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">栏目:</div>
                                        <select id="catId" name="catId" class="form-control">
                                            <option value="" selected=""></option>
                                            <c:forEach items="${catList}" var="cat">
                                                <option value="${cat.id}">${cat.catName}</option>
                                            </c:forEach>
                                         </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">标题:</div>
                                        <textarea name="title" class="form-control" rows="3" placeholder="请输入帖子标题">${topicVo.title}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">内容:</div>
                                        <textarea name="content" class="form-control" rows="12" placeholder="请输入帖子内容">${topicVo.content}</textarea>
                                    </div>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="contentIsHTML" value="true"> 是否网页？
                                    </label>
                                    <label>
                                        <input type="checkbox" name="top" value="true"> 置顶帖？
                                    </label>
                                    <label>
                                        <input type="checkbox" name="good" value="true"> 精华帖？
                                    </label>
                                </div>

                                <div class="form-group">
                                    <div class="text-center">
                                        <input type="hidden" name="authorId" value="${loginUser.id}">
                                        <button class="btn btn-success" type="submit">新建</button>
                                        <button class="btn btn-default" type="reset">清空</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>