<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <div class='panel'>
            <div class='inner'>
                <div class="topic_content text-center">
                    <h2>403 Forbidden</h2>
                    <img src="${contextPath}/static/images/403.png" alt="">
                    <p>返回<a href="${contextPath}/">首页</a>或者<a href="${contextPath}/login">登陆</a></p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>