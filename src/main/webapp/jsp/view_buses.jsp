<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vehicles</title>
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
            <th><fmt:message key="view_buses.rate"/></th>
            <th><fmt:message key="view_buses.status"/></th>
            <c:if test="${user.role.ordinal() == 1}">
                <th><fmt:message key="view_buses.change_status"/></th>
            </c:if>
            <c:if test="${user.role.ordinal() == 0}">
                <th><fmt:message key="view_buses.name"/></th>
                <th><fmt:message key="view_buses.surname"/></th>
                <th><fmt:message key="view_buses.appoint_driver"/></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${buses}" var="bus">
            <tr>
                <td>${bus.key.getBrand()}</td>
                <td>${bus.key.getModel()}</td>
                <td>${bus.key.getNumberOfSeats()}</td>
                <td>${bus.key.getRegistrationNumber()}</td>
                <td>${bus.key.getRate()}</td>
                <td>${bus.key.getStatus()}</td>
                <c:if test="${user.role.ordinal() == 1}">
                    <td>
                        <form action="controller" method="post">
                            <p><input type="radio" name="bus_status_id" value="0"><fmt:message
                                    key="view_buses.status_ready"/></p>
                            <p><input type="radio" name="bus_status_id" value="1"><fmt:message
                                    key="view_buses.status_on_repair"/></p>
                            <input type="hidden" name="bus_id" value="${bus.key.getBusId()}">
                            <input type="hidden" name="command" value="change_bus_status">
                            <input type="submit" value="<fmt:message key="view_buses.change_status"/>" class="btn btn-outline-info">
                        </form>
                    </td>
                </c:if>
                <c:if test="${user.role.ordinal() == 0}">
                    <td>${bus.value.getName()}</td>
                    <td>${bus.value.getSurname()}</td>
                    <td>
                        <form action="controller">
                            <input type="hidden" name="bus_id" value="${bus.key.getBusId()}">
                            <input type="hidden" name="command" value="choose_driver">
                            <input type="submit" value="<fmt:message key="view_buses.appoint_driver"/>"
                                   class="btn btn-outline-info">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="include/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
