<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <ul class='breadcrumb'>
            <li><a href='${contextPath}/'>主页</a><span class='divider'></span></li>
            <li class='active'>注册</li>
        </ul>
        <div class="row wrapper">
            <div class="col-sm-3">&nbsp;</div>
            <div class="col-sm-6">
                <jsp:include page="inc/msgbox.jsp"></jsp:include>
                <form:form action="${contextPath}/register" modelAttribute="registerForm" method="post">
                    <h3 class="form-signin-header text-center">用户注册</h3>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户名:</div>
                            <form:input path="username" cssClass="form-control" name="username" id="username" placeholder="请输入用户名"/>
                        </div>
                        <form:errors path="username" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
                            <form:password path="password" cssClass="form-control" name="password" id="password" placeholder="请输入密码"/>
                        </div>
                        <form:errors path="password" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">邮&nbsp;&nbsp;&nbsp;&nbsp;件:</div>
                            <form:input path="email"  cssClass="form-control" name="email" id="email" placeholder="电子邮件"/>
                        </div>
                        <form:errors path="email" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">验证码:</div>
                            <form:input path="validateCode" cssClass="form-control" cssStyle="display:inline-block;width:120px;margin-right:13px;" id="validateCode"/>
                            <img id="validateCodeImg" src="${contextPath}/validateCode"/>&nbsp;&nbsp;<a href="#" onclick="javascript:reloadValidateCode();">看不清？</a>
                        </div>
                        <form:errors path="validateCode" cssClass="error"/>
                    </div>
                    <div class="btn-group btn-group-justified" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <button class="btn btn-success" type="submit">注册</button>
                        </div>
                        <div class="btn-group" role="group">
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </div>
                    <br>
                    <p>已经有账户？点击<a href="${contextPath}/login">登陆</a></p>
                </form:form>
            </div>
            <div class="com-sm-3">&nbsp;</div>
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