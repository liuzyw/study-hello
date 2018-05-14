<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>天气预报</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<style type="text/css">

    body {
        background-color: black;
        font-size: 14px;
    }

    .box {
        border: 1px dotted aquamarine;
        height: 500px;
    }

    .box .weather {
        width: 16%;
        height: 40%;
        border: 1px dotted chartreuse;
        margin-left: 40px;
        margin-top: 20px;
        float: left;
        color: white;
    }

    .box .weather div {
        line-height: 24px;
    }

    .box .weather .pv {
        font-size: 24px;
        font-weight: 700;
    }
</style>
<body>
<div align="center">

    <p align="center">
    <h2 style="color: white">weather-web</h2>
    </p>
    <hr/>
    <br/>
    <br/>
    <div>
        <select id="province" onchange="getWeather(this)">
            <option>--- 请选择 ---</option>
            <option>长沙</option>
            <option>上海</option>
            <option>石家庄</option>
            <option>广州</option>
            <option>合肥</option>
            <option>北京</option>
        </select>
        <br/>
        <br/>

        <div class="box">
            <div id="result" class=" weather">
                <div id="date"></div>
                <div id="city" class="pv"></div>
                <div id="high"></div>
                <div id="low"></div>
                <div id="fengli"></div>
                <div id="fengxiang"></div>
                <div id="type"></div>
                <div><img src="image/sun.jpeg" style="width: 50px"></div>
            </div>
            <div class=" weather">
                <div id="date2"></div>
                <div id="city2" class="pv"></div>
                <div id="high2"></div>
                <div id="low2"></div>
                <div id="fengli2"></div>
                <div id="fengxiang2"></div>
                <div id="type2"></div>
                <div><img src="image/sun.jpeg" style="width: 50px"></div>

            </div>
            <div class=" weather">
                <div id="date3"></div>
                <div id="city3" class="pv"></div>
                <div id="high3"></div>
                <div id="low3"></div>
                <div id="fengli3"></div>
                <div id="fengxiang3"></div>
                <div id="type3"></div>
            </div>
            <div class=" weather">
                <div id="date4"></div>
                <div id="city4" class="pv"></div>
                <div id="high4"></div>
                <div id="low4"></div>
                <div id="fengli4"></div>
                <div id="fengxiang4"></div>
                <div id="type4"></div>
            </div>
            <div class=" weather">
                <div id="date5"></div>
                <div id="city5" class="pv"></div>
                <div id="high5"></div>
                <div id="low5"></div>
                <div id="fengli5"></div>
                <div id="fengxiang5"></div>
                <div id="type5"></div>
            </div>


        </div>
    </div>
    <br/>
    <br/>

    <br/>
    <br/>

    <br/>
    <br/>

    <br/>
    <br/>

    <script type="text/javascript">

      function getWeather(obj) {
        //首先获得下拉框的节点对象；
        var select = document.getElementById("province");

        //1.如何获得当前选中的值？：
        var value = select.value;

        //2.如何获得该下拉框所有的option的节点对象
        var options = select.options;
        //注意：得到的options是一个对象数组

        //3.如何获得第几个option的value值?比如我要获取第一option的value,可以这样：
        var value1 = options[0].value;
        //4.如何获得第几个option的文本内容?比如我要获取第一option的文本,可以这样：
        var text1 = options[0].text;

        //5.如何获得当前选中的option的索引？
        var index = select.selectedIndex;

        //6.如何获得当前选中的option的文本内容？
        //从第2个问题，我们已经获得所有的option的对象数组options了
        //又从第5个问题，我们获取到了当前选中的option的索引值
        //所以我们只要同options[index]下标的方法得到当前选中的option了

        var selectedText = options[index].text;

        $.ajax({
          type: "POST",
          url: "/getWeatherByCityName",
          data: {name: selectedText},
          dataType: 'json',
          contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
          success: function (msg) {
//            alert(msg.data.city + " - " + msg.data.ganmao);
            document.getElementById("date").innerHTML = msg.data.forecast[0].date;
            document.getElementById("city").innerHTML = msg.data.city;
            document.getElementById("high").innerHTML = msg.data.forecast[0].high;
            document.getElementById("low").innerHTML = msg.data.forecast[0].low;
            document.getElementById("fengli").innerHTML = msg.data.forecast[0].fengli;
            document.getElementById("fengxiang").innerHTML = msg.data.forecast[0].fengxiang;
            document.getElementById("type").innerHTML = msg.data.forecast[0].type;

            document.getElementById("date2").innerHTML = msg.data.forecast[1].date;
            document.getElementById("city2").innerHTML = msg.data.city;
            document.getElementById("high2").innerHTML = msg.data.forecast[1].high;
            document.getElementById("low2").innerHTML = msg.data.forecast[1].low;
            document.getElementById("fengli2").innerHTML = msg.data.forecast[1].fengli;
            document.getElementById("fengxiang2").innerHTML = msg.data.forecast[1].fengxiang;
            document.getElementById("type2").innerHTML = msg.data.forecast[1].type;

            document.getElementById("date3").innerHTML = msg.data.forecast[2].date;
            document.getElementById("city3").innerHTML = msg.data.city;
            document.getElementById("high3").innerHTML = msg.data.forecast[2].high;
            document.getElementById("low3").innerHTML = msg.data.forecast[2].low;
            document.getElementById("fengli3").innerHTML = msg.data.forecast[2].fengli;
            document.getElementById("fengxiang3").innerHTML = msg.data.forecast[2].fengxiang;
            document.getElementById("type3").innerHTML = msg.data.forecast[2].type;

            document.getElementById("date4").innerHTML = msg.data.forecast[3].date;
            document.getElementById("city4").innerHTML = msg.data.city;
            document.getElementById("high4").innerHTML = msg.data.forecast[3].high;
            document.getElementById("low4").innerHTML = msg.data.forecast[3].low;
            document.getElementById("fengli4").innerHTML = msg.data.forecast[3].fengli;
            document.getElementById("fengxiang4").innerHTML = msg.data.forecast[3].fengxiang;
            document.getElementById("type4").innerHTML = msg.data.forecast[3].type;

            document.getElementById("date5").innerHTML = msg.data.forecast[4].date;
            document.getElementById("city5").innerHTML = msg.data.city;
            document.getElementById("high5").innerHTML = msg.data.forecast[4].high;
            document.getElementById("low5").innerHTML = msg.data.forecast[4].low;
            document.getElementById("fengli5").innerHTML = msg.data.forecast[4].fengli;
            document.getElementById("fengxiang5").innerHTML = msg.data.forecast[4].fengxiang;
            document.getElementById("type5").innerHTML = msg.data.forecast[4].type;
          }
        });

      }

    </script>

</div>
</body>
</html>
