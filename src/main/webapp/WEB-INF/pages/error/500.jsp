<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class='container main'>
    <div class='col-md-9'>
        <div class='panel'>
            <div class='inner'>
                <div class="topic_content text-center">
                    <h2>500 Internal Server Error!</h2>
                    <img class="img-500" src="${contextPath}/static/images/500.png" title="500: internal error" alt="500: internal error" />
                    <p>返回<a href="${contextPath}/">首页</a></p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>


