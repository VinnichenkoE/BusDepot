<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Appoint Driver</title>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="i18n\messages"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
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
            <th><fmt:message key="welcome.brand"/></th>
            <th><fmt:message key="welcome.model"/></th>
            <th><fmt:message key="welcome.number_of_seats"/></th>
            <th><fmt:message key="view_buses.registration_number"/></th>
            <th><fmt:message key="view_buses.status"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${bus.getBrand()}</td>
            <td>${bus.getModel()}</td>
            <td>${bus.getNumberOfSeats()}</td>
            <td>${bus.getRegistrationNumber()}</td>
            <td>${bus.getStatus()}</td>
            <td>
                <form action="controller" id="form">
                    <select name="user_id">
                        <option value=""></option>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.getUserId()}">${user.getName()} ${user.getSurname()}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="bus_id" value="${bus.getBusId()}">
                    <input type="hidden" name="command" value="appoint_driver">
                </form>
            </td>
            <td>
                <input form="form" type="submit" value="<fmt:message key="view_buses.appoint_driver"/>"
                       class="btn btn-outline-info">
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
