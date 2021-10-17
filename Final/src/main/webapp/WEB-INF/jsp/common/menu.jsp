<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<!doctype html>
<html>
<head>
    <title><fmt:message key="restaurant"/></title>
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
<div class="container-fluid pt-3">
    <div class="row">
        <ul class="ul_pages">
            <li class="li_pages">
                <div class="button_navbar_menu">
                    <a href = "${request.contextPath}controller?command=show_about_us" class="a_link">
                        <img src="image/group.png" class="ico"> <fmt:message key="about.us"/>
                    </a>
                </div>
            </li>
            <li class="li_pages">
                <div class="button_navbar_menu">
                    <a href="${request.contextPath}controller?command=show_main_page" class="a_link">
                        <img src="image/magazine.png" class="ico"> <fmt:message key="our.dishes"/>
                    </a>
                </div>
            </li>
            <li class="li_pages">
                <div class="button_navbar_menu">
                    <a href="${request.contextPath}controller?command=show_contacts" class="a_link">
                        <img src="image/hours.png" class="ico"> <fmt:message key="contacts"/>
                    </a>
                </div>
            </li>
            <li class="li_pages">
                <div class="button_navbar_menu">
                    <a href="${request.contextPath}controller?command=show_delivery" class="a_link">
                        <img src="image/runner.png" class="ico"> <fmt:message key="delivery"/>
                    </a>
                </div>
            </li>
            <li class="li_pages">
                <div class="button_navbar_menu">
                    <a href="${request.contextPath}controller?command=show_comment&page=1" class="a_link">
                        <img src="image/pen.png" class="ico"><fmt:message key="comment"/>
                    </a>
                </div>
            </li>
            <li class="li_pages">
                <c:choose>
                    <c:when test="${user.role == 3}">
                        <div class="button_navbar_menu">

                            <a href="${request.contextPath}controller?command=show_basket" class="a_link">
                                <button type="button" class="in_basket_menu h5">
                                    <img src="image/basket.png" class="ico"><fmt:message key="basket"/>
                                </button>
                            </a>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="button_navbar_menu">
                            <button type="button" class="in_basket_menu h5" data-toggle="modal" data-target="#myModalBask">
                                <img src="image/basket.png" class="ico"> <fmt:message key="basket"/>
                            </button>
                            <div class="modal fade" id="myModalBask">
                                <div class="modal-dialog modal-dialog-centered modal-sm">
                                    <div class="modal-content">

                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;
                                            </button>
                                        </div>

                                        <div class="modal-body">
                                            <fmt:message key="login.as.user"/>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal"><fmt:message key="close"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</div>
</ul>
</body>
</html>
