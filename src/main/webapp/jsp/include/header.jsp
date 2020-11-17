<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="i18n\messages"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/main.css">
    <title>Title</title>
</head>
<body>
<div class="nav_bar">
    <div class="container-fluid ">
        <div class="row">
            <div class="col-3 align-self-start">
                <c:choose>
                    <c:when test="${empty user}">
                        <fmt:message key="header.hello_message"/> <fmt:message key="header.guest"/><br>
                        <a href="controller?command=registration_page"><fmt:message key="header.registration"/></a>
                    </c:when>
                    <c:when test="${not empty user}">
                        <fmt:message key="header.hello_message"/> ${user.name}!
                        <p><a class="btn btn-outline-info" href="controller?command=logout"><fmt:message
                                key="header.logout"/></a></p>
                    </c:when>
                </c:choose>
                <c:if test="${empty user}">
                    <form action="controller?command=authorization" method='post' style="margin-top: 15px">
                        <table class="tab">
                            <tr>
                                <td><fmt:message key="header.login"/></td>
                                <td><input type='text' name='login'
                                           placeholder="<fmt:message key="header.login"/>">
                                </td>
                            </tr>
                            <tr>
                                <td><fmt:message key="header.password"/></td>
                                <td><input type="password" name='password'
                                           placeholder="<fmt:message key="header.password"/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input class="btn btn-outline-info" name="submit" type="submit"
                                           value="<fmt:message key="header.enter"/>"/>
                                </td>
                                <td>
                                    <div style="color: red">
                                        <c:if test="${message.equals('login')}">
                                            <fmt:message key="header.error_login_message"/>
                                        </c:if>
                                        <c:if test="${message.equals('password')}">
                                            <fmt:message key="header.error_password_message"/>
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </c:if>
            </div>
            <div class="col-2 align-self-start justify-content-start">
                <div class="navbar navbar-light"
                     style="background-color: #2a7b9b; margin-top: 20px; margin-left: -20px">
                    <div class="btn-group" role="group">
                        <a class="btn btn-outline-info" href="controller?command=welcome_page"><fmt:message
                                key="header.home"/></a>
                        <c:if test="${not empty user}">
                            <a class="btn btn-outline-info" href="controller?command=account_page"><fmt:message
                                    key="welcome.account"/></a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-3">
            </div>
            <div class="col-2 align-self-center" style="">
                <img class="img" src="${pageContext.request.contextPath}/static/img/logo_image.png" alt="" height="180">
            </div>
            <div class="col-1">
            </div>
            <div class="col-1 align-self-start">
                <p>
                    <a href="/controller?command=change_language&lang=ru">
                        <img src="/static/img/Russia-Flag.png" height="30" alt="Русский">
                    </a>
                    <a href="/controller?command=change_language&lang=en">
                        <img src="/static/img/United-Kingdom-flag.png" height="30" alt="English">
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</body>
</html>
