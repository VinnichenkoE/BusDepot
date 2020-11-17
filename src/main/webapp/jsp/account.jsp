<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Account</title>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="i18n\messages"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <nav class="navbar navbar-light justify-content-start" style="background-color: #e3f2fd;">

        <a href="/controller?command=view_orders" class="btn btn-outline-info"><fmt:message key="account.my_bids"/></a>
        <c:if test="${user.role.ordinal() <= 1}">
            <a href="controller?command=view_buses" class="btn btn-outline-info"><fmt:message
                    key="account.vehicles"/></a>
        </c:if>
        <c:if test="${user.role.ordinal() == 0 || user.role.ordinal() == 2}">
            <div class="btn-group">
                <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="account.add"/>
                </button>
                <div class="dropdown-menu">
                    <c:if test="${user.role.ordinal() == 0}">
                        <a href="controller?command=registration_page" class="btn btn-outline-info"><fmt:message
                                key="account.add_driver"/></a>
                        <a href="controller?command=add_bus_page" class="btn btn-outline-info"><fmt:message
                                key="view_buses.add_bus"/></a>
                    </c:if>
                    <c:if test="${user.role.ordinal() == 2}">
                        <a href="controller?command=add_order_page" class="btn btn-outline-info"><fmt:message
                                key="account.add_bid"/></a>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${user.role.ordinal() <= 1}">
            <a href="controller?command=view_trips" class="btn btn-outline-info"><fmt:message key="account.trips"/></a>
        </c:if>
        <c:if test="${user.role.ordinal() == 0}">
            <a href="controller?command=view_users" class="btn btn-outline-info"><fmt:message
                    key="account.view_users"/></a>
        </c:if>
        <a href="controller?command=update_user_page" class="btn btn-outline-info"><fmt:message
                key="show_profile.change"/></a>
    </nav>
    <table class="table table-info account">
        <tr>
            <td>
                <fmt:message key="show_profile.login"/>
            </td>
            <td>
                ${user.login}
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="show_profile.name"/>
            </td>
            <td>
                ${user.name}
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="show_profile.surname"/>
            </td>
            <td>
                ${user.surname}
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="show_profile.phone_number"/>
            </td>
            <td>
                ${user.phoneNumber}
            </td>
        </tr>
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
