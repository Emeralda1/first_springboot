<%--
  Created by IntelliJ IDEA.
  Date: 5/13/2022
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/static/css/back.css" rel="stylesheet" type="text/css">
    <script src="/static/css/bootstrap.js"></script>
    <script src="/static/js/jquery-3.6.0.min.js"></script>
    <script src="/static/js/loginjs.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="loginbox">
    <div class="row" style="width: 350px;height: 300px;">
        <form id="loginform" method="post">
            <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                <p class="text">用户名：</p>
            </div>
            <div class="col-md-12" style="width: 300px;height: 30px; margin-bottom: 5px">
                <input class="inputediv" type="text" name="username">
            </div>
            <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                <p class="text">密码：</p>
            </div>
            <div class="col-md-12" style="width: 300px;height: 30px;">
                <input class="inputediv" type="password" name="password">
            </div>
            <button class="btncss btntext" id="loginbtn" style="margin-left: 10px;margin-right: 80px;margin-top: 40px;" onclick="logincheck()" type="button">登录</button>
            <a href="/index/register"><button class="btncss btntext" style="margin-top: 40px;" type="button">注册</button></a>
        </form>
    </div>
    </div>
</div>
</body>
</html>
