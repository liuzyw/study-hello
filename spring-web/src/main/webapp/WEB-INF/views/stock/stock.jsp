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
<p>抢购</p>
<div>
    productId:
    <input type="text" value="108" name="productId" id="productId"/>
    <br/>
    count:
    <input type="text" value="1" name="count" id="count"/>
    <br/>
    <input type="button" value="buy" id="buy"/><br/>
    <input type="button" value="add" id="add"/>

</div>
<br/>
<br/>
<div><p id="aaa"></p></div>
</body>
<script>
  $(function () {
    $("#buy").click(function () {
      var productId = document.getElementById("productId").value;
      var count = document.getElementById("count").value;

      $.ajax({
        type: "POST",
        url: "/buy",
        data: JSON.stringify({productId: productId, count: count}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          alert("预定成功");
          document.getElementById("aaa").innerHTML = result;
        }
      });
    });
  });

</script>

<script>
  $(function () {
    $("#add").click(function () {
      var productId = document.getElementById("productId").value;
      var count = document.getElementById("count").value;

      $.ajax({
        type: "POST",
        url: "/addStock",
        data: JSON.stringify({productId: productId, count: count}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          alert("添加成功");
          document.getElementById("aaa").innerHTML = result;
        }
      });
    });
  });
</script>

</html>
