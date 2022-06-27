<%--
  Created by IntelliJ IDEA.
  Date: 5/13/2022
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
    <link rel="shortcut icon" href="/static/iconfont/isolated.svg">
    <link rel="stylesheet" href="/static/css/uigg.css">
    <link rel="stylesheet" href="/static/css/admin.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/index.js"/>
    <script src="/static/js/uigg.js"></script>
    <script src="/static/js/admin.js"></script>
    <script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>
    <script>L2Dwidget.init({
    "model": {
        jsonPath: "https://unpkg.com/live2d-widget-model-hijiki@1.0.5/assets/hijiki.model.json",
        "scale": 1
    },
    "display": {
        "position": "left",
        "width": 100,
        "height": 160,
        "hOffset": 0,
        "vOffset": -20
    },
    "mobile": {
        "show": true,
        "scale": 0.5
    },
    "react": {
        "opacityDefault": 0.9,
        "opacityOnHover": 0.2
    }
});</script>
</head>
<body>
<section class="admin anime-fade-in flex flex-column">
    <section class="head">
        <h1 class="logo float-left center anime-zoom-in"><i></i><span>lsc-<em>INDEX</em></span></h1>
        <div class="head-cont float-left anime-fade-in">
            <a class="btn btn-med sider-toggle"><i class="ico ico-menu"></i></a>
        </div>
        <div class="head-cont float-right anime-fade-in">
            <div class="head-info flex float-left">
                <c:choose>
                    <c:when test="${user!=null}">
                        <span>welcome<em>${user.showname}</em></span>
                        <a href="/index/profile" class="float-right"><img src="${user.photopath}" class="float-left" style="margin-top:4px;width: 30px;height: 30px;border-radius: 50%"></a>
                        <a href="/logout" class="ico ico-circular-direction-right"></a>
                    </c:when>
                    <c:otherwise>
                        <a href="/index/login">
                            <button type="button">登录</button>
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>
    <section class="subject">
        <section class="sider">
            <div class="sider-search"><form action="/search" method="get"><input type="text" name="title"><button class="ico ico-search"></button></form></div>
            <h6>basic</h6>
            <div class="sider-list">
                <li><a><i class="ico ico-home"></i>首页</a>
                    <div class="sider-group anime-fade-in">
                        <a href="/home?page=1&cate=全部">全部</a>
                        <a href="/home?page=1&cate=技术">技术</a>
                        <a href="/home?page=1&cate=娱乐">娱乐</a>
                    </div>
                </li>
                <li><a><i class="ico ico-app"></i>我的话题</a>
                    <div class="sider-group anime-fade-in">
                        <a href="/mytopic?cate=">我发表的</a>
                        <a href="/myreply">我回复的</a>
                    </div>
                </li>
            </div>
            <h6>other</h6>
            <div class="sider-list">
                <li><a href="/index/profile" class="active"><i class="ico ico-circular-user"></i>user</a></li>
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
                                <img src="${user.photopath}" class="float-left" style="margin-top:6px;margin-right:10px;width: 55px;height: 55px;border-radius: 40px">
                                <input class="med" type="file" accept="image/*" name="file" id="uploadphoto">
                                <button class="btn" type="submit" id="uploadconfirm" >上传</button>
                                </form>
                            </li>
                            <li>
                                <span>
                                    等级
                                </span>
                                <span name="exp">${user.exp}</span>
                                <span>距离下一级</span>
                                <span name="gap"></span>
                            </li>
                            <li>
                                <span>注册日期</span><span>${user.date}</span>
                            </li>
                    </section>
            </div>
            </div>
        </section>
    </section>
</section>
</body>
</html>
