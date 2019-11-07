<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>订单中心</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/orders.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/order.js"></script>
	<script src="/property_system/js/wx/hui.js"></script>
	<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
</head>
<body class="bgw" onload="initData()">
	<!-- 头部STR -->
  <div class="head">
    <h1>订单中心</h1>
    <!--<a href="" class="back"></a>-->
    <a onclick="scanQrCode()" class="scan"></a> <!-- sel表示有消息  -->
  </div>
  <div class="h88"></div>
  <!-- Tab菜单 STR -->
  <div class="pubtabmenu">
    <div class="wrap">
      <a class="item" id="new_order" onclick="optionClick(0)">新订单</a>
      <a class="item" id="finish_order" onclick="optionClick(1)">已完成</a>
		<a class="item" id="quanyi_order" onclick="optionClick(2)">权益订单</a>
    </div>
  </div>
	<a class="top_people_static_tag" id="top_people_static_tag">消费人数：0人</a>
	<a class="top_number_static_tag" id="top_number_static_tag">消费次数：0次</a>
  <!-- Tab菜单 END -->
  <ul class="order_in_progress_ul" id="order_in_progress_ul">
	  <!--
  	<li class="order_li">
  		<div class="li_top_div">
  			<a class="li_top_day_tag">24</a>
  			<a class="li_order_no_tag">NO.1010011011</a>
  		</div>
  		<div class="li_commodites_div">
  			<a class="commodities_number_tag">商品(10)</a>
  			<ul class="commodities_ul">
  				<li>
  					<a class="commodity_name_tag">美式奥尔良鸡腿原味咖喱饭</a>
  					<a class="commodity_number_tag">x1</a>
  					<a class="price_tag">35.00</a>
  				</li>
  				<li>
  					<a class="commodity_name_tag">美式奥尔良鸡腿原味咖喱饭</a>
  					<a class="commodity_number_tag">x1</a>
  					<a class="price_tag">35.00</a>
  				</li>
  			</ul>
  		</div>
  		<div class="li_bottom_div">
  			<a class="line_order_time">2018/09/10 15:30:10</a>
  			<a class="order_price_tag">￥70.00</a>
  		</div>
  	</li>
  	-->
	  <%--权益订单--%>
	  <!--
	  <li class="order_li">
		  <div class="li_top_div">
			  <a class="li_top_day_tag">24</a>
			  <a class="li_order_no_tag">NO.1010011011</a>
		  </div>
		  <div class="li_commodites_div">
			  <a class="commodities_number_tag">基础信息</a>
			  <div class="user_div">
				  <a>会员名称：111</a>
				  <a>会员性别：男</a>
				  <a>卡片等级：金卡</a>
				  <a>用户星级：5星用户</a>
			  </div>
		  </div>
		  <div class="li_bottom_div">
			  <a class="line_order_time">2018/09/10 15:30:10</a>
			  <a class="order_price_tag">成功</a>
		  </div>
	  </li>
	  -->
  </ul>
	
  <!-- 底部菜单STR -->
  <div class="h108"></div>
  <div class="footermenu">
    <a href="/property_system/wnk_business/joinOrders" class="item order sel">订单</a>
    <a href="/property_system/wnk_business/joinCommodity" class="item commodity">商品</a>
    <a href="/property_system/wnk_business/joinStatistics" class="item wy">统计</a>
    <a href="/property_system/wnk_business/joinBusinessCenter" class="item my">我的</a>
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
