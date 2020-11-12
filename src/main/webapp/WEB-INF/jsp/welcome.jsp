<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pgn" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="i18n\messages"/>
    <title><fmt:message key="welcome.welcome"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/main.css">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div class="content">
    <h3>
        <fmt:message key="welcome.our_cars"/>
    </h3>
    <div class="container-fluid">
        <div class="row">
            <c:forEach items="${buses}" var="bus">
                <div class="col-3">
                    <div class="card" style="width: 18rem; margin-top: 20px">
                        <img src="images/${bus.imageName}.jpg" class="card-img-top" alt="..." width="18rem"
                             height="180px">
                        <div class="card-body">
                            <h5 class="card-title">Марка: ${bus.brand}</h5>
                            <h6 class="card-subtitle">Модель: ${bus.model}</h6>
                            <p class="card-text">Количество пасажирских мест:${bus.numberOfSeats}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="row justify-content-end">
            <div class="col-auto">
                <div class="btn-group" role="group" style="padding-top: 20px; padding-bottom: 50px">
                    <pgn:pgn pageAmount="${number_pages}"/>
                </div>
            </div>
            <div class="col-auto">
                <div class="btn-group" style="padding-top: 20px; padding-bottom: 50px">
                    <button type="button" class="btn btn-outline-info dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        ${number_items}
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="controller?number_items=4&command=welcome_page">4</a>
                        <a class="dropdown-item" href="controller?number_items=8&command=welcome_page">8</a>
                        <a class="dropdown-item" href="controller?number_items=16&command=welcome_page">16</a>
                        <div class="dropdown-divider"></div>
                    </div>
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
</body>
</html>
