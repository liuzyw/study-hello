<%--
  Created by IntelliJ IDEA.
  User: liuzhaoyuan
  Date: 2018/1/8
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
   login
<div align="center">
    <form action="/login" method="post">
        <table>
            <tr>Name: <input type="text" name="username" value="liu"/></tr>
            <br/>
            <tr>Pass: <input type="text" name="password" value="123"/></tr>
            <br/>
            <br/>
            <input type="submit" value="submit"/>
        </table>
    </form>
</div>
</body>
</html>
