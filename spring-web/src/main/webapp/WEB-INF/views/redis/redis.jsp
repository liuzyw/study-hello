<%--
  Created by IntelliJ IDEA.
  User: liuzhaoyuan
  Date: 2017/10/2
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redis</title>
</head>
<body>
保存一个key
<form action="/addToRedis" method="post">
    name:<input type="text" name="key"/><br/><br/>
    value:<input type="text" name="value"/><br/><br/>
    <input type="submit" value="save"/>
</form>
<br/>
<br/>
<<a href="/showRedisValue?key=hello">根据key查找value</a>>
</body>
</html>
