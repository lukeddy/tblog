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
                    <li class='active'>新建栏目</li>
                </ul>
            </div>
            <div class='inner'>
                <div class="topic_content">
                    <div class="markdown-text">
                        <h3>已有栏目 <a href="${contextPath}/cat/create">新增</a> </h3>
                        <jsp:include page="../inc/msgbox.jsp"></jsp:include>
                        <c:if test="${not empty pager and not empty pager.content}">
                            <table class="table">
                                <tr>
                                    <th>栏目名称</th>
                                    <th>目录</th>
                                    <th>创建日期</th>
                                    <th>更新日期</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${pager.content}" var="cat">
                                    <tr>
                                        <td>${cat.catName}</td>
                                        <td>${cat.catDir}</td>
                                        <td>${cat.createAtFormatted}</td>
                                        <td>${cat.updateAtFormatted}</td>
                                        <td>
                                            <a href="${contextPath}/cat/edit/${cat.id}">修改</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <nav aria-label="Page navigation">
                                <span>总共${pager.totalPages}页</span>
                                <ul class="pagination">
                                    <!--在这里统一定义分页全局属性-->
                                    <c:set var="currentPage" value="${pager.number+1}"/>
                                    <c:set var="totalPages" value="${pager.totalPages}"/>
                                    <c:set var="pageStart" value="${currentPage - 2 > 0 ? currentPage - 2 : 1}"/>
                                    <c:set var="pageEnd" value="${pageStart + 4 >= totalPages ? totalPages : pageStart + 4}"/>
                                    <c:set var="pageSize" value="${pager.size}"/>
                                    <c:set var="baseURL" value="${contextPath}/cat/list"/>

                                    <c:if test="${currentPage==1}">
                                        <li class="disabled"><span aria-hidden="true">&laquo;</span></li>
                                    </c:if>
                                    <c:if test="${currentPage>1}">
                                        <li>
                                            <a href="${baseURL}?pageSize=${pageSize}&pageNO=1" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <c:if test="${pageStart>1}">
                                        <li><a>...</a></li>
                                    </c:if>
                                    <c:forEach begin="${pageStart}" end="${pageEnd}" varStatus="status">
                                        <c:if test="${currentPage==status.index}">
                                            <li class="active"><a href="${baseURL}?pageSize=${pageSize}&pageNO=${status.index}">${status.index}</a></li>
                                        </c:if>
                                        <c:if test="${currentPage!=status.index}">
                                          <li><a href="${baseURL}?pageSize=${pageSize}&pageNO=${status.index}">${status.index}</a></li>
                                         </c:if>
                                    </c:forEach>
                                    <c:if test="${pageEnd<totalPages}">
                                        <li><a>...</a></li>
                                    </c:if>

                                    <c:if test="${currentPage==totalPages}">
                                        <li class='disabled'><a>»</a></li>
                                    </c:if>
                                    <c:if test="${currentPage<totalPages}">
                                        <li>
                                            <a href="${baseURL}?pageSize=${pageSize}&pageNO=${totalPages}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </c:if>
                        <c:if test="${empty pager.content}">
                            <p class="text-center">还没有创建栏目</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>