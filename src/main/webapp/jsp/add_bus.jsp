<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n\messages"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/main.css">
    <title>Add vehicle</title>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <form action="upload" method="post" enctype="multipart/form-data">
        <div class="container-fluid">
            <div class="row field">
                <div class="col-3">
                    <fmt:message key="add_bus.enter_brand"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="brand" placeholder="<fmt:message key="add_bus.brand"/>"
                           value="${parameters.brand}" pattern="[\d\p{LC}]{3,30}">
                    <small class="form-text text-muted"><fmt:message key="add_bus.brand_message"/></small>
                </div>
                <c:if test="${parameters.brand == ''}">
                    <div class="col-auto">
                        <fmt:message key="add_bus.brand_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-3">
                    <fmt:message key="add_bus.enter_model"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="model" placeholder="<fmt:message key="add_bus.model"/>"
                           value="${parameters.model}" pattern="[\d\p{LC}\-]{1,30}">
                    <small class="form-text text-muted"><fmt:message key="add_bus.model_message"/></small>
                </div>
                <c:if test="${parameters.model == ''}">
                    <div class="col-auto">
                        <fmt:message key="add_bus.model_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-3">
                    <fmt:message key="add_bus.enter_registration_number"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="registration_number"
                           placeholder="<fmt:message key="add_bus.registration_number"/>"
                           value="${parameters.registration_number}" pattern="\d{4}\s\p{LC}{2}-\d">
                    <small class="form-text text-muted"><fmt:message key="add_bus.registration_number_message"/></small>
                </div>
                <c:if test="${parameters.registration_number == ''}">
                    <div class="col-auto">
                        <fmt:message key="add_bus.registration_number_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-3">
                    <fmt:message key="add_bus.enter_number_of_seats"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="number_of_seats"
                           placeholder="<fmt:message key="add_bus.number_of_seats"/>"
                           value="${parameters.number_of_seats}" pattern="([8-9]|[1-3][0-9]|[4][0-5])">
                    <small class="form-text text-muted"><fmt:message key="add_bus.number_of_seats_message"/></small>
                </div>
                <c:if test="${parameters.number_of_seats == ''}">
                    <div class="col-auto">
                        <fmt:message key="add_bus.number_of_seats_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-3">
                    <fmt:message key="add_bus.enter_rate"/>
                </div>
                <div class="col-4">
                    <input required type="text" name="rate" placeholder="<fmt:message key="add_bus.rate"/>"
                           value="${parameters.rate}" pattern="\d{1,7}.*\d{0,2}">
                    <small class="form-text text-muted"><fmt:message key="add_bus.rate_message"/></small>
                </div>
                <c:if test="${parameters.rate == ''}">
                    <div class="col-auto">
                        <fmt:message key="add_bus.rate_message"/>
                    </div>
                </c:if>
            </div>
            <div class="row field">
                <div class="col-3">
                    <fmt:message key="add_bus.upload_image"/>
                </div>
                <div class="col-4">
                    <input required type="file" name="image" accept="image/jpeg">
                </div>
            </div>
            <div class="row field">
                <div class="col-3">
                    <input type="hidden" name="command" value="save_bus">
                    <input type="submit" value="<fmt:message key="add_bus.add"/>" class="btn btn-outline-info">
                </div>
                <div class="col-3">
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
