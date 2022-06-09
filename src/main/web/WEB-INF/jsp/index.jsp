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
        <drop class="float-left">
          <drop-list>
            <ul>
              <li><a>1</a></li>
              <li><a>2</a></li>
              <li><a>3</a></li>
            </ul>
          </drop-list>
        </drop>
      </div>
      <div class="head-cont float-right anime-fade-in">
        <div class="head-info flex float-left">
          <span>welcome<em>${user.showname}</em></span>
          <a href="/index/profile" class="float-right"><img src="${user.photopath}" class="float-left" style="margin-top:4px;width: 30px;height: 30px;border-radius: 50%"></a>
          <a href="/logout" class="ico ico-circular-direction-right"></a>
        </div>
      </div>
    </section>
    <section class="subject">
      <section class="sider">
        <div class="sider-search"><input type="text"><button class="ico ico-search"></button></div>
        <h6>basic</h6>
        <div class="sider-list">
          <li><a href="/index" class="active"><i class="ico ico-home"></i>home</a></li>
        </div>
        <h6>other</h6>
        <div class="sider-list">
          <li><a href="/index/profile"><i class="ico ico-circular-user"></i>user</a></li>
          <li><a><i class="ico ico-volume"></i>notice<span>23</span></a></li>
        </div>
      </section>
    </section>
  </section>
  </body>
</html>
