<%--
  Created by IntelliJ IDEA.
  Date: 5/13/2022
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
    <link rel="shortcut icon" href="/static/iconfont/isolated.svg">
    <link rel="stylesheet" href="/static/css/uigg.css">
    <link rel="stylesheet" href="/static/css/admin.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/uigg.js"></script>
    <script src="/static/js/admin.js"></script>
</head>
<body>
<section class="admin anime-fade-in flex flex-column">
    <section class="head">
        <h1 class="logo float-left center anime-zoom-in"><i></i><span>lsc-<em>INDEX</em></span></h1>
        <div class="head-cont float-left anime-fade-in">
            <a class="btn btn-med sider-toggle"><i class="ico ico-menu"></i></a>
            <drop class="float-left">
                <drop-list>
                    <ul>
                        <li><a href="/index">1</a></li>
                        <li><a href="/index">2</a></li>
                        <li><a href="/index">3</a></li>
                    </ul>
                </drop-list>
            </drop>
        </div>
        <div class="head-cont float-right anime-fade-in">
            <div class="head-info flex float-left">
                <span>welcome<em>${user.showname}</em></span>
                <a href="/index/profile" class="float-right"><img src="${user.photopath}" class="float-left" style="margin-top:4px;width: 30px;height: 30px;border-radius: 50%"></a>
                <a  href="/logout" class="ico ico-circular-direction-right"></a>
            </div>
        </div>
    </section>
    <section class="subject">
        <section class="sider">
            <div class="sider-search"><input type="text"><button class="ico ico-search"></button></div>
            <h6>basic</h6>
            <div class="sider-list">
                <li><a href="/index"><i class="ico ico-home"></i>home</a></li>
            </div>
            <h6>other</h6>
            <div class="sider-list">
                <li><a href="/index/profile" class="active"><i class="ico ico-circular-user"></i>user</a></li>
                <li><a><i class="ico ico-volume"></i>notice<span>23</span></a></li>
            </div>
        </section>
        <section class="clause">
            <div class="title"><h4>个人信息</h4></div>
            <div class="contant">
                <div class="item">
                    <section class="form">
                            <li><span>用户名</span>
                                <span>${user.username}</span>
                            </li>
                            <li><span>邮箱</span>
                                <span>${user.email}</span>
                            </li>
                            <li><span>昵称</span>
                                <input class="med" type="text" name="showname" value="${user.showname}" required>
                                <button class="btn code" id="editusername" type="button">确认修改</button>
                            </li>
                            <li><span>头像</span>
                                <form enctype="multipart/form-data" action="/uploadphoto" method="post">
                                <img src="${user.photopath}" class="float-left" style="margin-top:6px;margin-right:10px;width: 55px;height: 55px;border-radius: 50%">
                                <input class="med" type="file" accept="image/*" name="file" id="uploadphoto">
                                <button class="btn" type="submit" id="uploadconfirm" >上传</button>
                                </form>
                            </li>
                            <li>
                                <span>注册日期</span><span>${user.signdate}</span>
                            </li>
                    </section>
            </div>
            </div>
        </section>
    </section>
</section>
</body>
</html>
