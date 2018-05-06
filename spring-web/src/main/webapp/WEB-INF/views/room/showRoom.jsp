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
    会议室:${id.index + 1}     编号:${room.id}     名称:${room.name}<br/>
    <c:forEach items="${room.list}" var="pair" varStatus="offset">
        可用时间段: ${pair.beginDate} - ${pair.endDate}<br/>
    </c:forEach>
    ------------------------------------------------------------
    <br/>
    <br/>
</c:forEach>
<br/>
<br/>
预定房间
<br/>
<div id="aa">
    <%--<form action="/bookRoom" method="post">--%>
    预订人:<input id="bookName" name="bookName" value="liu"/><br/>
    会议室编号:<input id="no" name="no" value="2"/><br/>
    开始时间:<input id="begin" name="beginDate" value="8"/><br/>
    结束时间:<input id="end" name="endDate" value="16"/><br/>
    开始预定:<input id="booking" type="submit" value="bookRoom"/>

    <%--</form>--%>

</div>
<br/>
<div id="bbb">
    签到:<br/>
    预订人:<input id="signName" name="bookName" value="liu"/><br/>
    会议室编号:<input id="signNo" name="no" value="2"/><br/>
    签到:<input id="signBook" type="submit" value="sign"/>
</div>
<script>
  $(function () {
    $("#booking").click(function () {
      var bookName = document.getElementById("bookName").value;
      var no = document.getElementById("no").value;
      var begin = document.getElementById("begin").value;
      var end = document.getElementById("end").value;

      $.ajax({
        type: "POST",
        url: "/bookRoom",
        data: JSON.stringify({bookName: bookName, no: no, beginDate: begin, endDate: end}),
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


<script>
  $(function () {
    $("#signBook").click(function () {
      var signName = document.getElementById("signName").value;
      var signNo = document.getElementById("signNo").value;

      $.ajax({
        type: "POST",
        url: "/signIn",
        data: JSON.stringify({bookName: signName, no: signNo}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          if (result) {
            alert("签到成功");
          } else {
            alert("签到失败")
          }
          window.location.assign(location.href);
        }
      });
    });
  });
</script>

</body>
</html>
