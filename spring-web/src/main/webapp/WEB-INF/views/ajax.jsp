<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax</title>
</head>
<body>
test ajax

<br/>
<br/>

<%--<script type="text/javascript" src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script>
  $(function () {
    $("#click").click(function () {
      $.ajax({
        type: "POST",
        url: "/getAjaxMessage",
        data: JSON.stringify({name: "BWMaaa", color: "red"}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (msg) {
          alert(msg);
          var buss = eval(msg);
          var str = "";
          for (var i = 0; i < buss.length; i++) {
            str = str +"["+ buss[i].name + "," + buss[i].color+","+buss[i].date+"]";
          }
          document.getElementById("aaa").innerHTML = str;
        }
      });
    });
  });
</script>
<br/><input id="click" type="button" value="post bus"/>
/getAjaxMessage post bus by jq param
<div id="aaa"></div>
<br/>

<script>
  $(function () {
    $("#clickc").click(function () {
      $.ajax({
        type: "GET",
        url: "/getAjaxMessage1",
        data: {name: 'BWMaaa1', color: 'redd'},
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (msg) {
          alert(msg);
          var buss = eval(msg);
          var str = "";
          for (var i = 0; i < buss.length; i++) {
            str = str +"["+ buss[i].name + "," + buss[i].color+","+buss[i].date+"]";
          }
          document.getElementById("adaa").innerHTML = str;
        }
      });
    });
  });

</script>
<br/><input id="clickc" type="button" value="get bus"/>
/getAjaxMessage get bus by jq param
<div id="adaa"></div>
<br/>

<script>
  $(function () {
    $("#click1").click(function () {
      $.ajax({
        type: "GET",
        url: "/getBuss1",
        data: {},
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (msg) {
          alert(msg);
          var buss = eval(msg);
          var str = "";
          for (var i = 0; i < buss.length; i++) {
            str = str +"["+ buss[i].name + "," + buss[i].color+","+buss[i].date+"]";
          }
          document.getElementById("bbb").innerHTML = str;
        }
      });
    });
  });
</script>
<br/><input id="click1" type="button" value="get jquery"/>
/getbuss1 get bus by jq
<div id="bbb"></div>
<br/>


<script>
  var cfg = {
    type: 'POST',
    data: JSON.stringify({name: "BWMabba", color: "red"}),
    dataType: 'json',
    contentType: 'application/json;charset=UTF-8',
    success: function (result) {
      alert(result);
      var buss = eval(result);
      var str = "";
      for (var i = 0; i < buss.length; i++) {
        str = str +"["+ buss[i].name + "," + buss[i].color+","+buss[i].date+"]";
      }
      document.getElementById("ccc").innerHTML = str;
    }
  };

  function doTestJson(actionName) {
    cfg.url = actionName;
    $.ajax(cfg);
  }
</script>
<br/><input type="button" value="submit1" onclick="doTestJson('/getAjaxMessage')"/>
/getAjaxMessage post by Jq2 param
<div id="ccc"></div>
<br/>


<script>
  function getBuss() {
    var xhr = null;
    if (window.XMLHttpRequest) {
      xhr = new XMLHttpRequest();
    } else {
      xhr = new ActiveXObject("Microft.XMLHttp");
    }

    var url = '/getBuss';
    xhr.open("post", url, true);

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          var text = xhr.responseText;
          alert(text);
          document.getElementById("ddd").innerHTML = text;
          ///////////解析后台发送的Json数据///////////////
          //js中eval方法():校验参数文本串符合js中哪一种数据类型，并把其转换成对应的类型----这里将会转换成json
          var buss = eval(msg);
          var str = "";
          for (var i = 0; i < buss.length; i++) {
            str = str +"["+ buss[i].name + "," + buss[i].color+","+buss[i].date+"]";
          }
          alert(str);
        }
      }
    }

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
  }
</script>
<br/><input type="button" value="getBuss" onclick="getBuss()"/>
/getbuss post origin
<div id="ddd"></div>
<br/>

<script>
  function getBuss1() {
    var xhr = null;
    if (window.XMLHttpRequest) {
      xhr = new XMLHttpRequest();
    } else {
      xhr = new ActiveXObject("Microft.XMLHttp");
    }

    var url = '/getBuss1';
    xhr.open("get", url, true);

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          var text = xhr.responseText;
          alert(text);
          document.getElementById("eee").innerHTML = text;
          ///////////解析后台发送的Json数据///////////////
          //js中eval方法():校验参数文本串符合js中哪一种数据类型，并把其转换成对应的类型----这里将会转换成json
//          var buss = eval(text);//得到一个json对象
          var buss = eval(msg);
          var str = "";
          for (var i = 0; i < buss.length; i++) {
            str = str +"["+ buss[i].name + "," + buss[i].color+","+buss[i].date+"]";
          }
          alert(str);
        }
      }
    }

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
  }
</script>
<br/><input type="button" value="getBuss1" onclick="getBuss1()"/>
/getbuss get origin
<div id="eee"></div>
<br/>


</body>
</html>
