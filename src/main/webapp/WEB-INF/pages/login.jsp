<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:include page="inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class='panel'>
            <div class='header'>
                <ul class='breadcrumb'>
                    <li><a href='/'>主页</a><span class='divider'>/</span></li>
                    <li class='active'>登录</li>
                </ul>
            </div>
            <div class='inner'>
                <div class="row"  id="login-box" style="width:480px;margin:0 auto 5% auto;">
                    <form method="post" action="">
                        <h3 class="form-signin-header text-center">登录TBlog</h3>
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>出错了!</strong> 用户名或者密码错误!
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">用户名:</div>
                                <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
                                <input type="password" class="form-control" name="pwd" id="password" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">验证码:</div>
                                <input type="text" class="form-control" style="display:inline-block;width:120px;margin-right:13px;" name="validateCode" id="validateCode" placeholder="请输入验证码">
                                <img id="validateCodeImg" src="${contextPath}/images/vcode.jpg"/>&nbsp;&nbsp;<a href="#" onclick="javascript:refreshValidateCode();">看不清？</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="pull-right"></div>
                        </div>
                        <button class="btn btn-lg btn-success btn-block" type="submit">登录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="inc/footer.jsp"></jsp:include>