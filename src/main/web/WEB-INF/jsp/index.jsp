<%--
  Created by IntelliJ IDEA.
  Date: 5/13/2022
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>$Title$</title>
      <link rel="shortcut icon" href="/static/iconfont/isolated.svg">
      <link rel="stylesheet" href="/static/css/uigg.css">
      <link rel="stylesheet" href="/static/css/admin.css">
      <script src="/static/js/jquery.min.js" ></script>
      <script src="/static/js/index.js"></script>
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
                <button type="button">??????</button>
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
          <li><a><i class="ico ico-home"></i>??????</a>
          <div class="sider-group anime-fade-in">
            <a href="/home?page=1&cate=??????">??????</a>
            <a href="/home?page=1&cate=??????">??????</a>
            <a href="/home?page=1&cate=??????">??????</a>
          </div>
          </li>
          <li><a><i class="ico ico-app"></i>????????????</a>
            <div class="sider-group anime-fade-in">
              <a href="/mytopic">????????????</a>
              <a href="/myreply">????????????</a>
            </div>
          </li>
        </div>
        <h6>other</h6>
        <div class="sider-list">
          <li><a href="/index/profile"><i class="ico ico-circular-user"></i>user</a></li>
        </div>
      </section>
      <section class="clause">
        <div class="title"><h4>HOME</h4></div>
        <div name="${cate}" id="cate" hidden></div>
        <div class="contant">
          <div class="title flex">
            <h5 class="float-left">${cate}</h5>
            <cite></cite><u></u>
            <a href="/publish" class="btn btn-med">???????????????</a>
          </div>
          <div class="item">
            <div class="list">
              <c:forEach items="${topiclist}" var="topic">
                <a href="/topic?tid=${topic.tid}">
              <li class="center">
                <img src="${topic.u.photopath}" class="float-left" style="margin-top:6px;margin-right:30px;width: 100px;height: 100px;border-radius: 40px">
                <div class="list-cont">
                  <h6>${topic.title}</h6>
                  <p>${topic.content}</p>
                  <div class="list-info">
                    <span>????????????${topic.u.showname}</span>
                    <span class="float-right"><i class="ico ico-time"></i>${topic.date}</span>
                  </div>
                </div>
              </li>
                </a>
              </c:forEach>
            </div>
            <page value="${currentpage}" max="${totalpages}"></page>
          </div>
        </div>
      </section>
    </section>
  </section>
  </body>
</html>
