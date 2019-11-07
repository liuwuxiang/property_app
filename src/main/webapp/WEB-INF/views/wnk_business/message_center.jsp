<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>消息中心</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/message_center.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
  <script src="/property_system/js/wnk_business/message_center.js"></script>
</head>
<body class="bgw">
	<!-- 头部STR -->
  <div class="head">
    <h1>消息中心</h1>
    <a onclick="back()" class="back"></a>
  </div>
  <div class="h88"></div>
  <!-- Tab菜单 STR -->
  <div class="pubtabmenu">
    <div class="wrap">
      <a class="item sel" onclick="listOptionInit(0)" id="system_message_item">系统消息</a>
      <a class="item" onclick="listOptionInit(1)" id="my_message_item">我的消息</a>
    </div>
  </div>
<!-- 明细STR -->
  <div class="condetaile">
    <ul class="system_message_list" id="system_message_ul">
      <li>
      	<a class="system_message_title">银币收益提醒</a>
      	<a class="system_message_content">您的团队13390517636兑换银币6个，您获得6个银币奖励</a>
      	<a class="system_message_time">2017-09-10 16:30:20</a>
      </li>
      <li>
      	<a class="system_message_title">银币收益提醒</a>
      	<a class="system_message_content">您的团队13390517636兑换银币6个，您获得6个银币奖励</a>
      	<a class="system_message_time">2017-09-10 16:30:20</a>
      </li>
    </ul>
    <ul class="system_message_list" id="my_message_ul">
      <li>
      	<a class="system_message_title">测试消息</a>
      	<a class="system_message_content">测试消息测试消息测试消息，用于测试</a>
      	<a class="system_message_time">2017-09-10 16:30:20</a>
      </li>
    </ul>
  </div>
  <!-- 明细END -->

  <!-- 积分为空 -->
  <div class="publicnull" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text" id="request_tip">暂无消息</p>
  </div>
</body>
</html>
