<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Update User</title>
    <meta charset="utf-8">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="i18n\messages"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <form action="controller" method="post">
        <div class="container-fluid">
            <div class="row field">
                <div class="col-1">
                    <label for="login"><fmt:message key="update_user.login"/></label>
                </div>
                <div class="col-4">
                    <input id="login" type="text" name="login" value="${parameters.login}" required pattern="[\d\p{LC}]{4,25}">
                    <small class="form-text text-muted"><fmt:message key="update_user.wrong_login_message"/></small>
                </div>
                <div class="col-2" style="color: red">
                    <c:if test="${parameters.login.equals('') && !parameters.message.equals('login')}">
                        <fmt:message key="update_user.wrong_login_message"/>
                    </c:if>
                    <c:if test="${parameters.message.equals('login')}">
                        <fmt:message key="update_user.login_exist_message"/>
                    </c:if>
                </div>
            </div>
            <div class="row field">
                <div class="col-1">
                    <label for="name"><fmt:message key="update_user.name"/></label>
                </div>
                <div class="col-4">
                    <input id="name" type="text" name="name" value="${parameters.name}" required pattern="\p{LC}{2,35}">
                    <small class="form-text text-muted"><fmt:message key="update_user.name_message"/></small>
                </div>
                <div class="col-auto" style="color: red">
                    <c:if test="${parameters.name == ''}">
                        <fmt:message key="update_user.name_message"/>
                    </c:if>
                </div>
            </div>
            <div class="row field">
                <div class="col-1">
                    <label for="surname"><fmt:message key="update_user.surname"/></label>
                </div>
                <div class="col-4">
                    <input id="surname" type="text" name="surname" value="${parameters.surname}" required pattern="[\p{LC}-]{2,50}">
                    <small class="form-text text-muted"><fmt:message key="update_user.surname_message"/></small>
                </div>
                <div class="col-auto" style="color: red">
                    <c:if test="${parameters.surname == ''}">
                        <fmt:message key="update_user.surname_message"/>
                    </c:if>
                </div>
            </div>
            <div class="row field">
                <div class="col-1">
                    <label for="phoneNumber"><fmt:message key="update_user.phone_number"/></label>
                </div>
                <div class="col-4">
                    <input id="phoneNumber" name="phoneNumber" value="${parameters.phoneNumber}" required pattern="^375\d{9}$">
                    <small class="form-text text-muted"><fmt:message key="update_user.phone_number_message"/></small>
                </div>
                <div class="col-auto" style="color: red">
                    <c:if test="${parameters.phoneNumber == ''}">
                        <fmt:message key="update_user.phone_number_message"/>
                    </c:if>
                </div>
            </div>
            <div class="row field">
                <div class="col-1">
                    <input type="hidden" name="command" value="update_user">
                    <input type="submit" value="<fmt:message key="update_user.save"/>" class="btn-outline-info">
                </div>
            </div>

        </div>
    </form>
    <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#changePassword">
        <fmt:message key="update_user.change_password"/>
    </button>
    <div style="color: red">
        <c:if test="${parameters.message.equals('new_password')}">
            <fmt:message key="update_user.wrong_password"/>
        </c:if>
        <c:if test="${parameters.message.equals('password')}">
            <fmt:message key="update_user.password_message"/>
        </c:if>
    </div>
    <div class="modal fade" id="changePassword" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel"><fmt:message
                            key="update_user.change_password"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="controller" method="post">
                        <div class="container-fluid">
                            <div class="row field">
                                <div class="col-2">
                                    <label for="oldPassword"><fmt:message key="update_user.old_password"/></label>
                                </div>
                                <div class="col-4">
                                    <input id="oldPassword" type="password" name="oldPassword" required pattern="[\d\p{LC}]{4,16}">
                                </div>
                                <div class="col-auto">
                                </div>
                            </div>
                            <div class="row field">
                                <div class="col-2">
                                    <label for="password"><fmt:message key="update_user.new_password"/></label>
                                </div>
                                <div class="col-4">
                                    <input id="password" type="password" name="newPassword" required pattern="[\d\p{LC}]{4,16}">
                                    <small class="form-text text-muted"><fmt:message key="update_user.wrong_password"/></small>
                                </div>
                            </div>
                            <div class="row field">
                                <div class="col-2">
                                    <label for="repeatPassword"><fmt:message key="update_user.repeat_password"/></label>
                                </div>
                                <div class="col-4">
                                    <input id="repeatPassword" type="password" name="repeatPassword" required>
                                    <p id="validate-status"></p>
                                </div>
                            </div>
                            <div class="row field">
                                <div class="col-2">
                                    <c:forEach var="currentParameter" items="${parameters}">
                                        <input type="hidden" name="${currentParameter.key}"
                                               value="${currentParameter.value}">
                                    </c:forEach>
                                    <input type="hidden" name="command" value="change_password">
                                    <input type="submit" value="<fmt:message key="update_user.change_password"/>"
                                           id="submit" class="btn-outline-info">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
