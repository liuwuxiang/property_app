<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>营业统计</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/statistics.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/statistics.js"></script>
	<script src="/property_system/js/wx/hui.js"></script>
	<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="optionClick(0)">
  <!-- 头部STR -->
  <div class="head">
    <h1>统计</h1>
    <!--<a href="" class="back"></a>-->
    <!--<a href="" class="msg sel"></a> <!-- sel表示有消息  -->
  </div>
  <div class="h88"></div>
  <!-- Tab菜单 STR -->
  <div class="pubtabmenu">
    <div class="wrap">
      <a class="item" id="order_statistics" onclick="optionClick(0)">订单统计</a>
      <a class="item" id="seal_statistics" onclick="optionClick(1)">销售统计</a>
    </div>
  </div>
  <%--<div class="statistics_time_div">--%>
  	 <%--<input type="text" value="" placeholder="选择日期" class="start_time fselect" id="selectDatetime"/>--%>
  	 <%--<a>~</a>--%>
  	 <%--<input type="text" value="" placeholder="选择日期" class="fselect" id="selectDatetimeend"/>--%>
  <%--</div>--%>
  <div class="order_statistics_div" id="order_statistics_div">
  	<!--
      	作者：offline
      	时间：2018-08-29
      	描述：新订单
      -->
      <div class="new_order_div">
      	<div class="order_top_div">
      		<img src="/property_system/images/wnk_business/new_order_icon.png"/>
      		<a>新订单</a>
      	</div>
      	<ul class="new_order_statistics_ul" id="new_order_statistics_ul">
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8%</div>
						</div>
					</div>
      			</div>
      		</li>
      	</ul>
      </div>
      
      <!--
      	作者：offline
      	时间：2018-08-29
      	描述：已完成订单
      -->
      <div class="new_order_div finish_order_div">
      	<div class="order_top_div finish_order_top_div">
      		<img src="/property_system/images/wnk_business/finish_order_icon.png"/>
      		<a>已完成订单</a>
      	</div>
      	<ul class="new_order_statistics_ul" id="finish_order_statistics_ul">
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8%</div>
						</div>
					</div>
      			</div>
      		</li>
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8%</div>
						</div>
					</div>
      			</div>
      		</li>
      	</ul>
      </div>
  </div>
  
  
  <div class="order_statistics_div seal_statistics_div" id="seal_statistics_div">
  	<!--
      	作者：offline
      	时间：2018-08-29
      	描述：新订单
      -->
      <div class="new_order_div">
      	<div class="order_top_div">
      		<img src="/property_system/images/wnk_business/number_seal_icon.png"/>
      		<a>按量统计</a>
      	</div>
      	<ul class="new_order_statistics_ul" id="order_count_statistics_ul">
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8单</div>
						</div>
					</div>
      			</div>
      		</li>
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8单</div>
						</div>
					</div>
      			</div>
      		</li>
      	</ul>
      </div>
      
      <!--
      	作者：offline
      	时间：2018-08-29
      	描述：已完成订单
      -->
      <div class="new_order_div finish_order_div">
      	<div class="order_top_div finish_order_top_div">
      		<img src="/property_system/images/wnk_business/amount_seal_icon.png"/>
      		<a>按金额统计</a>
      	</div>
      	<ul class="new_order_statistics_ul" id="amount_count_statistics_ul">
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">800元</div>
						</div>
					</div>
      			</div>
      		</li>
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8000元</div>
						</div>
					</div>
      			</div>
      		</li>
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">800元</div>
						</div>
					</div>
      			</div>
      		</li>
      		<li>
      			<a class="month_name_tag">09月</a>
      			<div class="progress_div">
      				<div class="progress">
						<div class="progress-bar progress-bar-info progress-bar-striped active" style="width: 85%;">
							<div class="progress-value">8000元</div>
						</div>
					</div>
      			</div>
      		</li>
      	</ul>
      </div>
  </div>

  <!-- 底部菜单STR -->
  <div class="h108"></div>
  <div class="footermenu">
    <a href="/property_system/wnk_business/joinOrders" class="item order">订单</a>
    <a href="/property_system/wnk_business/joinCommodity" class="item commodity">商品</a>
    <a href="/property_system/wnk_business/joinStatistics" class="item wy sel">统计</a>
    <a href="/property_system/wnk_business/joinBusinessCenter" class="item my">我的</a>
  </div>
  <!-- 底部菜单END -->


  <script src="/property_system/js/wnk_business/common.js"></script>
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">    
  <script src="/property_system/plus/lib/jquery-2.1.4.js"></script>
  <script src="/property_system/plus/lib/fastclick.js"></script>
  <script>
    $(function() {
      FastClick.attach(document.body);
    });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
  <script src="/property_system/plus/js/city-picker.js"></script>
  <script type="text/javascript">
    // 日期选择
    $("#selectDatetime").datetimePicker({
      times: function () {
        return null;
      },
      onChange: function (picker, values, displayValues) {
        console.log(values);
      },
    });
    // 日期选择
    $("#selectDatetimeend").datetimePicker({
      times: function () {
        return null;
      },
      onChange: function (picker, values, displayValues) {
        console.log(values);
      },
    });
  </script>
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
