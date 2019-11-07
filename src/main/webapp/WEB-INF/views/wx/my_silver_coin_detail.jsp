<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>我的银币明细</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/my_silver_coin_detail.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部END -->  

  <!-- 明细STR -->
  <div class="condetaile">
    <ul class="list" id="list">
      <li class="item">
        <div class="left">
          <span class="name">升级为铂金会员</span>
          <span class="time">余额 6000银币</span>
        </div>
        <div class="right">
          <span class="num down">-1800</span>
          <p class="time">2018-05-20 10:59:59</p>
        </div>
      </li>

      <li class="item">
        <div class="left">
          <span class="name">银币兑换</span>
          <span class="time">余额 6000银币</span>
        </div>
        <div class="right">
          <span class="num up">+600</span>
          <p class="time">2018-05-20 10:59:59</p>
        </div>
      </li>
    </ul>
  </div>
  <!-- 明细END -->
  <!-- 积分为空 -->
  <div class="publicnull" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text">您还没有银币</p>
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



  <script type="text/javascript">
    // tab菜单顶部固定
    pubtabmenu("pubtabmenu",0.88);
  </script>

</body>
</html>
