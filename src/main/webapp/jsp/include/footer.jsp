<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid" style="background-color: #2a7b9b; height: 100px;">
    <div class="row justify-content-center">
        <div class="col-4 align-self-center" style="padding-top: 30px">
            <fmt:message key="footer.message"/>
        </div>
    </div>
</div>
</body>
</html>
