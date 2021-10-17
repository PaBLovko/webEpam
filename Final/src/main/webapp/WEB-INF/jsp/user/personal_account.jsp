<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="login.client"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="${request.contextPath}controller?command=show_main_page"><fmt:message key="return"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${request.contextPath}controller?command=log_out"><fmt:message key="login.exit"/></a>
        </li>
    </ul>
    <br>
    <h2><fmt:message key="personal.information"/></h2>
    <br>
    <div class="wrong_message"><c:out value="${ wrong }"/></div>
    <div class="right_message"><c:out value="${ right }"/></div>
    <br>
    <table class="table table-striped">
        <tbody>
        <tr>
            <td><fmt:message key="surname"/></td>
            <td><c:out value="${ user.surname }"/>
            </td>
            <td>
                <button type="button" class="change-info btn_focus btn-primary" data-toggle="modal" data-target="#myModal">
                    <fmt:message key="change.information"/>
                </button>
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><fmt:message key="change.surname"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><fmt:message key="surname"/> (<fmt:message key="max.70.symbols"/>)</span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="Surname" name="newSurname"
                                               pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)" required>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="command" value="change_surname">
                                        <input type="hidden" name="page" value="${ page }">
                                        <input type="submit" value="<fmt:message key="change"/>" class="btn btn-secondary">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="name"/></td>
            <td><c:out value="${ user.name }"/></td>
            <td>
                <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myModal2">
                    <fmt:message key="change.information"/>
                </button>
                <div class="modal fade" id="myModal2">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><fmt:message key="change.name"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><fmt:message key="name"/> (<fmt:message key="max.70.symbols"/>)</span>
                                        </div>
                                        <input type="text" class="form-control" name="newName" placeholder="<fmt:message key="name"/>"
                                               pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)" required>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="command" value="change_name">
                                        <input type="hidden" name="page" value="${ page }">
                                        <input type="submit" value="<fmt:message key="change"/>" class="btn btn-secondary">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="patronymic"/></td>
            <td><c:out value="${ user.patronymic }"/></td>
            <td>
                <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myModal3">
                    <fmt:message key="change.information"/>
                </button>
                <div class="modal fade" id="myModal3">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><fmt:message key="change.patronymic"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><fmt:message key="patronymic"/> (<fmt:message key="max.70.symbols"/>)</span>
                                        </div>
                                        <input type="text" class="form-control" name="newPatronymic" placeholder="<fmt:message key="patronymic"/>"
                                               pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)" required>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="command" value="change_patronymic">
                                        <input type="hidden" name="page" value="${ page }">
                                        <input type="submit" value="<fmt:message key="change"/>" class="btn btn-secondary">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="address"/></td>
            <td><c:out value="${ user.address }"/></td>
            <td>
                <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myModal4">
                    <fmt:message key="change.information"/>
                </button>
                <div class="modal fade" id="myModal4">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><fmt:message key="change.address"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><fmt:message key="address"/> (5-70 <fmt:message key="symbols"/>)</span>
                                        </div>
                                        <input type="text" class="form-control" name="newAddress" placeholder="Address"
                                               pattern="(^.{5,70}$)" required>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="command" value="change_address">
                                        <input type="hidden" name="page" value="${ page }">
                                        <input type="submit" value="<fmt:message key="change"/>" class="btn btn-secondary">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td><fmt:message key="phone"/></td>
            <td><c:out value="${ user.phone }"/></td>
            <td>
                <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myModal5">
                    <fmt:message key="change.information"/>
                </button>
                <div class="modal fade" id="myModal5">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><fmt:message key="change.phone"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><fmt:message key="phone.code"/></span>
                                        </div>
                                        <input type="text" class="form-control" name="newPhone" placeholder="8-044-1234567"
                                               pattern="(^[8]-(033|029|044|017)-[1-9][0-9]{2}-[0-9]{2}-[0-9]{2}$)" required>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="command" value="change_phone">
                                        <input type="hidden" name="page" value="${ page }">
                                        <input type="submit" value="<fmt:message key="change"/>" class="btn btn-secondary">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="container-fluid">
    <br>
    <h2><fmt:message key="order.history"/></h2>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="preparation.time"/></th>
            <th><fmt:message key="delivery.time"/></th>
            <th><fmt:message key="table.total"/></th>
        </tr>
        </thead>
        <c:set var="index" scope="session" value="0"/>
        <c:forEach var="element" items="${userOrderProducts}" varStatus="status">
            <c:choose>
                <c:when test="${index != element.order.id }">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <thead>
                    <tr>
                        <th><c:out value="${ element.order.id }"/></th>
                        <th>
                            <mark><c:out value="${ element.order.status.toString().replace(\"_\", \" \") }"/></mark>
                        </th>
                        <th>
                            <mark><c:out
                                    value="${ element.order.preparationTime.toString().replace(\"T\", \" \") }"/></mark>
                        </th>
                        <th>
                            <mark><c:out
                                    value="${ element.order.deliveryTime.toString().replace(\"T\", \" \") }"/></mark>
                        </th>
                        <th><c:out value="${ element.order.total }"/>0 BYN</th>
                    </tr>
                    </thead>
                    <tr>
                        <td><img class="image_dish_basket" src="<c:out value="${ element.dish.picture }"/>"></td>
                        <td><c:out value="${ element.dish.name }"/></td>
                        <td><c:out value="${ element.dish.price }"/>0 BYN</td>
                        <td>x<c:out value="${ element.amount }"/></td>
                        <td><c:out value="${ element.cost }"/>0 BYN</td>
                    </tr>
                    <c:set var="index" scope="session" value="${ element.order.id }"/>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td><img class="image_dish_basket" src="<c:out value="${ element.dish.picture }"/>"></td>
                        <td><c:out value="${ element.dish.name }"/></td>
                        <td><c:out value="${ element.dish.price }"/>0 BYN</td>
                        <td>x<c:out value="${ element.amount }"/></td>
                        <td><c:out value="${ element.cost }"/>0 BYN</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
    <ul class="pagination justify-content-center" style="margin:20px 0">
        <li class="page-item"><a class="pagination_color page-link"
                                 href="${request.contextPath}controller?command=personal_account&page=1"><fmt:message key="first"/></a>
        </li>
        <li class="page-item"><a class="pagination_color page-link"
                                 href="${request.contextPath}controller?command=personal_account_decrease_page&page=${page}&count=${count}"><<</a>
        </li>
        <li class="pagination_number">
            <span class="pagination_number"><mark>&nbspPage <c:out value="${ page }"/> from <c:out value="${ count }"/>&nbsp</mark></span>
        </li>
        <li class="page-item">
            <a class="pagination_color page-link"
               href="${request.contextPath}controller?command=personal_account_increase_page&page=${page}&count=${count}">>></a>
        </li>
        <li class="page-item"><a class="pagination_color page-link"
                                 href="${request.contextPath}controller?command=personal_account&page=${count}"><fmt:message key="last"/></a></li>
    </ul>
</div>
<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>
</body>
</html>
