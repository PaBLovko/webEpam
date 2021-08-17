<%--
  Created by IntelliJ IDEA.
  User: 50922
  Date: 09.08.2021
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>XMLParsing</title>
</head>
<body align="center">
<h2>Парсинг XML</h2>
<form action="parsing" method="post" name="parserChooserForm" enctype="multipart/form-data">
    <p><input type="file" name="file"></p>
    <p>
        <select name="parserChooser">
            <option value="DOM">DOM</option>
            <option value="SAX">SAX</option>
            <option value="StAX">StAX</option>
        </select></p>
    <p><input type="submit" value="RUN"></p>
</form>
</body>
</html>
