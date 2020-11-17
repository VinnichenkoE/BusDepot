<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <title><fmt:message key="add_order.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <form action="controller?command=save_order" method="post">
                    <div class="container-fluid">
                        <div class="row field">
                            <div class="col-3">
                                <label for="number_of_seats"><fmt:message key="add_order.number_of_seats"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" name="number_of_seats" id="number_of_seats"
                                       value="${parameters.number_of_seats}" required pattern="([8-9]|[1-3][0-9]|[4][0-5])">
                                <small class="form-text text-muted"><fmt:message key="add_order.number_seats_error_message"/></small>
                            </div>
                            <c:if test="${parameters.number_of_seats == ''}">
                                <div class="col-auto" style="color: red">
                                    <fmt:message key="add_order.number_seats_error_message"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="row field">
                            <div class="col-3">
                                <label for="datetime"><fmt:message key="add_order.start_date"/></label>
                            </div>
                            <div class="col-4">
                                <input type="datetime-local" id="datetime" name="start_date"
                                       value="${parameters.start_date}" required>
                            </div>
                            <c:if test="${parameters.start_date == ''}">
                                <div class="col-auto" style="color: red">
                                    <fmt:message key="add_order.date_error_message"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="row field">
                            <div class="col-3">
                                <label for="endData"><fmt:message key="add_order.end_date"/></label>
                            </div>
                            <div class="col-4">
                                <input type="datetime-local" id="endData" name="end_date" value="${parameters.end_date}"
                                       required>
                            </div>
                            <c:if test="${parameters.end_date == ''}">
                                <div class="col-auto" style="color: red">
                                    <fmt:message key="add_order.date_error_message"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="row field">
                            <div class="col-3">
                                <label for="startPoint"><fmt:message key="add_order.start_point"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" name="start_point" id="startPoint" value="${parameters.start_point}"
                                       required pattern="[\p{LC}\-]{2,45}">
                                <small class="form-text text-muted"><fmt:message key="add_order.point_error_message"/></small>
                            </div>
                            <c:if test="${parameters.start_point == ''}">
                                <div class="col-auto" style="color: red">
                                    <fmt:message key="add_order.point_error_message"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="row field">
                            <div class="col-3">
                                <label for="endPoint"><fmt:message key="add_order.end_point"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" name="end_point" id="endPoint" value="${parameters.end_point}"
                                       required pattern="[\p{LC}\-]{2,45}">
                                <small class="form-text text-muted"><fmt:message key="add_order.point_error_message"/></small>
                            </div>
                            <c:if test="${parameters.start_point == ''}">
                                <div class="col-auto" style="color: red">
                                    <fmt:message key="add_order.point_error_message"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="row field">
                            <div class="col-3">
                                <label for="distance"><fmt:message key="add_order.distance"/></label>
                            </div>
                            <div class="col-4">
                                <input type="text" name="distance" id="distance" value="${parameters.distance}"
                                       required pattern="\d{1,4}">
                                <small class="form-text text-muted"><fmt:message key="add_order.distance_error_message"/></small>
                            </div>
                            <c:if test="${parameters.distance == ''}">
                                <div class="col-auto" style="color: red">
                                    <fmt:message key="add_order.distance_error_message"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="row field">
                            <div class="col-3">
                                <input type="hidden" name="user_id" value="${user.userId}">
                                <input type="submit" value="<fmt:message key="add_order.save_order"/>" class="btn btn-outline-info">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
