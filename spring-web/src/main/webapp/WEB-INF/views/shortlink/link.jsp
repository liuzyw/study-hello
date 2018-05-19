<%--
  User: liuzhaoyuan
  Date: 2018/5/18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShortLink</title>
</head>
<style type="text/css">

    body {
        background: yellowgreen;
        background-image: url("http://ww1.sinaimg.cn/large/0060lm7Tly1fkq3tfk269j318g0gpq77.jpg");
    }

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">

  function getShortLink() {
    var longurl = document.getElementById("longurl").value;
    $.ajax({
      type: "POST",
      url: "/compressUrl",
      data: {longurl: longurl},
      dataType: 'json',
      contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
      success: function (data) {
        document.getElementById("shorturl").style.display = "block";
        document.getElementById("shorturl").value = data.shortlink;

      }
    });
  }
</script>
<body>
<br/>
<p align="center" style="margin-top: 20px; text-align: center;font-size: 40px">
    短链接生成平台</p>
<br/>

<div style="border: 2px solid yellowgreen;width: 800px;height: 400px; margin: 20px auto;" align="center">

    <div id="content">

        <h4>请输入长网址</h4>
        <input type="text" style="width: 400px;height: 40px;border: 2px solid lightskyblue" id="longurl" name="longurl" value="">
        <br/>
        <br/>
        <input type="submit" id="submit" value="压缩" style="width: 50px;height: 40px;" onclick="getShortLink()">

        <br/>
        <br/>
        <input hidden="hidden" type="text" style="width: 400px;height: 40px;border: 2px solid lightskyblue;" id="shorturl" name="shorturl">


    </div>

</div>

<br/>
</body>
</html>
