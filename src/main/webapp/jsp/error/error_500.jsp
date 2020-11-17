<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/main.css">
    <title>500</title>
</head>
<body>
<div class="e500">
    <a class="btn btn-outline-info" href="controller?command=welcome_page" style="margin-top: 50px; margin-left: 50px"><fmt:message
            key="header.home"/></a>
</div>
</body>
</html>
