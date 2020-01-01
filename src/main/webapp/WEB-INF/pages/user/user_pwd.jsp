<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="container main">
    <div class="row">
        <div class="col-md-3 left sidebar">
            <jsp:include page="user_sidebar.jsp">
                <jsp:param name="activeTab" value="changepwd"/>
            </jsp:include>
        </div>
        <div class="col-md-9">
            <ul class="breadcrumb">
                <li><a href="${contextPath}/"><i class="glyphicon glyphicon-home"></i> 首页</a></li>
                <li class="active">修改密码</li>
            </ul>
            <div class="wrapper">
                <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                <form:form action="${contextPath}/user/changePwd" cssClass="form-horizontal" modelAttribute="changePwdForm" method="post">
                    <div class="form-group">
                        <label for="oldPwd" class="col-md-2 control-label">当前密码</label>
                        <div class="col-lg-6">
                            <form:password path="oldPwd" cssClass="form-control" id="oldPwd"/>
                            <form:errors path="oldPwd" cssClass="error"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newPwd" class="col-md-2 control-label">新密码</label>
                        <div class="col-lg-6">
                            <form:password path="newPwd" cssClass="form-control" id="newPwd"/>
                            <form:errors path="newPwd" cssClass="error"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="repeatNewPwd" class="col-md-2 control-label">新密码确认</label>
                        <div class="col-lg-6">
                            <form:password path="repeatNewPwd" cssClass="form-control" id="repeatNewPwd"/>
                            <form:errors path="repeatNewPwd" cssClass="error"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="hidden" name="uid" value="${loginUser.id}">
                            <input type="hidden" name="username" value="${loginUser.username}">
                            <button type="submit" class="btn btn-primary">修改密码</button>
                        </div>
                    </div>
                </form:form>
            </div>

        </div>

    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>