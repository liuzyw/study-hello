<%--
  Created by IntelliJ IDEA.
  User: liuzhaoyuan
  Date: 2019/2/17
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div align="center"><p>欢迎登录 权限</p></div>
<br/>
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
