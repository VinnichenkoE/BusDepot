<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <title><fmt:message key="registration.registration"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <form action="/controller?command=save_user" method="post">
        <div class="container-fluid">
            <div class="row field justify-content-start">
                <div class="col-2">
                    <fmt:message key="registration.enter_login"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="login" placeholder="<fmt:message key="registration.login"/>"
                           value="${parameters.login}" pattern="[\d\p{LC}]{4,25}">
                    <small class="form-text text-muted"><fmt:message key="registration.login_message"/></small>
                </div>
                <c:if test="${parameters.login == ''}">
                    <div class="col-auto">
                        <fmt:message key="registration.login_message"/>
                    </div>
                </c:if>
                <c:if test="${not empty parameters.message}">
                    <div class="col-auto">
                        <fmt:message key="registration.login_exist_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-2">
                    <fmt:message key="registration.enter_password"/>
                </div>
                <div class="col-4">
                    <input required type="password" id="password" name="password"
                           placeholder="<fmt:message key="registration.password"/>" pattern="[\d\p{LC}]{4,16}">
                    <small class="form-text text-muted"><fmt:message key="registration.password_message"/></small>
                </div>
                <c:if test="${parameters.password == ''}">
                    <div class="col-auto">
                        <fmt:message
                                key="registration.password_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-2">
                    <fmt:message key="registration.enter_repeat_password"/>
                </div>
                <div class="col-4">
                    <input type="password" id="repeatPassword"
                           placeholder="<fmt:message key="registration.repeat_password"/>">
                </div>
                <div class="col-auto">
                    <p id="validate-status"></p>
                </div>
            </div>
            <div class="row field">
                <div class="col-2">
                    <fmt:message key="registration.enter_name"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="name" placeholder="<fmt:message key="registration.name"/>"
                           value="${parameters.name}" pattern="\p{LC}{2,35}">
                    <small class="form-text text-muted"><fmt:message key="registration.name_message"/></small>
                </div>
                <c:if test="${parameters.name == ''}">
                    <div class="col-auto">
                        <fmt:message key="registration.name_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-2">
                    <fmt:message key="registration.enter_surname"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="surname"
                           placeholder="<fmt:message key="registration.surname"/>"
                           value="${parameters.surname}" pattern="[\p{LC}-]{2,50}">
                    <small class="form-text text-muted"><fmt:message key="registration.surname_message"/></small>
                </div>
                <c:if test="${parameters.surname == ''}">
                    <div class="col-auto">
                        <fmt:message key="registration.surname_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-2">
                    <fmt:message key="registration.enter_phone_number"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="phoneNumber"
                           placeholder="<fmt:message key="registration.phone_number"/>"
                           value="${parameters.phoneNumber}" pattern="^375\d{9}$">
                    <small class="form-text text-muted"><fmt:message key="registration.phone_number_message"/></small>
                </div>
                <c:if test="${parameters.phoneNumber == ''}">
                    <div class="col-auto">
                        <fmt:message key="registration.phone_number_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-3">
                    <input type="hidden" name="role" value="${param.role}">
                    <input type="submit" id="submit" class="btn btn-outline-info"
                           value="<fmt:message key="registration.save"/>">
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="include/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
        integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
