<%--
  User: liuzhaoyuan
  Date: 2018/5/5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Room</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<body>
find room
<br/>
<c:forEach items="${rooms}" var="room" varStatus="id">
    会议室:${id.index + 1} ${room}<br/>
</c:forEach>
<br/>
<br/>
预定房间
<br/>
<div id="aa">
    <%--<form action="/bookRoom" method="post">--%>
    <input id="no" name="no" value="2"/><br/>
    <input id="begin" name="beginDate"/><br/>
    <input id="end" name="endDate"/><br/>
    <input id="booking" type="submit" value="bookRoom"/>
    </form>

</div>
<script>
  $(function () {
    $("#booking").click(function () {
      var no = document.getElementById("no").value;
      var begin = document.getElementById("begin").value;
      var end = document.getElementById("end").value;

      $.ajax({
        type: "POST",
        url: "/bookRoom",
        data: JSON.stringify({no: no, beginDate: begin, endDate: end}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          if (result) {
            alert("预定成功");
          } else {
            alert("预定失败")
          }
          window.location.assign(location.href);
        }
      });
    });
  });
</script>
</body>
</html>
