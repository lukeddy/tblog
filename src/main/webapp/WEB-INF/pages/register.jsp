<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class='panel'>
            <div class='header'>
                <ul class='breadcrumb'>
                    <li><a href='/'>主页</a><span class='divider'>/</span></li>
                    <li class='active'>注册</li>
                </ul>
            </div>
            <div class='inner'>
                <div class="row"  id="login-box" style="width:480px;margin:0 auto 5% auto;">
                    <jsp:include page="inc/msgbox.jsp"></jsp:include>
                    <form:form action="${contextPath}/register" commandName="registerForm" method="post">
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
                        <div class="form-group">
                            <div class="pull-right"></div>
                        </div>
                        <button class="btn btn-lg btn-success btn-block" type="submit">注册</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>
<script>
    function reloadValidateCode() {
        $("#validateCodeImg").attr("src", "${contextPath}/validateCode?data=" + new Date() + Math.floor(Math.random() * 24));
    }
</script>