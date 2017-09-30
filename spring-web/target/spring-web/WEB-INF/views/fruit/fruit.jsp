<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fruit</title>
</head>
<body>
<br/>
<p>查找水果</p>
<form action="findFruitById" method="post">
    fruit id:
    <input type="text" value="10" name="id"/>
    <br/>
    <input type="submit" value="submit"/>
</form>
<br/>
<br/>
<<a href="/showFruit?fruitId=11">通过路径参数接受输入/showFruit?fruitId=11</a>>
<br/>
<br/>
<<a href="/showFruit/10">通过路径参数接受输入/showFruit/{fruitId}</a>>

</body>
</html>
