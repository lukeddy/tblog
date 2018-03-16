<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand hidden-sm" href="${contextPath}/">
            <span class="tblog-leaf-logo">&nbsp;</span>
        </a>
        <form class="navbar-form pull-right" action="${contextPath}/search">
            <div class="form-group hidden-xs">
                <div class="input-group">
                    <div class="input-group-addon"><i class="glyphicon glyphicon-search"></i></div>
                    <input type="text" name="keywords" class="form-control" placeholder="">
                </div>
            </div>
        </form>
    </div>
    <div class="navbar-collapse collapse pull-right">
        <ul class="nav navbar-nav">
            <li><a href="${contextPath}/">首页</a></li>
            <li><a href="${contextPath}/about">关于</a></li>
            <c:if test="${empty loginUser}">
                <li><a href="${contextPath}/register">注册</a></li>
                <li><a href="${contextPath}/login">登陆</a></li>
            </c:if>
            <c:if test="${not empty loginUser}">
                <c:if test="${loginUser.username=='admin'}">
                 <li><a href="${contextPath}/cat/list">栏目管理</a></li>
                </c:if>
                <li><a href="${contextPath}/topic/list">帖子管理</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">${loginUser.username}<b class="caret"></b></a>
                    <ul class="dropdown-menu dropdown-menu-right">
                        <li><a href="${contextPath}/user/info/${loginUser.username}"><i class="glyphicon glyphicon-user"></i> 用户中心</a></li>
                        <li><a href="${contextPath}/user/changePwd"><i class="glyphicon glyphicon-edit"></i> 修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="${contextPath}/logout"><i class="glyphicon glyphicon-off"></i>退出</a></li>
                    </ul>
                </li>
            </c:if>
        </ul>
    </div>
</div>