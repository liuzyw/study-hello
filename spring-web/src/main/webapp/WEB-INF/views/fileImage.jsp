<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>FileImage</title>
</head>
<body>
FileImage Details<br/>
Name: ${fileImage.name}<br/>
Type: ${fileImage.type}<br/>
images:<br/>
<c:forEach items="${fileImage.images}" var="image">
    <li>${image.originalFilename}</li>
    <img src="/Users/liuzhaoyuan/gitwork/study-hello/spring-web/upload/${image.originalFilename}"/>
</c:forEach>
</body>
</html>
