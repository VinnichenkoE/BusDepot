<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="date" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <title><fmt:message key="view_trips.title"/></title>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <table class="table table-info account">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="view_trips.trip_id"/></th>
            <th><fmt:message key="view_trips.start_date"/></th>
            <th><fmt:message key="view_trips.end_date"/></th>
            <th><fmt:message key="view_trips.cost"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${trips}" var="trip">
            <tr>
                <td>${trip.tripId}</td>
                <td><date:todate date="${trip.startDate}"/></td>
                <td>
                    <c:if test="${trip.endDate != 0}">
                        <date:todate date="${trip.endDate}"/>
                    </c:if>
                </td>
                <td>${trip.cost}</td>
                <c:if test="${user.role.ordinal() == 1 && trip.endDate == 0}">
                    <td>
                        <form action="controller">
                            <input type="hidden" name="command" value="finish_trip">
                            <input type="hidden" name="trip_id" value="${trip.tripId}">
                            <input type="submit" value="finish trip" class="btn btn-outline-info">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
