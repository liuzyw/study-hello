<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

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
<form action="/getMvBook" method="post">

    id<input type="text" name="id" value="5"/>
    <br/>

    <input type="submit" value="getMvBook"/>
</form>
<br/>
<br/>
<<a href="/showBook/2">通过路径参数接受输入/showBook/{bookId}</a>>

<br/>


<div>
    <input type="button" value="修改" onclick="modify();"/><br>
    <input type="button" value="删除" onclick="del();"/><br>
</div>

</body>

<script type="text/javascript">


  function modify() {
    $.ajax({
      url: '/book/puttRestful',
      type: 'put',
      data: JSON.stringify({id: 1, name: 'dasd', type: 'java', price: 23}),
      contentType: 'application/json;charset=UTF-8',
      dataType: "json",
      success: function (data) {
        alert(data.name + "_" + data.type);
      }
    });
  }

  function del() {
    $.ajax({
      url: '/book?id=3',
      type: 'delete',
      success: function (data) {
        alert(data);
      }
    });
  }


</script>
</html>
