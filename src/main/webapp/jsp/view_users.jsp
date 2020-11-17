<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="i18n\messages"/>
    <title>Users</title>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <c:if test="${wrong_data}">
        <fmt:message key="wrong_data"/>
    </c:if>
    <table class="table table-info account">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="view_users.login"/></th>
            <th><fmt:message key="view_users.name"/></th>
            <th><fmt:message key="view_users.surname"/></th>
            <th><fmt:message key="view_users.phone_number"/></th>
            <th><fmt:message key="view_users.role"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.phoneNumber}</td>
                <td>
                    <c:if test="${user.role.ordinal() == 1}">
                        <fmt:message key="view_users.driver"/>
                    </c:if>
                    <c:if test="${user.role.ordinal() == 2}">
                        <fmt:message key="view_users.user"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${user.isActive == 1}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="change_is_active">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="is_active" value="0">
                            <input type="submit" value="<fmt:message key="view_users.block"/>"
                                   class="btn btn-outline-info">
                        </form>
                    </c:if>
                    <c:if test="${user.isActive == 0}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="change_is_active">
                            <input type="hidden" name="user_login" value="${user.login}">
                            <input type="hidden" name="is_active" value="1">
                            <input type="submit" value="<fmt:message key="view_users.unblock"/>"
                                   class="btn btn-outline-info">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
