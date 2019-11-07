<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>商户中心</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/my.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部STR -->
  <div class="head">
    <h1>商户中心</h1>
    <!--<a href="" class="back"></a>-->
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->  
  </div>
  <div class="h88"></div>
  
  <!-- 入口STR -->
  <div class="memmenu" style="margin-top: 0px;border-width: 1px;">
    <a href="/property_system/wnk_business/joinShopInformation" class="item member">店铺信息</a>
    <a href="/property_system/wnk_business/joinAccountBalance" class="item balance">账户余额<span id="balance_tag">0元</span></a>
    <%--<a href="/property_system/wnk_business/joinMessageCenter" class="item message">消息中心</a>--%>
    <%--<a href="/property_system/wnk_business/joinIntegral" class="item integral_system">积分系统</a>--%>
    <a class="item integral_system"  onclick="kaifazhong()">积分系统</a>
    <a class="item member_system"    onclick="kaifazhong()">会员系统</a>
    <a class="item extension_system" onclick="kaifazhong()">推广系统</a>
    <a href="/property_system/wnk_business/joinAccountSecurity"    class="item securty">账户安全</a>
    <a href="/property_system/wnk_business/joinSuggestionFeedback" class="item suggest">投诉建议</a>
    <a href="/property_system/wx/v1.0.0/aboutUs" class="item about_us">关于我们</a>
  </div>
  <div class="pubhandle">
    <a onclick="exitLogin()" class="defualt">退出登录</a>
  </div>

  <!-- 底部菜单STR -->
  <div class="h108"></div>
  <div class="footermenu">
    <a href="/property_system/wnk_business/joinOrders"         class="item order"    >订单</a>
    <a href="/property_system/wnk_business/joinCommodity"      class="item commodity">商品</a>
    <a href="/property_system/wnk_business/joinStatistics"     class="item wy"       >统计</a>
    <a href="/property_system/wnk_business/joinBusinessCenter" class="item my sel"   >我的</a>
  </div>
  <!-- 底部菜单END -->


  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
  <%--<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>--%>
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
