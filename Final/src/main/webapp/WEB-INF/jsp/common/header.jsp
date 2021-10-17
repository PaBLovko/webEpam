<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        <%@include file="../../../css/bootstrap.min.css" %>
        <%@include file="../../../css/style.css" %>
    </style>
    <script>
        <%@include file="../../../js/bootstrap.js" %>
    </script>
    <title><fmt:message key="restaurant"/></title>
</head>
<body>
<div class="container-fluid col-sm-12">
    <div class="p-3 m-3 bg text-white-50">
        <table>
            <tr>
                <td class="logo"rowspan="2"><img class="image_logo" src="image/logo1.png"></td>
                <td class="name_organization text-center">CAFE.BY</td>
            </tr>
            <tr>
                <td class="address_organization text-center"><fmt:message key="address.company"/></td>
                <td class="mts_logo"><img class="image_logo_a1" src="image/mts.png"></td>
                <td class="mts_phone"><fmt:message key="contacts.message4"/></td>
                <td class="a1_logo"><img class="image_logo_a1" src="image/a1.png"></td>
                <td class="a1_phone"><fmt:message key="contacts.message5"/></td>
                <td>
                    <a class="interface" href="${request.contextPath}controller?command=show_main_page&locale=ru_RU">RU</a>
                    <a class="interface" href="${request.contextPath}controller?command=show_main_page&locale=en_EN">EN</a>
                    <a class="interface" href="${request.contextPath}controller?command=show_main_page&locale=be_BE">BE</a>
                </td>
        </tr>
        </table>
    </div>
</div>
</body>
</html>
