<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>账户余额</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/account_balance.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
  <script src="/property_system/js/wnk_business/account_balance.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部STR -->
  <div class="head">
    <h1>账户余额</h1>
    <a onclick="back()" class="back"></a>
  </div>
  <div class="h88"></div>
  <div class="top_div">
  	 <div class="balance_div">
  	 	<a class="balance_tip">可用余额</a>
  	 	<a class="account_balance_value" id="account_balance_value">0</a><span class="money_danwei">元</span>
         <div style="clear: both;"></div>
  	 	<input type="button" value="提现" class="withdraw_button" onclick="joinWithdraw()"/>
  	 </div>
  </div>
  <!-- Tab菜单 STR -->
  <div class="pubtabmenu">
    <div class="wrap">
      <a class="item" id="income_a" onclick="optionClick(0)">收入明细</a>
      <a class="item" id="expenditure_a" onclick="optionClick(1)">支出明细</a>
    </div>
  </div>
  <!-- Tab菜单 END -->
  <ul class="money_detail_ul" id="money_detail_ul">
  	 <%--<li>--%>
  	 	<%--<div class="li_top_div">--%>
  	 		<%--<a class="detail_name">营业收入</a>--%>
  	 		<%--<a class="income_money">+3298.00</a>--%>
  	 	<%--</div>--%>
  	 	<%--<div class="li_bottom_div">--%>
  	 		<%--<a class="detail_time">成功 2015-10-13 12:50:33</a>--%>
  	 		<%--<a class="detail_balance">余额：1200.00</a>--%>
  	 	<%--</div>--%>
  	 <%--</li>--%>
  </ul>

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
