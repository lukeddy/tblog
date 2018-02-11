<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <!--在这里统一定义分页全局属性-->
        <c:set var="currentPage" value="${pager.number+1}"/>
        <c:set var="totalPages" value="${pager.totalPages}"/>
        <c:set var="pageStart" value="${currentPage - 2 > 0 ? currentPage - 2 : 1}"/>
        <c:set var="pageEnd" value="${pageStart + 4 >= totalPages ? totalPages : pageStart + 4}"/>
        <c:set var="pageSize" value="${pager.size}"/>
        <c:set var="baseURL" value="${param.baseURL}"/>
        <c:set var="otherParams" value="${param.otherParams}"/>

        <c:if test="${currentPage==1}">
            <li class="disabled"><span aria-hidden="true">&laquo;</span></li>
        </c:if>
        <c:if test="${currentPage>1}">
            <li>
                <a href="${baseURL}?pageSize=${pageSize}&pageNO=1&${otherParams}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <c:if test="${pageStart>1}">
            <li><a>...</a></li>
        </c:if>
        <c:forEach begin="${pageStart}" end="${pageEnd}" varStatus="status">
            <c:if test="${currentPage==status.index}">
                <li class="active"><a href="${baseURL}?pageSize=${pageSize}&pageNO=${status.index}&${otherParams}">${status.index}</a></li>
            </c:if>
            <c:if test="${currentPage!=status.index}">
                <li><a href="${baseURL}?pageSize=${pageSize}&pageNO=${status.index}&${otherParams}">${status.index}</a></li>
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
                <a href="${baseURL}?pageSize=${pageSize}&pageNO=${totalPages}&${otherParams}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <li>
            <span>当前第${currentPage}页,共${totalPages}页</span>
        </li>
    </ul>
</nav>