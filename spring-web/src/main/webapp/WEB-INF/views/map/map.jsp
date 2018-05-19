<%--
  User: liuzhaoyuan
  Date: 2018/5/18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Map</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="${pageContext.request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">

    <!-- 注释该行表示不用高德的 css -->
    <!-- <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/> -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.4.6&key=4f17361cd823c6683fe1dc4bf43d2d5e&plugin=AMap.Autocomplete"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

</head>
<style type="text/css">

    .map {
        width: 1100px;
        height: 900px;
        background: gainsboro;
        border: 2px solid lightskyblue;
        z-index: 1;
    }

    .map h1 {
        line-height: 50px;
        background-color: lightskyblue;
    }

    .map .con {
        width: 1000px;
        height: 600px;
        border: 2px;
        background: #FC6;
        z-index: 2;
    }


</style>

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
<div>
    <p align="center" style="margin-top: 20px; text-align: center;font-size: 40px">
        地图查询平台</p>
    <br/>
</div>

<div align="center">
    <div id="content" class="map" align="center">
        <h1>去你想去的地方</h1>


        <div id="container" align="center" class="con">
            <div id="myPageTop" style="position:absolute;margin-left: 700px;z-index: 32">
                <table>
                    <tr>
                        <td>
                            <label>搜索：</label>
                        </td>
                        <td class="column2">
                            <label>经纬度：</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
                        </td>
                        <td class="column2">
                            <input type="text" readonly="true" id="lnglat">
                        </td>
                    </tr>


                </table>
            </div>


        </div>


    </div>
</div>
<br/>

<script>
  var map = new AMap.Map('container', {
    resizeEnable: true,
    zoom: 11,
    center: [116.397428, 39.90923]
  });
  map.plugin(["AMap.ToolBar"], function () {
    map.addControl(new AMap.ToolBar());
  });
  //输入提示
  var auto = new AMap.Autocomplete({
    input: "tipinput"
  });

  //为地图注册click事件获取鼠标点击出的经纬度坐标
  var clickEventListener = map.on('click', function (e) {
    document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
  });

  AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
  function select(e) {
    if (e.poi && e.poi.location) {
      map.setZoom(15);
      map.setCenter(e.poi.location);
    }
  }

  // 路线规划
  var route, marker;
  var path = [];
  path.push([116.303843, 39.983412]);
  path.push([116.321354, 39.896436]);
  path.push([116.407012, 39.992093]);
  map.plugin("AMap.DragRoute", function () {
    route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE); //构造拖拽导航类
    route.search(); //查询导航路径并开启拖拽导航
  });

</script>
</body>


</html>
