<%--
  User: liuzhaoyuan
  Date: 2018/5/5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stock</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<body>
<br/>
秒杀测试
<br/>
<div id="aa">
    productId:<input id="productId" name="productId" value="11"/><br/>
    count:<input id="count" name="count" value="2"/><br/>
    添加:<input id="add" type="submit" value="addStock"/>&nbsp;&nbsp;&nbsp;&nbsp;
    抢购:<input id="buy" type="submit" value="buyStock"/>
</div>
<br/>
<br/>

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
          if (result.data) {
            alert("添加成功");
          } else {
            alert("添加失败");
          }
        },
        error: function () {
          alert("添加失败");
        }
      });
    });
  });
</script>


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
          if (result.code == 200) {
            alert("抢购成功: " + result.data);
          } else {
            alert("抢购失败: " + result.data);
          }

        },
        error: function () {
          alert("抢购失败");
        }
      });
    });
  });
</script>

</body>
</html>
