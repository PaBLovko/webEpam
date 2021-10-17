<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title><fmt:message key="add.new.order"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <a class="link_acc nav-link" href="${request.contextPath}controller?command=admin_account"><fmt:message key="return"/></a>
    <table class="table table-striped">
        <tbody>
        <tr>
            <td><fmt:message key="user.id.is"/>  <c:out value="${ userForOrder.id }"/>, <c:out value="${ userForOrder.surname }"/> <c:out
                    value="${ userForOrder.name }"/> <c:out value="${ userForOrder.patronymic }"/></td>
        </tr>
        </tbody>
    </table>
    <br>
    <div>
        <fmt:message key="new.order"/>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="amount"/></th>
            <th><fmt:message key="cost"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        </thead>
        <c:forEach var="element" items="${basketProducts}" varStatus="status">
            <tr>
                <td><c:out value="${ element.dish.id }"/></td>
                <td><c:out value="${ element.dish.name }"/></td>
                <td><c:out value="${ element.dish.price }"/>0 BYN</td>
                <td>x<c:out value="${ element.amount }"/></td>
                <td><c:out value="${ element.cost }"/>0 BYN</td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myModalAdd${ element.id }">
                        <fmt:message key="delete"/>
                    </button>
                    <div class="modal fade" id="myModalAdd${ element.id }">
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
                                        <input type="hidden" name="command" value="admin_delete_dish_from_order">
                                        <input type="submit" class="btn btn-secondary" value="<fmt:message key="delete"/>">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                        </button>
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
            </td>
            <td>
            </td>
            <td>
            </td>
            <td>
                <fmt:message key="total"/>
            </td>
            <td>
                <c:out value="${ total }0 BYN"/>
            </td>
            <td>
                <form action="controller" method="POST">
                    <input type="hidden" name="command" value="admin_product_to_order">
                    <input type="hidden" name="total" value="${ total }">
                    <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="to.order"/>">
                </form>
            </td>
        </tr>
    </table>
    <br>
    <br>
</div>
<div class="container-fluid pt-3">
    <div>
        <fmt:message key="our.dishes"/>
    </div>
    <br>
    <div class="wrong_message"><c:out value="${ wrong }"/></div>
    <div class="right_message"><c:out value="${ right }"/></div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="picture"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="weight"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="description"/></th>
            <th><fmt:message key="amount"/></th>
            <th><fmt:message key="action"/></th>
        </tr>
        </thead>
        <c:forEach var="element" items="${dishes}" varStatus="status">
            <tr>
                <td><c:out value="${ element.id }"/></td>
                <td><img class="image_dish_basket" src="<c:out value="${ element.picture }"/>"></td>
                <td><c:out value="${ element.name }"/></td>
                <td><c:out value="${ element.weight }"/></td>
                <td><c:out value="${ element.price }"/></td>
                <td><c:out value="${ element.description }"/></td>
                <form action="controller" method="POST">
                    <td class="product_amount">
                        <input type="number" min="1" max="99" value="1" name="dishAmount"
                               class="input_center">
                    </td>
                    <td>
                        <input type="hidden" name="dishId" value="${ element.id }">
                        <input type="hidden" name="dishPrice" value="${ element.price }">
                        <input type="hidden" name="command" value="admin_add_dish_to_order">
                        <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="add.to.basket"/>">
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>
</body>
</html>