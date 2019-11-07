<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>生活缴费</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common3.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wx/mobile_phone_open.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/living_payment.js"></script>
</head>
<body>
  <!-- 头部STR -->
  <div class="head">
    <h1>生活缴费</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
  </div>
  <div class="h88"></div>
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <ul class="wangge_ul">
    		<li onclick="billChoose(0)">
    			<img src="/property_system/images/wx/icon/icon_wy_04.svg"/>
    			<a>水费</a>
    		</li>
    		<li onclick="billChoose(1)">
    			<img src="/property_system/images/wx/icon/icon_wy_05.svg"/>
    			<a>电费</a>
    		</li>
    		<li onclick="billChoose(4)">
    			<img src="/property_system/images/wx/icon/icon_wy_06.svg"/>
    			<a>宽带</a>
    		</li>
    		<li onclick="billChoose(3)">
    			<img src="/property_system/images/wx/icon/icon_wy_07.svg"/>
    			<a>电视费</a>
    		</li>
    		<li onclick="billChoose(2)">
    			<img src="/property_system/images/wx/icon/icon_wy_08.svg"/>
    			<a>燃气费</a>
    		</li>
    		<li onclick="billChoose(5)">
    			<img src="/property_system/images/wx/icon/icon_wy_09.svg"/>
    			<a>物业费</a>
    		</li>
    </ul>
  </div>
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
</body>
</html>
