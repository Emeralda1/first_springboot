<%--
  Created by IntelliJ IDEA.
  Date: 5/13/2022
  Time: 11:09 AM
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
    <script src="/static/js/register.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="registerbox">
        <div class="row" style="width: 350px;height: 650px;margin-top: 20px">
            <form id="signupform" action="/index/login" method="post">
            <div class="col-md-12">
                <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                    <span class="text">用户名：</span>
                </div>
                <div class="col-md-12" style="width: 300px;height: 30px; margin-bottom: 5px">
                    <input class="inputediv" type="text" name="username">
                </div>
                <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                    <span class="text">密码：</span>
                </div>
                <div class="col-md-12" style="width: 300px;height: 30px;">
                    <input class="inputediv" type="password" name="password">
                </div>
                <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                    <span class="text">确认密码：</span>
                </div>
                <div class="col-md-12" style="width: 300px;height: 30px; margin-bottom: 5px">
                    <input class="inputediv" type="password" name="repassword">
                </div>
                <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                    <span class="text">邮箱：</span>
                    <span></span>
                </div>
                <div class="col-md-12" style="width: 300px;height: 30px; margin-bottom: 5px">
                    <input class="inputediv" type="text" name="email">
                </div>
                <div class="col-md-12" style="width: 350px;height: 35px;margin-bottom: 5px;padding: 5px;">
                    <span class="text">昵称：</span>
                </div>
                <div class="col-md-12" style="width: 300px;height: 30px; margin-bottom: 5px">
                    <input class="inputediv" type="text" name="showname">
                </div>
                <a href="/index/login" style="color: #eccccf;opacity: 0.6;"><span>已有账号？点击直接登录</span></a>
                <button class="btncss" type="button" style="margin-top: 15px;margin-left: 220px" onclick="signupcheck()">注册</button>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
