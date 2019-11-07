<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>消费积分</title>
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
  <script src="/property_system/js/wx/consumption_integral.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="listOptionInit(0)">
  <!-- 头部END -->

  <!-- 积分STR -->
  <div class="jfhead" style="height: 155px">
    <a onclick="joinIntegralHelp()" class="help" style="width: 100%;text-align: right;display: block;color: white;">帮助说明</a>
    <div class="desc" style="width: 100%;height: 70px;">
      <span class="tit" style="width: 100%;text-align: center;">积分余额(分)</span>
      <p class="num" id="consumption_balance" style="width: 100%;text-align: center;">0</p>
    </div>
    <div class="hadle" style="width: 100%;text-align: center;margin-top: -11px;height: 50px;padding: 0;">
      <%--<a onclick="joinIntegralHelp()" class="help">帮助说明</a>--%>
      <div class="buts">
        <a class="b1" onclick="joinIntegralRechargeDetail()">充值</a>
        <a onclick="joinIntegralExchange()">兑换</a>
      </div>
    </div>
  </div>
  <!-- 积分END -->

  <!-- Tab菜单 STR -->
  <div class="pubtabmenu" id="pubtabmenu">
    <div class="wrap">
      <a class="item sel" id="xfmx_item" onclick="listOptionInit(0)">消费明细</a>
      <a class="item" id="czmx_item" onclick="listOptionInit(1)">充值明细</a>
    </div>
  </div>
  <!-- Tab菜单 END -->

  <!-- 明细STR -->
  <div class="condetaile">
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
    <ul class="list" id="recharge_detail_ul">
      <li class="item">
        <div class="left">
          <span class="name">银币兑换</span>
          <span class="time">余额：1100</span>
        </div>
        <div class="right">
        	  <span class="time">2018-03-04 15:40:30</span>
          <span class="num down">+1800</span>
        </div>
      </li>
    </ul>
  </div>
  <!-- 明细END -->

  <!-- 积分为空 -->
  <div class="publicnull" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text" id="request_tip">暂无消费记录</p>
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
      window.addEventListener('pageshow', function(e) {
          // 通过persisted属性判断是否存在 BF Cache
          if (e.persisted) {
              getUserBaseInformation();
          }
      });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>


</body>
</html>
