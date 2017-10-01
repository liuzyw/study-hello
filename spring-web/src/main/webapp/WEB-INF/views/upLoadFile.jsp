<%--
  User: liuzhaoyuan
  Date: 2017/8/17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upLoadFile</title>
</head>
<body>
<br/>
测试文件上传
<br/>
单个文件上传
<br/>
<form action="/fileUpload" method="post" enctype="multipart/form-data">
    <h2>文件上传</h2>
    文件:<input type="file" name="uploadFile"/>
    <br/>
    <br/>
    <input type="submit" value="上传"/>
</form>
<br/>
<br/>
多文件上传
<form action="/fileUploadList" method="post" enctype="multipart/form-data">
    <h2>文件上传</h2>
    name:<input type="text" name="name"/><br/>
    type:<input type="text" name="type"/><br/>
    file1:<input type="file" name="images[0]"/><br/>
    file2:<input type="file" name="images[1]"/>

    <br/>
    <br/>
    <input type="submit" value="上传"/>
</form>

<br/>
<br/>
文件下载
<br/>
<<<a href="/fileDownload">文件 testDownload.pdf 下载</a>>
</body>
</html>
