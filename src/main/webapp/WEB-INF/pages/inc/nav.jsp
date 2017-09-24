<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand hidden-sm" href="/">
                <span class="tblog-logo">tBlog</span>
            </a>
        </div>
        <form id='search_form' class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control search-query" placeholder="">
            </div>
        </form>
        <div class="navbar-collapse collapse pull-right" style="height: 1px;">
            <ul class="nav navbar-nav">
                <li><a href="/">首页</a></li>
                <%--<li class="dropdown">--%>
                <%--<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Bootstrap2中文文档<b class="caret"></b></a>--%>
                <%--<ul class="dropdown-menu">--%>
                <%--<li class="">--%>
                <%--<a href="http://v2.bootcss.com/getting-started.html" target="_blank">新手入门</a>--%>
                <%--</li>--%>
                <%--<li class="">--%>
                <%--<a href="http://v2.bootcss.com/scaffolding.html" target="_blank">API</a>--%>
                <%--</li>--%>
                <%--<li class="">--%>
                <%--<a href="http://v2.bootcss.com/base-css.html" target="_blank">注册</a>--%>
                <%--</li>--%>
                <%--<li class="">--%>
                <%--<a href="http://v2.bootcss.com/components.html" target="_blank">登录</a>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="dropdown">--%>
                <%--<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Bootstrap3中文文档<b class="caret"></b></a>--%>
                <%--<ul class="dropdown-menu">--%>
                <%--<li>--%>
                <%--<a href="http://v3.bootcss.com/getting-started/" target="_blank">起步</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="http://v3.bootcss.com/css/" target="_blank">CSS</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="http://v3.bootcss.com/components/" target="_blank">组件</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="http://v3.bootcss.com/javascript/" target="_blank">JavaScript插件</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="http://v3.bootcss.com/customize/" target="_blank">定制</a>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</li>--%>
                <li><a href="${contextPath}/about">关于</a></li>
                <li><a href="${contextPath}/register">注册</a></li>
                <li><a href="${contextPath}/login">登陆</a></li>
                <c:if test="${not empty loginUser}">
                    <li><a href="${contextPath}/cat/list">栏目管理</a></li>
                    <li><a href="${contextPath}/topic/list">帖子管理</a></li>
                    <li><a href="${contextPath}/logout">退出</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>