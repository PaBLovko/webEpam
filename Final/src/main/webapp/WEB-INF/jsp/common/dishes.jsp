<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title><fmt:message key="restaurant"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="menu.jsp"/>
<div class="container-fluid pt-3">
    <c:if test="${user.role == 3}">
        <div class="wrong_message"><c:out value="${ wrong }"/></div>
        <div class="right_message"><c:out value="${ right }"/></div>
    </c:if>
    <br>
    <button type="button" class="dropdown_sort btn btn-prim" data-toggle="dropdown">
        <fmt:message key="sort.by"/>
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="${request.contextPath}controller?command=sort_by_increase_price"><fmt:message key="price.increase"/></a>
        <a class="dropdown-item" href="${request.contextPath}controller?command=sort_by_decrease_price"><fmt:message key="price.decrease"/></a>
    </div>
    <ul class="ul_dish">
        <c:forEach var="element" items="${dishes}" varStatus="status">
            <li class="li_dish">
                <table class="table_dish">
                    <tr>
                        <td class="product_width" rowspan="6"><img class="image_dish rounded"
                                                                   src="<c:out value="${ element.picture }"/>">
                        </td>
                        <td class="product_name"><c:out value="${ element.name }"/></td>
                    <tr>
                        <td class="product_description"><c:out value="${ element.description }"/></td>
                    </tr>
                    <tr>
                        <td class="product_weight"><c:out value="${ element.weight }"/> <fmt:message key="grams"/></td>
                    </tr>
                    <tr>
                        <td class="product_price">
                            <mark><c:out value="${ element.price }"/>0 BYN</mark>
                        </td>
                    </tr>
                    <c:choose>
                        <c:when test="${user.role == 3}">
                            <form action="controller" method="POST">
                                <tr>
                                    <td class="product_amount">
                                        <input type="number" min="1" max="99" value="1" name="dishAmount" class="input_center">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="button_navbar_menu">
                                        <input type="hidden" name="dishId" value="${ element.id }"/>
                                        <input type="hidden" name="dishPrice" value="${ element.price }"/>
                                        <input type="hidden" name="command" value="add_dish">
                                        <input type="submit" name="button" class="in_basket" value="<fmt:message key="to.basket"/>"/>
                                    </td>
                                </tr>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="product_amount">
                                    <input type="number" min="1" max="99" value="1" name="${ element.id }amount" class="input_center">
                                </td>
                            </tr>
                            <tr>
                                <td class="button_navbar_menu">
                                    <input type="submit" name="button" class="in_basket" value="<fmt:message key="to.basket"/>"
                                           data-toggle="modal" data-target="#myModalBasket"/>
                                    <div class="modal fade" id="myModalBasket">
                                        <div class="modal-dialog modal-dialog-centered modal-sm">
                                            <div class="modal-content">
                                                <!-- Modal Header -->
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;
                                                    </button>
                                                </div>
                                                <!-- Modal body -->
                                                <div class="modal-body">
                                                    <fmt:message key="login.as.user"/>
                                                </div>
                                                <!-- Modal footer -->
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal" data-dismiss="modal"><fmt:message key="close"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </li>
        </c:forEach>
    </ul>
    <div class="footer">
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>