<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<!doctype html>
<html>
<head>
    <title><fmt:message key="login.admin"/></title>
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
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="admin_menu.jsp"/>
<div class="container-fluid">
    <br>
    <h2><fmt:message key="orders"/></h2>
    <br>
    <div class="wrong_message"><c:out value="${ wrong }"/></div>
    <div class="right_message"><c:out value="${ right }"/></div>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="status"/></th>
            <th><fmt:message key="user.id"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="patronymic"/></th>
            <th><fmt:message key="preparation.time"/></th>
            <th><fmt:message key="delivery.time"/></th>
            <th><fmt:message key="table.total"/></th>
            <th><fmt:message key="action"/></th>
            <th></th>
        </tr>
        </thead>
        <c:set var="index" scope="session" value="0"/>
        <c:forEach var="element" items="${orderProducts}" varStatus="status">
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
                <th><mark><c:out value="${ element.order.status.toString().replace(\"_\", \" \") }"/></mark></th>
                <th><c:out value="${ element.order.user.id }"/></th>
                <th><c:out value="${ element.order.user.surname }"/></th>
                <th><c:out value="${ element.order.user.name }"/></th>
                <th><c:out value="${ element.order.user.patronymic }"/></th>
                <th><mark><c:out value="${ element.order.preparationTime.toString().replace(\"T\", \" \") }"/></mark></th>
                <th><mark><c:out value="${ element.order.deliveryTime.toString().replace(\"T\", \" \") }"/></mark></th>
                <th><c:out value="${ element.order.total }"/>0 BYN</th>
                <th>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                            data-target="#myModalDelOrd${ element.order.id }">
                        <fmt:message key="delete"/>
                    </button>
                    <div class="modal fade" id="myModalDelOrd${ element.order.id }">
                        <div class="modal-dialog modal-dialog-centered modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <fmt:message key="delete.order"/>
                                </div>
                                <div class="modal-footer">
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="delId" value="${ element.order.id }"/>
                                        <input type="hidden" name="command" value="delete_order">
                                        <input type="submit" class="btn btn-secondary" value="<fmt:message key="delete"/>">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </th>
                <th>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                            data-target="#myModalChange${ element.order.id }">
                        <fmt:message key="change"/>
                    </button>
                    <div class="modal fade" id="myModalChange${ element.order.id }">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title"><fmt:message key="change.information"/></h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <form action="controller" method="POST">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="preparation.time"/></span>
                                            </div>
                                            <input type="datetime-local" class="form-control" name="preparationTime">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="delivery.time"/></span>
                                            </div>
                                            <input type="datetime-local" class="form-control" name="deliveryTime">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="status"/></span>
                                            </div>
                                            <select name="changeStatus">
                                                <option value="not ready"><fmt:message key="not.ready"/></option>
                                                <option value="ready"><fmt:message key="ready"/></option>
                                                <option value="delivered"><fmt:message key="delivered"/></option>
                                                <option value="not delivered"><fmt:message key="not.delivered"/></option>
                                            </select>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="hidden" name="changeId" value="${ element.order.id }"/>
                                            <input type="hidden" name="command" value="change_order">
                                            <input type="submit" class="btn btn-secondary" value="<fmt:message key="change"/>">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                <fmt:message key="cancel"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </th>
            </tr>
        </thead>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><img class="image_dish_basket" src="<c:out value="${ element.dish.picture }"/>"></td>
                <td><c:out value="${ element.dish.name }"/></td>
                <td><c:out value="${ element.dish.price }"/>0 BYN</td>
                <td>x<c:out value="${ element.amount }"/></td>
                <td><c:out value="${ element.cost }"/>0 BYN</td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                            data-target="#myModalProdDel${ element.id }">
                        <fmt:message key="delete"/>
                    </button>
                    <div class="modal fade" id="myModalProdDel${ element.id }">
                        <div class="modal-dialog modal-dialog-centered modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <fmt:message key="delete.order.product"/>
                                </div>
                                <div class="modal-footer">
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="delId" value="${ element.id }"/>
                                        <input type="hidden" name="command" value="delete_order_product">
                                        <input type="submit" class="btn btn-secondary" value="<fmt:message key="delete"/>">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            <fmt:message key="cancel"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <c:set var="index" scope="session" value="${ element.order.id }"/>
        </c:when>
            <c:otherwise>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><img class="image_dish_basket" src="<c:out value="${ element.dish.picture }"/>"></td>
                <td><c:out value="${ element.dish.name }"/></td>
                <td><c:out value="${ element.dish.price }"/>0 BYN</td>
                <td>x<c:out value="${ element.amount }"/></td>
                <td><c:out value="${ element.cost }"/>0 BYN</td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                            data-target="#myModalProdDel${ element.id }">
                        <fmt:message key="delete"/>
                    </button>
                    <div class="modal fade" id="myModalProdDel${ element.id }">
                        <div class="modal-dialog modal-dialog-centered modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <fmt:message key="delete.order.product"/>
                                </div>
                                <div class="modal-footer">
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="delId" value="${ element.id }"/>
                                        <input type="hidden" name="command" value="delete_order_product">
                                        <input type="submit" class="btn btn-secondary" value="<fmt:message key="delete"/>">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </c:otherwise>
        </c:choose>
        </c:forEach>
    </table>
    <ul class="pagination justify-content-center" style="margin:20px 0">
        <li class="page-item"><a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_order_product&page=1"><fmt:message key="first"/></a></li>
        <li class="page-item"><a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_order_product_decrease_page&page=${page}&count=${count}"><<</a></li>
        <li class="pagination_number">
            <span class="pagination_number"><mark>&nbspPage <c:out value="${ page }"/> from <c:out value="${ count }"/>&nbsp</mark></span>
        </li>
        <li class="page-item">
            <a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_order_product_increase_page&page=${page}&count=${count}">>></a></li>
        <li class="page-item"><a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_order_product&page=${count}"><fmt:message key="last"/></a></li>
    </ul>
</div>

<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>
</body>
</html>
