<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<br/>
<p>查找书籍</p>
<form action="findBookById" method="post">
    book id:
    <input type="text" value="1" name="id"/>
    <br/>
    <input type="submit" value="submit"/>
</form>
<br/>
<br/>
<p>添加书籍</p>
<form action="saveBook" method="post">

    name<input type="text" name="name"/>
    <br/>
    type<input type="text" name="type"/>
    <br/>
    price<input type="text" name="price">
    <br/>
    <input type="submit" value="save"/>
</form>
<br/>
<br/>
<<a href="/showBook/2">通过路径参数接受输入/showBook/{fruitId}</a>>

</body>
</html>
