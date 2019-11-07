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
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wx/message_center.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/message_center.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="listOptionInit(0)">
  <!-- Tab菜单 STR -->
  <div class="pubtabmenu" id="pubtabmenu">
    <div class="wrap">
      <a class="item sel" onclick="listOptionInit(0)" id="system_message_item">系统消息</a>
      <a class="item" onclick="listOptionInit(1)" id="my_message_item">我的消息</a>
    </div>
  </div>
  <!-- Tab菜单 END -->

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

</body>
</html>
