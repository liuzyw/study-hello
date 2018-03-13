<%--
  Created by IntelliJ IDEA.
  User: liuzhaoyuan
  Date: 2017/8/17
  Time: 00:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Login</title>
</head>

<body>
<br/>
<p align="center">--  Login  --</p>
<br/>

<%--<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<br/>
<form action="/login" method="post">
    <table width="207" border="0" align="center">
        <tr>
            <td colspan="2" align="center" nowrap="nowrap">用户注册</td>
        </tr>
        <tr>
            <td width="68" nowrap="nowrap">用户名</td>
            <td width="127" nowrap="nowrap"><label>
                <input name="username" type="text" id="username" size="20"/>
            </label></td>
        </tr>
        <tr>
            <td nowrap="nowrap">密码</td>
            <td nowrap="nowrap"><input name="password" type="password" id="password" size="20" maxlength="10"/></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input id="index_code" name="code" type="text"/></td>

        </tr>
        <tr>
            <td></td>
            <td><img id="imgObj" alt="verificationCode" src="/code" onclick="changeImg()"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center" nowrap="nowrap"><label>
                <input type="submit" value="登录"/>
                <input type="reset" value="重填"/>
            </label></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
  function changeImg() {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", "/code?timestamp=" + (new Date()).valueOf());
  }
</script>

</body>
</html>