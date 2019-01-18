<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>聊天室</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sockjs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/stomp.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/webchat.js"></script>

<style type="text/css">

    .friend_lists {
        margin: 0 50px 0px 10px;
        position: relative;
    }

    .friend_lists .header_img {
        position: absolute;
        top: 5px;
    }

    .me_lists {
        position: relative;
        margin: 0 10px 0px 50px;
    }

    .msg-wrapper.left {
        text-align: right;
    }

    .msg-wrapper.right {
        text-align: left;
    }

    .msg-left-white {
        background-color: #FFFFFF;
        font-size: 16px;
    }

    .msg-right-green {
        background-color: #a0e759;
        font-size: 16px;
    }


</style>
<body>


<h2 align="center">聊天室</h2>

<div align="center" style="background-color: white;">

    欢迎你: <select id="me">
    <option>--- 请选择身份 ---</option>
    <option>鲁班</option>
    <option>后裔</option>
    <option>韩信</option>
    <option>小乔</option>
    <option>木兰</option>
    <option>群聊</option>
</select>

    &nbsp;&nbsp;&nbsp;&nbsp; To:

    <select id="friend">
        <option>--- 请选择 ---</option>
        <option>鲁班</option>
        <option>后裔</option>
        <option>韩信</option>
        <option>小乔</option>
        <option>木兰</option>
        <option>群聊</option>
    </select>

    <p/>

    <div align="center" style="background-color:lightskyblue;width: 600px;height: 400px;border: 1px;margin-bottom: 1px">
        <br/>
        <div id='msg'>

            <div class="friend_lists">
                <%--<div class="header_img">--%>
                    <%--<img src="image/psd.jpg" style="width: 40px;height: 40px"/>--%>
                <%--</div>--%>
                <div class="msg-wrapper right">
                    <p class="msg-left-white">晚上过来吃饭？</p>
                </div>
            </div>

            <div class="me_lists">

                <div class="msg-wrapper left">
                    <p class="msg-right-green">好的，没问题！时间地点？</p>
                </div>

                <!--<div class="header_img">-->
                <!--<img src="image/psd.jpeg" style="width: 40px;height: 40px"/>-->
                <!--</div>-->
            </div>

        </div>

    </div>
    <div style="background-color:whitesmoke;width: 600px;border: 1px">
        <textarea id='sendMsg' align="left" style="width: 600px;height: 100px">要发送</textarea>
    </div>
    <div align="right" style="width: 600px;border: 1px;margin: auto">

        <input id="send" type="button" value="发送" style="width: 60px;height: 30px"/>

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

</div>

<script type="text/javascript">

  $(function () {
    $("#send").click(function () {

      var select = document.getElementById("me");
      var options = select.options;
      var index = select.selectedIndex;
      var me = options[index].text;

      var select2 = document.getElementById("friend");
      var friend = select2.value;

      var msg = document.getElementById("sendMsg").value;

      $.ajax({
        type: "POST",
        url: "/sendMsg",
        data: JSON.stringify({id: me, msg: msg, friend: friend}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
          document.getElementById("sendMsg").value = "";
        }
      });
    });
  });

</script>


</body>
</html>
