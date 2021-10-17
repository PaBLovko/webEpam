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
    <br>
    <h2><fmt:message key="dishes"/></h2>
    <br>
    <div class="wrong_message"><c:out value="${ wrong }"/></div>
    <div class="right_message"><c:out value="${ right }"/></div>
    <br>
    <ul class="nav">
        <li class="li_admin nav-item">
            <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myTopModal3">
                <fmt:message key="add.new.dish"/>
            </button>
            <div class="modal fade" id="myTopModal3">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="add.new.dish"/></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form action="controller" method="POST">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="picture.path"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="savePicture" placeholder="<fmt:message key="path"/>" pattern="^.{0,255}$" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="name.symbols"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveName"  placeholder="<fmt:message key="name"/>" pattern="^[A-Z][a-z]{3,20}$" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="weight.grams"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveWeight" placeholder="100-3000" pattern="^((3000)|([1-9][0-9][0-9])|[1-2][0-9][0-9][0-9])$" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="price.rub"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="savePrice" placeholder="3.00 - 199.00" pattern="^(([3-9]\.00)|([1-9][0-9]\.00)|(1[0-9][0-9]\.00))$" required>                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="description.symbols"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveDescription" placeholder="<fmt:message key="description"/>" pattern=".{0,1900}$" required>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" name="command" value="save_dish">
                                    <input type="submit" value="<fmt:message key="add.new.dish"/>" class="btn btn-secondary">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </li>
        <li class="li_admin nav-item">
            <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myTopModal4">
                <fmt:message key="find.dish.by.id"/>
            </button>
            <div class="modal fade" id="myTopModal4">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="find.dish.by.id"/></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form action="controller" method="POST">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="enter.id.9.symbols"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="dishId" placeholder="123456789" pattern="^([1-9][0-9]{0,8})$" required>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="command" value="find_dish_by_id">
                                <input type="submit" value="<fmt:message key="find.dish.by.id"/>" class="btn btn-secondary">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                        </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </li>
        <li class="li_admin nav-item">
            <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myTopModal5">
                <fmt:message key="find.dish.by.name"/>
            </button>
            <div class="modal fade" id="myTopModal5">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="find.dish.by.name"/></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form action="controller" method="POST">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="name.symbols"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="dishName" placeholder="<fmt:message key="name"/>" pattern="^[A-Z][a-z]{4,20}$" required>
                                </div>
                               <div class="modal-footer">
                                <input type="hidden" name="command" value="find_dish_by_name">
                                   <input type="submit" value="<fmt:message key="find.dish.by.name"/>" class="btn btn-secondary">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                               </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </li>
        <li class="li_admin nav-item">
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="admin_dishes">
                <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="show.all.dishes"/>">
            </form>
        </li>
    </ul>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="picture"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="weight"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="description"/></th>
            <th><fmt:message key="action"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="element" items="${dishes}" varStatus="status">
            <tr>
                <td><c:out value="${ element.id }"/></td>
                <td><img class="image_dish_basket" src="<c:out value="${ element.picture }"/>"></td>
                <td><c:out value="${ element.name }"/></td>
                <td><c:out value="${ element.weight }"/></td>
                <td><c:out value="${ element.price }"/>0</td>
                <td><c:out value="${ element.description }"/></td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                        data-target="#myModal${ element.id }">
                    <fmt:message key="delete"/>
                </button>
                <div class="modal fade" id="myModal${ element.id }">
                    <div class="modal-dialog modal-dialog-centered modal-sm">
                        <div class="modal-content">
                            <div class="modal-body">
                                <fmt:message key="delete.dish.from.database"/>
                            </div>
                            <div class="modal-footer">
                                <form action="controller" method="POST">
                                    <input type="hidden" name="delId" value="${ element.id }"/>
                                    <input type="hidden" name="command" value="delete_dish">
                                    <input type="submit" class="btn btn-secondary" value="<fmt:message key="delete"/>">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                            data-target="#mySecModal${ element.id }">
                        <fmt:message key="change"/>
                    </button>
                    <div class="modal fade" id="mySecModal${ element.id }">
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
                                                <span class="input-group-text"><fmt:message key="picture.symbols"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changePicture" placeholder="<fmt:message key="path"/>" pattern="^.{0,255}$">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="name.symbols"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changeName" placeholder="<fmt:message key="name"/>" pattern="^[A-Z][a-z]{4,20}$">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="weight.grams"/></span
                                            </div>
                                            <input type="text" class="form-control" name="changeWeight" placeholder="100-3000" pattern="^((3000)|([1-9][0-9][0-9])|[1-2][0-9][0-9][0-9])$">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="price.rub"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changePrice" placeholder="3.00 - 199.00" pattern="^(([3-9]\.00)|([1-9][0-9]\.00)|(1[0-9][0-9]\.00))$">                                    </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="description.symbols"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changeDescription" placeholder="<fmt:message key="description"/>" pattern=".{0,1900}$">
                                        </div>
                                        <div class="modal-footer">
                                            <input type="hidden" name="changeId" value="${ element.id }"/>
                                            <input type="hidden" name="command" value="change_dish">
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
                </td>
            </tr>
        </c:forEach>
<%--        </tbody>--%>
    </table>
</div>
<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>
</body>
</html>
