<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Language" content="zh-CN">
	<title>首页</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="copyright" content="" />
	<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<!--标准mui.css-->
	<%--<link rel="stylesheet" href="/property_system/css/wx/mui.min.css">--%>
	<!--App自定义的css-->
	<%--<link rel="stylesheet" type="text/css" href="/property_system/css/wx/app.css"/>--%>
	<%--<script src="/property_system/js/wx/mui.min.js"></script>--%>
	<link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
	<link href="/property_system/css/wx/common2.css" rel="stylesheet" type="text/css" />
	<script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
	<script src="/property_system/js/wx/mui.min.js"></script>
	<script src="/property_system/js/wx/common.js"></script>
	<script src="/property_system/js/wx/home.js"></script>
</head>
<body class="bgw" onload="initDataAndImg()">
<%--<nav class="mui-bar mui-bar-tab">--%>
	<%--<a class="mui-tab-item mui-active" href="/property_system/wx/v1.0.0/index">--%>
		<%--<span class="mui-icon mui-icon-home"></span>--%>
		<%--<span class="mui-tab-label">首页</span>--%>
	<%--</a>--%>
	<%--<a class="mui-tab-item" href="/property_system/wx/v1.0.0/propertyCenter">--%>
		<%--<span class="mui-icon mui-icon-help"></span>--%>
		<%--<span class="mui-tab-label">物业中心</span>--%>
	<%--</a>--%>
	<%--<a class="mui-tab-item" href="/property_system/wx/v1.0.0/my">--%>
		<%--<span class="mui-icon mui-icon-person"></span>--%>
		<%--<span class="mui-tab-label">我的</span>--%>
	<%--</a>--%>
<%--</nav>--%>
<!-- 头部STR -->
<div class="head">
	<div class="serch">
		<input type="text" placeholder="搜服务/通知">
		<a href="" class="searchsubmit">搜索</a>
	</div>
	<a href="" class="address">曲靖市</a>
	<a href="/property_system/wx/v1.0.0/joinPropertyNotice" class="msg sel"></a> <!-- sel表示有消息  -->
</div>
<div class="h88"></div>
<!-- 头部END -->
<!-- 蓝色渐变背景 -->
<div class="gradientbg"></div>

<!-- 滚动图STR -->
<div class="indexbanner" style="height: auto;">
	<%--<div class="swiper-container">--%>
		<%--<div class="swiper-wrapper">--%>
			<%--<div class="swiper-slide"><a href="https://mp.weixin.qq.com/s/0yykKBaWoXwxvAqifL2qwA"><img src="/property_system/images/wx/business1.png" alt=""></a></div>--%>
			<%--<div class="swiper-slide"><a href="https://mp.weixin.qq.com/s/RpBhsoqQ4WKhI_cg47tqlQ"><img src="/property_system/images/wx/jinka_background.png" alt=""></a></div>--%>
			<%--<div class="swiper-slide"><a href="https://mp.weixin.qq.com/s/0yykKBaWoXwxvAqifL2qwA"><img src="/property_system/images/wx/business3.png" alt=""></a></div>--%>
		<%--</div>--%>
		<%--<div class="swiper-pagination"></div>--%>
	<%--</div>--%>
	<%--<link rel="stylesheet" type="text/css" href="/property_system/plus/swiper/css/swiper.min.css">--%>
	<%--<script type="text/javascript" src="/property_system/plus/swiper/js/swiper.min.js"></script>--%>
	<%--<script>--%>
        <%--var mySwiper = new Swiper('.swiper-container', {--%>
            <%--autoplay: 5000, //可选选项，自动滑动--%>
            <%--initialSlide: 1,--%>
            <%--autoplayDisableOnInteraction: true,--%>
            <%--centeredSlides: true,--%>
            <%--autoHeight: true,--%>
            <%--slidesPerView: 1.15,--%>
            <%--pagination: '.swiper-pagination',--%>
            <%--effect: 'coverflow',--%>
            <%--coverflow: {--%>
                <%--rotate: 0,--%>
                <%--stretch: 10,--%>
                <%--depth: 200,--%>
                <%--modifier: 2,--%>
                <%--slideShadows: true--%>
            <%--}--%>
        <%--})--%>
	<%--</script>--%>
		<!--轮播图-->
		<div id="slider" class="mui-slider">
			<!--轮播图片-->
			<div class="mui-slider-group mui-slider-loop" id="slider_group"></div>
			<!--导航圆点-->
			<div class="mui-slider-indicator" id="slider_dot" style="display: none;"></div>
		</div>
</div>
<!-- 滚动图END -->

<!-- 入口 -->
<div class="indexmenulist">
	<a href="/property_system/wx/v1.0.0/joinStudentAuthorization" class="item">
		<span class="img">
			<img src="/property_system/images/wx/student_card_wnk.png" alt="">
		</span>万能卡</a>
	<%--<a href="/property_system/wx/v1.0.0/propertyCenter" class="item"><span class="img"><img src="/property_system/images/wx/icon/icon_menu_sqfw.svg" alt=""></span>物业中心</a>--%>
	<a href="/property_system/wx/v1.0.0/joinAdultAuthorization" class="item">
		<span class="img">
			<img src="/property_system/images/wx/honorable_card_wnk.png" alt="">
		</span>万能卡</a>
	<a href="/property_system/wx/v1.0.0/universalCardLegal" class="item">
		<span class="img">
			<img src="/property_system/images/wx/vip_business_icon.png" alt="">
		</span>VIP商家</a>
	<%--<a href="" class="item"><span class="img"><img src="/property_system/images/wx/icon/icon_menu_tgmf.svg" alt=""></span>团购买房</a>--%>
	<%--<a href="" class="item"><span class="img"><img src="/property_system/images/wx/icon/icon_menu_zxsq.svg" alt=""></span>团购装修</a>--%>
	<%--<a href="" class="item"><span class="img"><img src="/property_system/images/wx/icon/icon_menu_tgjj.svg" alt=""></span>团购家具</a>--%>
	<%--<a href="" class="item"><span class="img"><img src="/property_system/images/wx/icon/icon_menu_axdk.svg" alt=""></span>装修贷款</a>--%>
</div>

<!-- 热门推荐 -->
<%--<div class="hotlist">--%>
	<%--<p class="title">热门推荐</p>--%>
	<%--<div class="list">--%>
		<%--<a href="" class="item">--%>
			<%--<span class="img"><img src="/property_system/images/wx/img_goods.jpg" alt=""></span>--%>
			<%--<p class="name">卧室舒适座椅 现代简约</p>--%>
			<%--<p class="price">¥ 3599.00 <span>¥ 499.00</span></p>--%>
		<%--</a>--%>
		<%--<a href="" class="item">--%>
			<%--<span class="img"><img src="/property_system/images/wx/img_goods.jpg" alt=""></span>--%>
			<%--<p class="name">卧室舒适座椅 现代简约</p>--%>
			<%--<p class="price">¥ 3599.00 <span>¥ 499.00</span></p>--%>
		<%--</a>--%>
		<%--<a href="" class="item">--%>
			<%--<span class="img"><img src="/property_system/images/wx/img_goods.jpg" alt=""></span>--%>
			<%--<p class="name">卧室舒适座椅 现代简约</p>--%>
			<%--<p class="price">¥ 3599.00 <span>¥ 499.00</span></p>--%>
		<%--</a>--%>
		<%--<a href="" class="item">--%>
			<%--<span class="img"><img src="/property_system/images/wx/img_goods.jpg" alt=""></span>--%>
			<%--<p class="name">卧室舒适座椅 现代简约</p>--%>
			<%--<p class="price">¥ 3599.00 <span>¥ 499.00</span></p>--%>
		<%--</a>--%>
		<%--<a href="" class="item">--%>
			<%--<span class="img"><img src="/property_system/images/wx/img_goods.jpg" alt=""></span>--%>
			<%--<p class="name">卧室舒适座椅 现代简约</p>--%>
			<%--<p class="price">¥ 3599.00 <span>¥ 499.00</span></p>--%>
		<%--</a>--%>
		<%--<a href="" class="item">--%>
			<%--<span class="img"><img src="/property_system/images/wx/img_goods.jpg" alt=""></span>--%>
			<%--<p class="name">卧室舒适座椅 现代简约</p>--%>
			<%--<p class="price">¥ 3599.00 <span>¥ 499.00</span></p>--%>
		<%--</a>--%>
	<%--</div>--%>
<%--</div>--%>
<!-- 底部菜单STR -->
<div class="h108"></div>
<div class="footermenu">
	<a href="/property_system/wx/v1.0.0/index" class="item home sel">首页</a>
	<a href="https://mp.weixin.qq.com/s/x__L4FX98tu0-cd22xkZMA" class="item card_introduction">卡片简介</a>
	<a href="/property_system/wx/v1.0.0/joinWnkMyOrderHome" class="item bus">我的订单</a>
	<%--<a href="/property_system/wx/v1.0.0/propertyCenter" class="item wy">物业中心</a>--%>
	<a href="/property_system/wx/v1.0.0/my" class="item my">我的</a>
</div>
</body>
</html>
