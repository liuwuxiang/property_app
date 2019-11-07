<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>我的信用</title>
  <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
  <style>
    html, body {
      width: 100%;
      height: 100%;
      margin: 0;
      background: white;
    }
    canvas {
      border: 1px solid #eee;
      /*position: relative;*/
      /*left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);*/
      background: -webkit-linear-gradient(top, #0e83f5 0%, #21bdf6 100%);
      background: -ms-linear-gradient(top, #0e83f5 0%, #21bdf6 100%);
      background: -moz-linear-gradient(top, #0e83f5 0%, #21bdf6 100%);
      /*background: linear-gradient(top, #0e83f5 0%, #21bdf6 100%);*/
      margin:0;
      padding: 0;
    }
    .tip_div{
      background: white;
      padding-top: 0;
      margin-top: -90px;
      z-index: 99999;
      position: relative;
      /*min-height: 50%;*/
    }
  </style>
</head>
  <body>
    <input type="hidden" id="credit_date" value="${credit_date}">
    <canvas id="canvas" height="340" data-score='${credit_number}'></canvas>
    <!--<div class="test_div"></div>-->
    <!-- 明细STR -->
  <div class="condetaile tip_div" id="tip_div">
    <ul class="list" id="consumption_detail_ul">
      <li class="item">
        <div class="left">
          <span class="name">银币兑换</span>
          <span class="time">2018-05-21 10:59:22</span>
        </div>
        <div class="right">
          <span class="num down">-1800</span>
        </div>
      </li>
    </ul>
  </div>
  </body>
  <script src="/property_system/js/wx/credit.js"></script>
  <!-- 积分为空 -->
  <div class="publicnull tip_div" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text" id="request_tip">暂无评级</p>
  </div>

  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">    
  <script src="/property_system/plus/lib/jquery-2.1.4.js"></script>
  <script src="/property_system/plus/lib/fastclick.js"></script>
  <script src="/property_system/js/wx/toast.js"></script>
  <script>
    $(function() {
      FastClick.attach(document.body);
    });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
</html>