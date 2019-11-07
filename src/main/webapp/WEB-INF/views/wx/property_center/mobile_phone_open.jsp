<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>手机开门</title>
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
	<script src="/property_system/js/wx/mobile_phone_open.js"></script>
</head>
<body>
  <!-- 头部STR -->
  <div class="head">
    <h1>手机开门</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
  </div>
  <div class="h88"></div>
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <ul class="wangge_ul">
    		<li onclick="jumpNewPage(0)">
    			<img src="/property_system/images/wx/icon/icon_wy_17.svg"/>
    			<a>密码开门</a>
    		</li>
    		<li onclick="jumpNewPage(1)">
    			<img src="/property_system/images/wx/icon/icon_wy_18.svg"/>
    			<a>二维码开门</a>
    		</li>
    		<li onclick="jumpNewPage(2)">
    			<img src="/property_system/images/wx/icon/icon_wy_19.svg"/>
    			<a>人脸识别</a>
    		</li>
    		<li onclick="jumpNewPage(3)">
    			<img src="/property_system/images/wx/icon/icon_wy_20.svg"/>
    			<a>智能卡片</a>
    		</li>
    		<li onclick="jumpNewPage(4)">
    			<img src="/property_system/images/wx/icon/icon_wy_21.svg"/>
    			<a>收入房号</a>
    		</li>
    		<li onclick="jumpNewPage(5)">
    			<img src="/property_system/images/wx/icon/icon_wy_22.svg"/>
    			<a>授权管理</a>
    		</li>
    		<li onclick="jumpNewPage(6)">
    			<img src="/property_system/images/wx/icon/icon_wy_23.svg"/>
    			<a>呼叫转移</a>
    		</li>
    </ul>
  </div>
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
</body>
</html>
