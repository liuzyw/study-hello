var stompClient = null;

// 浏览器加载 后，调用开启连接
$(function () {
  connect();
});

function connect() {

  var socket = new SockJS("/endpiontMark");

  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {

    console.log("connect: " + frame);

    stompTopic();

  });

}

// 发起订阅
function stompTopic() {

  stompClient.subscribe("/mass", function (response) {
    var message = JSON.parse(response.body);

    var response = $("#mass_div");

    var userName = $("#selectName").val();

    if (userName == message.fromName) {
      response.append("<div class='user-group'>"
          + " <div class='user-msg'>"
          + " <span class='user-reply'>" + message.chatValue + "</span>"
          + " <i class='triangle-user'></i>"
          + " </div>" + userName +
          +" </div>");
    } else {
      response.append("<div class='admin-group'>" + message.fromName
          + "<div class='admin-msg'>"
          + "<i class='triangle-admin'></i>"
          + "<span class='admin-reply'>" + message.chatValue + "</span>"
          + "</div>"
          + "</div>");
    }

  });

}

// 群发消息
function sendMassMessage() {
  var postValue = {};
  var chatValue = $("sendChatValue");
  var userName = $("#selectName").val();

  postValue.fromName = userName;

  postValue.chatValue = chatValue.val();

  if (postValue.fromName == null || postValue.fromName == 1) {
    alert("请选择角色");
    return;
  }

  if (postValue.chatValue == null || postValue.chatValue == "") {
    alert("请输入你想说的话");
    return;
  }

  // 向指定地址发送消息
  stompClient.send("/massRequest", {}, JSON.stringify(postValue));

  chatValue.val("");

}

// 一对一发起订阅
function stompQueue() {
  var userId = $("#selectName").val();
  alert("监听:" + userId);

  stompClient.subscribe('/queue/' + userId + '/alone', function (response) {
    var message = JSON.parse(response.body);
    var response = $("#alone_div");
    response.append("<div class='admin-group'>" + message.fromName +
        "<div class='admin-msg'>" +
        "<i class='triangle-admin'></i>" +
        "<span class='admin-reply'>" + message.chatValue + "</span>"
        + "</div></div>"
    );

  });
}

//  单独发送消息

function sendAloneMessage() {
  var postValue = {};
  var chatValue = $("#sendChatValue2");
  var userName = $("#selectName").val();
  var toUserId = $("#selectName2").val();
  var response = $("alone_div");
  stompClient.send("/aloneRequest", {}, JSON.stringify(postValue));

  response.append("<div class='user-group'>"
      + " <div class='user-msg'>"
      + " <span class='user-reply'>" + chatValue.val() + "</span>"
      + " <i class='triangle-user'></i>"
      + " </div>" + userName +
      +" </div>");

  chatValue.val("");

}

















