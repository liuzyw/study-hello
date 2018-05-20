<%--
  User: liuzhaoyuan
  Date: 2018/5/18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JVM</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="${pageContext.request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">

    <!-- 注释该行表示不用高德的 css -->
    <!-- <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/> -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>


</head>
<style type="text/css">


</style>


<body>
<div>
    <p align="center" style="margin-top: 20px; text-align: center;font-size: 40px">
        Java监控平台</p>
    <br/>
</div>

<div align="center">
    <div id="content" class="jvm" align="center">
        <p id="osVersion">操作系统版本: </p>
        <p id="osName">jvm虚拟机的版本信息: </p>
        <p id="vendor">Java的运行环境供应商: </p>
        <p id="cpu">操作系统核心数: </p>
        <p id="username">用户名: </p>
        <p id="totalMemory">总内存: </p>
        <p id="freeMemory">可用内存: </p>
        <p id="maxMemory">最大内存: </p>
    </div>
</div>
<br/>
<script type="text/javascript">
  getJVMInfo();
  function getJVMInfo() {
    $.ajax({
      type: "GET",
      url: "/getJVMInfo",
      data: {},
      dataType: 'json',
      contentType: 'application/json;charset=UTF-8',
      success: function (data) {
        document.getElementById("totalMemory").innerHTML = document.getElementById("totalMemory").innerHTML + data.totalMemory;
        document.getElementById("osVersion").innerHTML = document.getElementById("osVersion").innerHTML + data.osVersion;
        document.getElementById("vendor").innerHTML = document.getElementById("vendor").innerHTML + data.vendor;
        document.getElementById("cpu").innerHTML = document.getElementById("cpu").innerHTML + data.cpu;
        document.getElementById("osName").innerHTML = document.getElementById("osName").innerHTML + data.osName;
        document.getElementById("freeMemory").innerHTML = document.getElementById("freeMemory").innerHTML + data.freeMemory;
        document.getElementById("maxMemory").innerHTML = document.getElementById("maxMemory").innerHTML + data.maxMemory;
        document.getElementById("username").innerHTML = document.getElementById("username").innerHTML + data.username;

      }
    });
  }


</script>

</body>

</html>
