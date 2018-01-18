/**
 * Created by liuzhaoyuan on 2018/1/18.
 */
$(function () {
  $("#aaa").click(function () {
    $.ajax({
      type: "POST",
      url: "/hello",
//        data: {name: 'BWMaaa1', color: 'redd'},
//        dataType: 'json',
//        contentType: 'application/json;charset=UTF-8',
      success: function (msg) {
        // alert(msg);
        document.getElementById("bbb").innerHTML = msg;
      }
    });
  });
});