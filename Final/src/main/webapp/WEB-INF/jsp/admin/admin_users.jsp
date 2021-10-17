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
    <title><fmt:message key="login.admin"/></title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="admin_menu.jsp"/>
<div class="container-fluid">
    <br>
    <h2><fmt:message key="users"/></h2>
    <br>
    <div class="wrong_message"><c:out value="${ wrong }"/></div>
    <div class="right_message"><c:out value="${ right }"/></div>
    <br>
    <ul class="nav">
        <li class="li_admin nav-item">
            <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myTopModal3">
                <fmt:message key="add.new.user"/>
            </button>
            <div class="modal fade" id="myTopModal3">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="add.new.user"/></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form action="controller" method="POST">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="login.english"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveLogin" placeholder="5-12 <fmt:message key="symbols"/>"
                                           pattern="(^[a-zA-Z0-9_-]{5,12}$)" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="password.english"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="savePassword" placeholder="5-12 <fmt:message key="symbols"/>"
                                           pattern="(^[a-zA-Z0-9_-]{5,12}$)" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="role.numbers"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveRole" placeholder="1,2,3"
                                           pattern="^(1|2|3)$" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="surname"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveSurname" placeholder="<fmt:message key="max.70.symbols"/>"
                                           pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)"
                                           required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="name"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveName" placeholder="<fmt:message key="max.70.symbols"/>"
                                           pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)"
                                           required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="patronymic"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="savePatronymic" placeholder="<fmt:message key="max.70.symbols"/>"
                                           pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)"
                                           required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="address"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveAddress" placeholder="5-70 <fmt:message key="symbols"/>"
                                           pattern="(^.{5,70}$)" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="phone"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="savePhone" placeholder="8-044-123-45-67"
                                           pattern="(^[8]-(033|029|044|017)-[1-9][0-9]{2}-[0-9]{2}-[0-9]{2}$)" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><fmt:message key="note"/></span>
                                    </div>
                                    <input type="text" class="form-control" name="saveNote" placeholder="<fmt:message key="max.250.symbols"/>"
                                           pattern="(^.{0,250}$)" required>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" name="page" value="${ page }">
                                    <input type="hidden" name="command" value="save_user">
                                    <input type="submit" value="<fmt:message key="add.new.user"/>" class="btn btn-secondary">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </li>
    </ul>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="patronymic"/></th>
            <th><fmt:message key="address"/></th>
            <th><fmt:message key="phone"/></th>
            <th><fmt:message key="note"/></th>
            <th><fmt:message key="action"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="element" items="${users}" varStatus="status">
            <tr>
                <td><c:out value="${ element.id }"/></td>
                <td><c:out value="${ element.role }"/></td>
                <td><c:out value="${ element.surname }"/></td>
                <td><c:out value="${ element.name }"/></td>
                <td><c:out value="${ element.patronymic }"/></td>
                <td><c:out value="${ element.address }"/></td>
                <td><c:out value="${ element.phone }"/></td>
                <td><c:out value="${ element.note }"/></td>
                <td>
                    <button type="button" class="change-info btn btn-primary" data-toggle="modal"
                            data-target="#myModal${ element.id }">
                        <fmt:message key="delete"/>
                    </button>
                    <div class="modal fade" id="myModal${ element.id }">
                        <div class="modal-dialog modal-dialog-centered modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <fmt:message key="delete.user"/>
                                </div>
                                <div class="modal-footer">
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="delId" value="${ element.id }"/>
                                        <input type="hidden" name="command" value="delete_user">
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
                                                <span class="input-group-text"><fmt:message key="role.numbers"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changeRole" placeholder="1,2,3"
                                                   pattern="^(1|2|3)$">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="address"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changeAddress" placeholder="5-70 <fmt:message key="symbols"/>"
                                                   pattern="(^.{5,70}$)">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="phone"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changePhone" placeholder="8-044-123-45-67"
                                                   pattern="(^[8]-(033|029|044|017)-[1-9][0-9]{2}-[0-9]{2}-[0-9]{2}$)">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><fmt:message key="note"/></span>
                                            </div>
                                            <input type="text" class="form-control" name="changeNote" placeholder="<fmt:message key="max.250.symbols"/>"
                                                   pattern="(^.{0,250}$)">
                                        </div>
                                        <div class="modal-footer">
                                            <input type="hidden" name="changeId" value="${ element.id }"/>
                                            <input type="hidden" name="command" value="change_user">
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
    </table>
    <ul class="pagination justify-content-center" style="margin:20px 0">
        <li class="page-item"><a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_users&page=1"><fmt:message key="first"/></a></li>
        <li class="page-item"><a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_users_decrease_page&page=${page}&count=${count}"><<</a></li>
        <li class="pagination_number">
            <span class="pagination_number"><mark>&nbspPage <c:out value="${ page }"/> from <c:out value="${ count }"/>&nbsp</mark></span>
        </li>
        <li class="page-item">
            <a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_users_increase_page&page=${page}&count=${count}">>></a></li>
        <li class="page-item"><a class="pagination_color page-link" href="${request.contextPath}controller?command=admin_users&page=${count}"><fmt:message key="last"/></a></li>
    </ul>
</div>
<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="../common/footer.jsp"/>
    </div>
</div>
</body>
</html>