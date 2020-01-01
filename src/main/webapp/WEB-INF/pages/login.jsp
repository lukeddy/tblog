<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'>主页</a><span class='divider'></span></li>
            <li class='active'>登录</li>
        </ul>
        <div class="row wrapper">
            <div class="col-sm-3">&nbsp;</div>
            <div class="col-sm-6">
                <jsp:include page="inc/msgbox.jsp"></jsp:include>
                <form:form action="${contextPath}/login" modelAttribute="loginForm" method="post">
                        <h3 class="form-signin-header text-center">登录TBlog</h3>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">用户名:</div>
                                <form:input path="username" cssClass="form-control"/>
                            </div>
                            <form:errors path="username" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
                                <form:password path="password" cssClass="form-control"/>
                            </div>
                            <form:errors path="password" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">验证码:</div>
                                <form:input path="validateCode" cssClass="form-control" cssStyle="display:inline-block;width:120px;margin-right:13px;" id="validateCode" />
                                <img id="validateCodeImg" src="${contextPath}/validateCode"/>&nbsp;&nbsp;<a href="#" onclick="javascript:reloadValidateCode();">看不清？</a>
                            </div>
                            <form:errors path="validateCode" cssClass="error"/>
                        </div>
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                            <div class="btn-group" role="group">
                                <button class="btn btn-success" type="submit">登录</button>
                            </div>
                            <div class="btn-group" role="group">
                                <button class="btn btn-default" type="reset">重置</button>
                            </div>
                        </div>
                        <br>
                        <p>没有账户？点击<a href="${contextPath}/register">注册</a></p>
                    </form:form>
            </div>
            <div class="col-sm-3">&nbsp;</div>
        </div>

    </div>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
<script>
    function reloadValidateCode() {
        $("#validateCodeImg").attr("src", "${contextPath}/validateCode?data=" + new Date() + Math.floor(Math.random() * 24));
    }
</script>