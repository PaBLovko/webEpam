<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
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
<div class="container-fluid">
    <ul class="nav">
        <li class="nav-item">
            <a class="link_acc nav-link" href="${request.contextPath}controller?command=show_main_page"><fmt:message key="return"/></a>
        </li>
        <li class="nav-item">
            <a class="link_acc nav-link" href="${request.contextPath}controller?command=log_out"><fmt:message key="login.exit"/></a>
        </li>
    </ul>
    <div class="btn-group">
        <form action="controller" method="POST">
            <input type="hidden" name="command" value="admin_dishes">
            <input type="hidden" name="dishPage" value="1">
            <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="dish"/>">
        </form>
        <form action="controller" method="POST">
            <input type="hidden" name="command" value="admin_users">
            <input type="hidden" name="page" value="1">
            <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="user"/>">
        </form>
        <form action="controller" method="POST">
            <input type="hidden" name="command" value="admin_comment">
            <input type="hidden" name="page" value="1">
            <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="admin.comment"/>">
        </form>
        <form action="controller" method="POST">
            <input type="hidden" name="command" value="admin_order_product">
            <input type="hidden" name="page" value="1">
            <input type="submit" class="change-info btn btn-primary" value="<fmt:message key="order.product"/>">
        </form>
        <button type="button" class="change-info btn btn-primary" data-toggle="modal" data-target="#myTopModalNewOrder">
            <fmt:message key="add.new.order"/>
        </button>
        <div class="modal fade" id="myTopModalNewOrder">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="enter.user.id"/></h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <form action="controller" method="POST">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><fmt:message key="enter.id.9.symbols"/></span>
                                </div>
                                <input type="text" class="form-control" name="userForOrderId" placeholder="123456789" pattern="^([1-9][0-9]{0,8})$" required>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="command" value="admin_add_new_user_for_order">
                                <input type="submit" value="<fmt:message key="add.order"/>" class="btn btn-secondary">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="cancel"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
