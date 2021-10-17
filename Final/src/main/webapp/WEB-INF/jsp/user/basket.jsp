<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="by.training.webapplication.bean.User" scope="application"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        <%@include file="../../../css/style.css" %>
        <%@include file="../../../css/bootstrap.min.css" %>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script>
        <%@include file="../../../js/bootstrap.js" %>
    </script>
    <title><fmt:message key="my.basket"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/login.jsp"/>
<jsp:include page="../common/menu.jsp"/>
<div class="container-fluid mt-3">
    <br>
    <br>
    <h2><fmt:message key="my.basket"/></h2>
    <br>
    <ul class="nav">
        <li class="li_admin nav-item">
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="add_order">
                <input type="hidden" name="total" value="${ total }">
                <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="to.order"/>">
            </form>
        </li>
        <li class="li_admin nav-item">
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="clear_basket">
                <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="clear.basket"/>">
            </form>
        </li>
    </ul>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="dish"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="weight"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="amount"/></th>
            <th><fmt:message key="cost"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${basketProducts}" varStatus="status">
            <tr>
                <td><img class="image_dish_basket" src="<c:out value="${ element.dish.picture }"/>"></td>
                <td><c:out value="${ element.dish.name }"/></td>
                <td><c:out value="${ element.dish.weight }"/> <fmt:message key="grams"/></td>
                <td><c:out value="${ element.dish.price }"/>0 BYN</td>
                <td><c:out value="x${ element.amount }"/></td>
                <td><c:out value="${ element.cost }"/>0 BYN</td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myModalFeed${ element.id }">
                        <fmt:message key="delete"/>
                    </button>
                    <div class="modal fade" id="myModalFeed${ element.id }">
                        <div class="modal-dialog modal-dialog-centered modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <fmt:message key="delete.dish.from.basket"/>
                                </div>
                                <form action="controller" method="POST">
                                    <div class="modal-footer">
                                        <input type="hidden" name="basketProductId" value="${ element.id }"/>
                                        <input type="hidden" name="productCost" value="${ element.cost }"/>
                                        <input type="hidden" name="basketId" value="${ element.basket.id }"/>
                                        <input type="hidden" name="basketTotal" value="${ element.basket.total }"/>
                                        <input type="hidden" name="command" value="delete_dish_from_basket">
                                        <input type="submit" class="btn btn-secondary" value="<fmt:message key="delete"/>">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <fmt:message key="total"/>
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
                <c:out value="${ total }0 BYN"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>
</body>
</html>