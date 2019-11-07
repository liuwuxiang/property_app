<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>商品管理</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <!--标准mui.css-->
  <link rel="stylesheet" href="/property_system/css/wnk_business/mui.min.css">
  <link href="/property_system/css/wnk_business/commodities.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/commodities.js"></script>
	<script src="/property_system/js/wx/hui.js"></script>
	<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部STR -->
  <div class="head">
    <h1>商品管理</h1>
    <!--<a href="" class="back"></a>-->
    <a href="#topPopover_div" class="add"></a> <!-- sel表示有消息  -->
  </div>
  <!--<div class="h88"></div>-->
 <!--
 	作者：offline
 	时间：2018-08-29
 	描述：商品分类ul
 -->
  <ul class="commodities_type_ul" id="commodities_type_ul">
  	<%--<li class="type_li" id="type_li_1" onclick="typeClick('type_li_1',0)">--%>
  		<%--<a class="type_name">温馨提示</a>--%>
  	<%--</li>--%>
  </ul>
  
  <!--
  	作者：offline
  	时间：2018-08-29
  	描述：商品div
  -->
  <div class="content_div" id="content_div">
	  <div class="commodity_div" id="commodity_div">
	  	 <ul class="commodity_ul" id="content1">
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品1</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 </ul>
	  	 <%--<ul class="commodity_ul" id="content2">--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品2</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品2</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品2</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品2</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 <%--</ul>--%>
	  	 <%--<ul class="commodity_ul" id="content3">--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品3</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品3</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品3</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 	<%--<li onclick="setCommodity(0)">--%>
	  	 		<%--<img src="/property_system/images/wnk_business/integral_card_background.png"/>--%>
	  	 		<%--<div class="commodity_information_div">--%>
	  	 			<%--<a class="commodity_name_tag">测试商品测试商品3</a>--%>
	  	 			<%--<a class="commodity_describe">不素之霸双层牛宝1个+中薯条1分不素之霸双层牛宝1个+中薯条1分</a>--%>
	  	 			<%--<a class="month_sale_tag">上架</a>--%>
	  	 			<%--<a class="commodity_price_tag">￥33</a>--%>
	  	 		<%--</div>--%>
	  	 	<%--</li>--%>
	  	 <%--</ul>--%>
	  </div>
  </div>
  

  <!-- 底部菜单STR -->
  <div class="h108"></div>
  <div class="footermenu">
    <a href="/property_system/wnk_business/joinOrders" class="item order">订单</a>
    <a href="/property_system/wnk_business/joinCommodity" class="item commodity sel">商品</a>
    <a href="/property_system/wnk_business/joinStatistics" class="item wy">统计</a>
    <a href="/property_system/wnk_business/joinBusinessCenter" class="item my">我的</a>
  </div>
  <!-- 底部菜单END -->

<!--右上角弹出菜单-->
		<div id="topPopover_div" class="mui-popover mui-popover_div">
			<div class="mui-popover-arrow"></div>
			<div class="mui-scroll-wrapper">
				<div class="mui-scroll">
					<ul class="mui-table-view mui-table-view_ul">
						<li class="mui-table-view-cell">
							<a href="/property_system/wnk_business/joinAddCommodity">添加商品</a>
						</li>
						<li class="mui-table-view-cell"><a href="/property_system/wnk_business/joinCommodityTypeManager">分类管理</a>
						</li>
					</ul>
				</div>
			</div>

		</div>

<script src="/property_system/js/wnk_business/mui.min.js"></script>
<script>
			mui.init();
			
			mui.plusReady(function () {
			});
			
			mui('.mui-scroll-wrapper').scroll();
			mui('body').on('shown', '.mui-popover', function(e) {
				//console.log('shown', e.detail.id);//detail为当前popover元素
			});
			mui('body').on('hidden', '.mui-popover', function(e) {
				//console.log('hidden', e.detail.id);//detail为当前popover元素
			});
		</script>
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
