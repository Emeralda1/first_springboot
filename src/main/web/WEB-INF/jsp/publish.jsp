<%--
  Created by IntelliJ IDEA.
  Date: 6/19/2022
  Time: 10:38 PM
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
        "react": {
            "opacityDefault": 0.9,
            "opacityOnHover": 0.2
        }
    });</script>
    <script>
        $(document).ready(function () {
            $("#submit").click(function () {
                alert("经验值加30")
                $("#publishform").submit();
            });
        });
    </script>
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
            <div class="sider-search"><input type="text"><button class="ico ico-search"></button></div>
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
                <li><a href="/index/profile"><i class="ico ico-circular-user"></i>user</a></li>
            </div>
        </section>
        <section class="clause">
            <div class="contant">
                <div class="title">
                    <h5>发表新内容</h5>
                </div>
                <div class="item">
                    <section class="form">
                        <form action="/setnewtopic" method="post" id="publishform">
                            <li><span>标题</span>
                                <input class="med" type="text" required maxlength="20" name="title">
                                <cite></cite>
                            </li>
                            <li><span>类别</span>
                                <select class="sml" name="cate"><option selected value="技术">技术</option><option value="娱乐">娱乐</option></select>
                            </li>
                            <li><span>内容</span>
                                <textarea class="big" required maxlength="1000" name="content"></textarea>
                                <cite></cite>
                            </li>
                            <li class="resolve"><a href="/index"><button class="btn" type="button">取消</button></a><a href=""><button class="btn" id="submit" type="button">发表</button></a></li>
                        </form>
                    </section>
                </div>
            </div>
        </section>
    </section>
</section>
</body>
</html>
