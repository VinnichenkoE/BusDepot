<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="date" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <title><fmt:message key="appoint_bus.title"/></title>
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
            <th><fmt:message key="appoint_bus.order_id"/></th>
            <th><fmt:message key="appoint_bus.number_of_seats"/></th>
            <th><fmt:message key="appoint_bus.start_date"/></th>
            <th><fmt:message key="appoint_bus.end_date"/></th>
            <th><fmt:message key="appoint_bus.start_point"/></th>
            <th><fmt:message key="appoint_bus.end_point"/></th>
            <th><fmt:message key="appoint_bus.distance"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tr>
            <td>${order.orderId}</td>
            <td>${order.numberOfSeats}</td>
            <td><date:todate date="${order.startDate}"/></td>
            <td><date:todate date="${order.endDate}"/></td>
            <td>${order.startPoint}</td>
            <td>${order.endPoint}</td>
            <td>${order.distance}</td>
            <td>
                <form action="controller" method="post" id="appoint_form">
                    <select name="user_id">
                        <c:forEach items="${buses}" var="bus">
                            <option value="${bus.getUserId()}">${bus.getBrand()} ${bus.getModel()}
                                мест: ${bus.getNumberOfSeats()}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="command" value="appoint_order">
                    <input type="hidden" name="order_id" value="${order.orderId}">
                </form>
            </td>
            <td>
                <input type="submit" value="appoint" form="appoint_form" class="btn btn-outline-info">
            </td>
        </tr>
    </table>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
