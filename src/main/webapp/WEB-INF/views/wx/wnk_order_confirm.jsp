<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="Content-Language" content="zh-CN">
	  <title>提交订单</title>
	  <meta name="keywords" content="">
	  <meta name="description" content="">
	  <meta name="copyright" content="" />
	  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
	  <meta content="yes" name="apple-mobile-web-app-capable">
	  <meta content="black" name="apple-mobile-web-app-status-bar-style">
	  <meta content="telephone=no" name="format-detection">
		<link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
		<script src="/property_system/js/wx/mui.min.js"></script>
	  <link href="/property_system/css/wx/wnk_order_confirm.css" rel="stylesheet" type="text/css" />
	  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
	  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
	  <script src="/property_system/js/wx/common.js"></script>
		<script src="/property_system/js/wx/wnk_order_confirm.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData(${commodity_id},${guige_id},${pay_way})">
		<div class="top_div">
  			<a class="commodities_number_tag">商品</a>
  		</div>
  		
  		<ul class="commodities_ul">
  			<li>
  				<a class="commodity_name_tag" id="commodity_name"></a>
  				<%--<a class="commodity_number_tag">x1</a>--%>
				<%--<input type="number" value="1" class="commodity_number_tag">--%>
				<a class="price_tag" id="commodity_price">35.00</a>
				<div class="number_div">
					<input type="button" value="-" class="jianhao_input" onclick="commodity_number(0)"/>
					<a class="commodity_number_tag" id="commodity_number_tag">1</a>
					<input type="button" value="+" onclick="commodity_number(1)"/>
				</div>
  			</li>
  			<li class="xiaoji_li">
				<a class="order_price" id="order_price"></a>
				<a class="amount_fuhao">￥</a>
				<a class="xiaoji_tip">小计</a>
  			</li>
  		</ul>
  		
  		<div class="top_div pay_way_div">
  			<a class="commodities_number_tag">支付方式</a>
  		</div>
  		<!-- 表单STR -->
		  <div class="pubform">
		    <form>
		      <div class="fgroup" id="pay_war_div">
				  <%--<label class="fradio" style="height: 50px;">--%>
					  <%--<span class="icon" style="background-image: url(/property_system/images/wx/general_integral.png);"></span>--%>
					  <%--<span class="fname">通用积分</span>--%>
					  <%--<input type="radio" name="pay" id="general_integral_radio" checked>--%>
					  <%--<span class="status"></span>--%>
				  <%--</label>--%>

				  <%--<label class="fradio" style="height: 50px;">--%>
					  <%--<span class="icon" style="background-image: url(/property_system/images/wx/consumption_integral.png);"></span>--%>
					  <%--<span class="fname">消费积分</span>--%>
					  <%--<input type="radio" name="pay" id="consumption_integral_radio">--%>
					  <%--<span class="status"></span>--%>
				  <%--</label>--%>

				  <%--<label class="fradio" style="height: 50px;">--%>
					  <%--<span class="icon wxpay"></span>--%>
					  <%--<span class="fname">微信支付</span>--%>
					  <%--<input type="radio" name="pay" id="wx_pay_radio" checked>--%>
					  <%--<span class="status"></span>--%>
				  <%--</label>--%>
					  <%--<label class="fradio" style="height: 50px;">--%>
						  <%--<span class="icon wnkpay"></span>--%>
						  <%--<span class="fname">万能卡支付</span>--%>
						  <%--<input type="radio" name="pay" id="wx_wnk_pay_radio">--%>
						  <%--<span class="status"></span>--%>
					  <%--</label>--%>
		      </div>
		      <div class="fhandle">
		        <a onclick="orderWxPay()" class="fsubmit">支付</a>
		      </div>
		    </form>
		  </div>


		<!-- UI插件 -->
		<link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
		<link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
		<%--<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>--%>
		<script src="/property_system/plus/lib/fastclick.js"></script>
		<script src="/property_system/js/wx/toast.js"></script>
		<script>
            // 显示二维码
            selShow("ewm","ewmshow");
		</script>
		<script>
            $(function() {
                FastClick.attach(document.body);
            });
		</script>
		<script src="/property_system/plus/js/jquery-weui.js"></script>
	</body>
</html>
