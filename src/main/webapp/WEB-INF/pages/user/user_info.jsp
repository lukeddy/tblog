<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="container main">
    <div class="row">
        <div class="col-md-3 left sidebar">
            <jsp:include page="user_sidebar.jsp">
                <jsp:param name="activeTab" value="userinfo"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <ul class="breadcrumb">
                <li><a href="${contextPath}/"><i class="glyphicon glyphicon-home"></i> 首页</a></li>
                <li class="active">个人资料</li>
            </ul>
            <div class="wrapper">
                <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                <form:form action="${contextPath}/user/info/${loginUser.username}" modelAttribute="userInfoForm" method="post" cssClass="form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">用户名</label>
                        <div class="col-lg-6">
                            <p class="form-control-static">${loginUser.username}</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="col-lg-2 control-label">电子邮件</label>
                        <div class="col-lg-10">
                            <form:input path="email" cssClass="form-control" id="email"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="website" class="col-lg-2 control-label">个人网站</label>
                        <div class="col-lg-10">
                            <form:input path="website" cssClass="form-control" id="website"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="location" class="col-lg-2 control-label">所在地</label>
                        <div class="col-lg-10">
                            <form:input path="location" cssClass="form-control" id="location" placeholder="城市名即可，如苏州"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="slogan" class="col-lg-2 control-label">签名</label>
                        <div class="col-lg-10">
                            <form:input path="slogan" cssClass="form-control" id="slogan"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="selfDesc" class="col-lg-2 control-label">个人简介</label>
                        <div class="col-lg-10">
                            <form:textarea path="selfDesc" cssClass="form-control"></form:textarea>
                        </div>
                    </div>
                    <hr>

                    <div class="form-group">
                        <label for="githubUsername" class="col-lg-2 control-label">GitHub用户名</label>
                        <div class="col-lg-10">
                            <form:input path="socialInfo.githubUsername" cssClass="form-control" id="githubUsername"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="weiboUsername" class="col-lg-2 control-label">新浪微博</label>
                        <div class="col-lg-10">
                            <div class="input-group">
                                <span class="input-group-addon">http://weibo.com/</span>
                                <form:input path="socialInfo.weiboUsername" cssClass="form-control" id="weiboUsername"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <form:hidden path="id"/>
                            <input type="submit" class="btn btn-primary" value="保存设置">
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>