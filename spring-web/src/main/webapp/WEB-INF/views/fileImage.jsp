<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
    <li>${image.originalFilename}</li><br/>
    <img src="${pageContext.request.contextPath}/upload/${image.originalFilename}"/>
    <br/>
</c:forEach>
<br/>
直接写死的<br/>
<img src="${pageContext.request.contextPath}/upload/psb.jpg"/><br/>
<br/>
这没显示<br/><img src="/Users/liuzhaoyuan/Pictures/psb.jpg" /><br/>
<br/>百度的<br/><img src="http://img2.imgtn.bdimg.com/it/u=1742626185,2547278809&fm=214&gp=0.jpg"/>

</body>
</html>
