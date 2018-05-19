<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>springmvc index</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="${pageContext.request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body>
<div align="center">

    <p align="">
    <h1>spring-mvc-web</h1></p>
    <hr/>
    <br/>

    <div align="center" style="width: 800px">
        <table class="table table-striped table-bordered table-hover dataTables-example">
            <thead>
            <tr>
                <th>前往地址</th>
                <th>前往地址</th>

            </tr>
            </thead>
            <tbody>
            <tr class="gradeX">
                <td><<a href="/goFruit">跳转到水果页面</a>></td>
                <td><<a href="/goBook">跳转到书籍页面</a>></td>
            </tr>
            <tr class="gradeC">
                <td><<a href="/goUser">跳转到用户页面</a>></td>
                <td><<a href="/goAjaxReq">跳转到Ajax页面</a>></td>
            </tr>

            <tr class="gradeA">
                <td><<a href="/testLog">跳转到测试日志页面</a>></td>
                <td><<a href="/goUploadFile">跳转到文件上传页面</a>></td>
            </tr>
            <tr class="gradeA">
                <td><<a href="/goRedis">跳转到测试redis页面</a>></td>
                <td><<a href="/goLogin">跳转到登录页面</a>></td>
            </tr>

            <tr class="gradeA">
                <td><<a href="/goRoom">跳转到会议室页面</a>></td>
                <td><<a href="/goWeather">跳转到天气页面</a>></td>
            </tr>
            <tr class="gradeA">
                <td><<a href="/goShortLink">跳转到短链接页面</a>></td>
                <td><<a href="/goMap">跳转到地图页面</a>></td>
            </tr>


            </tbody>

        </table>
    </div>

</div>
</body>
</html>
