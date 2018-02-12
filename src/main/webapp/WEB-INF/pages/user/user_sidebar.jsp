<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="activeTab" value="userinfo"/>
<div class="list-group">
    <div class="list-group-item">
        <div class="pull-left avatar">
        <c:if test="${loginUser.avatarURLByUploaded}">
            <img class="img-circle" src="${contextPath}/upload/getImage/${loginUser.avatarURL}">
        </c:if>
        <c:if test="${!loginUser.avatarURLByUploaded}">
            <img class="img-circle" src="${loginUser.avatarURL}">
        </c:if>
        </div>
        <div class="pull-left info">
            <h4 style="margin-bottom: 0px;">${loginUser.username}</h4>
            <p><a href="mailto:${loginUser.email}">${loginUser.email}</a></p>
        </div>
        <div class="clearfix"></div>
    </div>
    <a href="${contextPath}/user/info/${loginUser.username}" class='list-group-item heading <c:if test="${'userinfo'==param.activeTab}">active</c:if>'>个人资料</a>
    <a href="${contextPath}/user/changePwd" class='list-group-item <c:if test="${'changepwd'==param.activeTab}">active</c:if>'>修改密码</a>
    <a href="${contextPath}/user/changeAvatar" class='list-group-item <c:if test="${'changeavatar'==param.activeTab}">active</c:if>'>修改头像</a>
    <div class="list-group-item">&nbsp;</div>
</div>