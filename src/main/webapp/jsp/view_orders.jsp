<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="date" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <title><fmt:message key="view_orders.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <c:if test="${wrong_data}">
        <fmt:message key="wrong_data"/>
    </c:if>
    <div class="container-fluid">
        <table class="table table-info account">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="view_orders.order_id"/></th>
                <th><fmt:message key="view_orders.number_of_seats"/></th>
                <th><fmt:message key="view_orders.start_date"/></th>
                <th><fmt:message key="view_orders.end_date"/></th>
                <th><fmt:message key="view_orders.start_point"/></th>
                <th><fmt:message key="view_orders.end_point"/></th>
                <th><fmt:message key="view_orders.distance"/></th>
                <th><fmt:message key="view_orders.status"/></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.numberOfSeats}</td>
                    <td><date:todate date="${order.startDate}"/></td>
                    <td><date:todate date="${order.endDate}"/></td>
                    <td>${order.startPoint}</td>
                    <td>${order.endPoint}</td>
                    <td>${order.distance}</td>
                    <td>${order.status}</td>
                    <c:if test="${user.role.ordinal() == 1 && order.status.ordinal() == 1}">
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="start_trip">
                                <input type="hidden" name="order_id" value="${order.orderId}">
                                <input type="hidden" name="user_id" value="${user.userId}">
                                <input type="submit" value="<fmt:message key="view_bids.start_trip"/>" class="btn btn-outline-info">
                            </form>
                        </td>
                    </c:if>
                    <td>
                        <c:if test="${user.role.ordinal() == 0}">
                            <form action="/controller" method="post">
                                <input type="hidden" name="command" value="appoint_bus">
                                <input type="hidden" name="order_id" value="${order.orderId}">
                                <input type="submit" value="appoint" class="btn btn-outline-info">
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
