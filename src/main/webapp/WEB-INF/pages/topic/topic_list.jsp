<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div id='main'>
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>
    <div id='content'>
        <div class='panel'>
            <div class='header'>
                <ul class='breadcrumb'>
                    <li><a href='/'>主页</a><span class='divider'></span></li>
                    <li class='active'>帖子管理</li>
                </ul>
            </div>
            <div class='inner'>
                <div class="topic_content">
                    <div class="markdown-text">
                        <h3>帖子列表 <a href="${contextPath}/topic/create">【新增】</a> </h3>
                        <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                        <c:if test="${not empty pager and not empty pager.content}">
                            <table class="table">
                                <tr>
                                    <th>帖子标题</th>
                                    <th>创建日期</th>
                                    <th>更新日期</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${pager.content}" var="topic">
                                    <tr>
                                        <td>${topic.title}</td>
                                        <td>${topic.createAtFormatted}</td>
                                        <td>${topic.updateAtFormatted}</td>
                                        <td>
                                            <a href="${contextpath}/topic/show/${topic.id}" target="_blank">查看</a>
                                            <a href="${contextPath}/topic/edit/${topic.id}">修改</a>
                                            <a href="${contextPath}/topic/del/${topic.id}">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <!--分页开始-->
                            <jsp:include page="../inc/pagination.jsp">
                                <jsp:param name="pager" value="${pager}"/>
                                <jsp:param name="baseURL" value="${contextPath}/topic/list"/>
                            </jsp:include>
                            <!--分页结束-->
                        </c:if>
                        <c:if test="${empty pager.content}">
                            <p class="text-center">还没有创建帖子</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>