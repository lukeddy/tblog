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
                    <li><a href='${contextPath}/cat/list'>管理栏目</a><span class='divider'></span></li>
                    <li class='active'>新建栏目</li>
                </ul>
            </div>
            <div class='inner'>
                <div class="topic_content">
                    <div class="markdown-text">
                        <h3>修改栏目</h3>
                        <div class="row">
                            <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                            <form method="post" action="${contextPath}/cat/edit/${cat.id}">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">栏目名称:</div>
                                        <input type="text" class="form-control" name="catName" value="${cat.catName}" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">目录名称（小写英文字母）:</div>
                                        <input type="text" class="form-control" name="catDir" value="${cat.catDir}" placeholder="" readonly="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">目录简介:</div>
                                        <textarea name="catDesc" class="form-control" rows="4" placeholder="请输入栏目简介，方便SEO优化">${cat.catDesc}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input type="hidden" name="id" value="${cat.id}">
                                        <button class="btn btn-success" type="submit">更新</button>
                                        <button class="btn btn-default" type="reset">重置</button>
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