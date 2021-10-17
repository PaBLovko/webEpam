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
    <title><fmt:message key="about.us"/>t</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="menu.jsp"/>
<div class="container mt-3">
    <table class="table table-hover">
        <tr>
            <th>
                <h><strong><fmt:message key="about.message1"/></strong></h>
                <br>
                <p><fmt:message key="about.message2"/></p>
                <p><fmt:message key="about.message3"/></p>
                <ul>
                    <li><fmt:message key="about.message4"/></li>
                    <li><fmt:message key="about.message5"/></li>
                    <li><fmt:message key="about.message6"/></li>
                </ul>
                <br>
            </th>
        </tr>
        <tr>
            <th>
                <h><strong><fmt:message key="about.message7"/></strong></h>
                <br>
                <ul>
                    <li><fmt:message key="about.message8"/></li>
                    <li><fmt:message key="about.message9"/></li>
                    <li><fmt:message key="about.message10"/></li>
                    <li><fmt:message key="about.message11"/></li>
                    <li><fmt:message key="about.message12"/></li>
                    <li><fmt:message key="about.message13"/></li>
                </ul>
                <p><fmt:message key="about.message14"/></p>
            </th>
        </tr>
    </table>
</div>
<div class="container-fluid pt-3">
    <div class="footer">
        <jsp:include page="footer.jsp"/>
    </div>
</div>
</body>
</html>
