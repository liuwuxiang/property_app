<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="Content-Language" content="zh-CN">
	  <title>订单详情</title>
	  <meta name="keywords" content="">
	  <meta name="description" content="">
	  <meta name="copyright" content="" />
	  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
	  <meta content="yes" name="apple-mobile-web-app-capable">
	  <meta content="black" name="apple-mobile-web-app-status-bar-style">
	  <meta content="telephone=no" name="format-detection">
	  <link href="/property_system/css/wx/my_order_detail.css" rel="stylesheet" type="text/css" />
	  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
	  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
	  <script src="/property_system/js/wx/common.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
		<script src="/property_system/js/wnk_business/order_detail.js"></script>
	</head>
	<body onload="initData(${order_id},${type})">
		<div class="top_div">
			<div class="top_business_div" onclick="joinWnkBusinessHome()">
				<a id="business_name">万达国际影城(奥体店)</a>
				<%--<img src="/property_system/images/wx/icon/icon_more.svg"/>--%>
			</div>
			<div class="li_commodites_div" id="li_commodites_div">
	  			<a class="commodities_number_tag" id="commodity_number_tag">商品(10)</a>
	  			<ul class="commodities_ul" id="commodities_ul">
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
	  		<%--<div class="qrcode_div" id="qrcode_div">--%>
	  			<%--<a>使用时出示此二维码</a>--%>
	  			<%--<img src="" id="qr_code_img"/>--%>
	  		<%--</div>--%>
		</div>
		
		<div class="order_information_div">
	  		<a id="line_time_tag">下单时间：</a>
	  		<a id="order_no_tag">订单号：</a>
	  		<a id="count_amount_tag">总价：</a>
	  	</div>

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
