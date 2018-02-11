<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- meta -->
    <meta charset="utf-8"/>
    <meta name='description' content='TBlog社区'>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="keywords" content="nodejs, node, express, connect, socket.io"/>
    <meta name="referrer" content="always">
    <meta name="author" content="echo" />
    <link title="RSS" type="application/rss+xml" rel="alternate" href="/rss"/>
    <link rel="icon" href="${contextPath}/static/images/favico.png" type="image/x-icon"/>
    <!-- style -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/static/js/editormd/css/editormd.css" rel="stylesheet">
    <link href="${contextPath}/static/css/site.css" rel="stylesheet">
    <c:if test="${not empty param.title}">
        <title>${param.title}</title>
    </c:if>
    <c:if test="${empty param.title}">
        <title>TBlo社区</title>
    </c:if>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>