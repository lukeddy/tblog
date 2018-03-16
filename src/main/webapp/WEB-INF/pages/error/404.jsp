<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <div class='panel'>
            <div class='inner'>
                <div class="topic_content text-center">
                    <h2>404 Not Found!</h2>
                    <img class="img-404" src="${contextPath}/static/images/404.gif" title="404" alt="404 Not Found!"/>
                    <p>返回<a href="${contextPath}/">首页</a></p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
