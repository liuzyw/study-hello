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


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- 背景 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/canvas_bg.js"></script>


</head>
<style type="text/css">
    {
        margin: 0
    ;
        padding: 0
    ;
    }
    body {
        width: 100%;
        height: 100%;
        overflow: hidden;
    }

</style>
<body>
<canvas id="c" style="position: absolute;z-index: -1;text-align: center;"></canvas>
<div align="center">
    <hr/>
    <p>
    <h1 style="font-size: 40px;font-weight: 700">spring-mvc-web</h1></p>
    <hr/>

    <div align="center" style="width: 800px">
        <p style="font-size: 30px;font-family:楷体;">陪伴是最长情的告白</p>
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
            <tr class="grade">
                <td><<a href="/goJavaJVM">跳转到JVM页面</a>></td>
                <td><<a href=""></a>></td>
            </tr>
            <tr class="grade">
                <td><<a href="/goStock">跳转到抢购页面</a>></td>
                <td><<a href=""></a>></td>
            </tr>


            </tbody>

        </table>
    </div>

</div>
</body>
</html>
