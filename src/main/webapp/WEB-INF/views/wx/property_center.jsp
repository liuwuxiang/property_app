<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Language" content="zh-CN">
	<title>物业中心</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="copyright" content="" />
	<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<link href="/property_system/css/wx/common3.css" rel="stylesheet" type="text/css" />
	<script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
	<script src="/property_system/js/wx/common.js"></script>
</head>
<body class="bgw">
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

<!-- 快捷入口 -->
<div class="quickmenu">
	<a href="/property_system/wx/v1.0.0/joinMobilePhoneOpen" class="item"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_top_01.svg" alt=""></span>访客通行</a>
	<a href="/property_system/wx/v1.0.0/joinMobilePhoneOpen" class="item"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_top_02.svg" alt=""></span>手机开门</a>
	<a href="/property_system/wx/v1.0.0/joinLivingPayment" class="item"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_top_03.svg" alt=""></span>生活缴费</a>
</div>

<!-- 我的应用 -->
<div class="wymyapp">
	<p class="tit">我的应用<span>(可放10个)</span><a href="" class="edit">编辑</a></p>
	<div class="applist">
		<a href="/property_system/wx/v1.0.0/joinPedestrianPassage" class="item"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_01.svg" alt=""></span>访客通行</a>
		<a href="/property_system/wx/v1.0.0/joinLivingPayment" class="item"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_02.svg" alt=""></span>生活缴费</a>
		<!--<a href="" class="item"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_03.svg" alt=""></span>社区商圈</a>-->
	</div>
</div>

<!-- 通知公告 -->
<div class="noticewrap clearpadding">
	<div class="box">
		<p class="tit">通知公告</p>
		<div class="noticelist">
			<div class="swiper-container swiper-container-vertical">
				<div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, -27px, 0px);margin-top: -10px;">
					<div class="swiper-slide swiper-slide-prev" style="height: 27px;"><a href="">关于端午节装修施工通知</a></div>
					<div class="swiper-slide swiper-slide-active" style="height: 27px;"><a href="">关于端午节装修施工通知</a></div>
					<div class="swiper-slide swiper-slide-next" style="height: 27px;"><a href="">关于端午节装修施工通知</a></div>
				</div>
			</div>
			<link rel="stylesheet" type="text/css" href="/property_system/plus/swiper/css/swiper.min.css">
			<script type="text/javascript" src="/property_system/plus/swiper/js/swiper.min.js"></script>
			<script type="text/javascript">
                var mySwiper = new Swiper('.swiper-container',{
                    direction : 'vertical',
                    autoplay:2000,
                })
			</script>
		</div>
	</div>
</div>
<div class="publine"></div>

<div class="appwrap" id="appwrap">
	<div class="leftnav">
		<a href="javascript:;" class="item sel">生活缴费</a>
		<a href="javascript:;" class="item">访客通行</a>
		<a href="javascript:;" class="item">物业服务</a>
		<a href="javascript:;" class="item">手机开门</a>
		<a href="javascript:;" class="item">社区服务</a>
		<a href="javascript:;" class="item">房屋租赁</a>
		<a href="javascript:;" class="item">社区社交</a>
		<a href="javascript:;" class="item">社区商圈</a>
	</div>
	<div class="rightmenu hitems">
		<div class="tablist">
			<div class="tabitem sel">
				<a href="/property_system/wx/v1.0.0/joinChargeWater"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_04.svg" alt=""></span>水费</a>
				<a href="/property_system/wx/v1.0.0/joinElectricityFees"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_05.svg" alt=""></span>电费</a>
				<a href="/property_system/wx/v1.0.0/joinBroadbandPayment"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_06.svg" alt=""></span>宽带</a>
				<a href="/property_system/wx/v1.0.0/joinTVFee"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_07.svg" alt=""></span>电视费</a>
				<a href="/property_system/wx/v1.0.0/joinGasCharge"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_08.svg" alt=""></span>燃气费</a>
				<a href="/property_system/wx/v1.0.0/joinPropertyFee"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_09.svg" alt=""></span>物业费</a>
			</div>
			<div class="tabitem">
				<a href="/property_system/wx/v1.0.0/joinPedestrianPassage"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_10.svg" alt=""></span>车辆通行</a>
				<a href="/property_system/wx/v1.0.0/joinPedestrianPassage"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_11.svg" alt=""></span>行人通行</a>
			</div>
			<div class="tabitem">
				<a href="/property_system/wx/v1.0.0/joinParkingApplication"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_12.svg" alt=""></span>车位申请</a>
				<a href="/property_system/wx/v1.0.0/joinRenovationSubmit"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_13.svg" alt=""></span>装修申请</a>
				<a href="/property_system/wx/v1.0.0/joinRepairSubmit"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_14.svg" alt=""></span>维修</a>
				<a href="/property_system/wx/v1.0.0/joinPropertyComplaint"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_15.svg" alt=""></span>投诉</a>
				<a href="/property_system/wx/v1.0.0/joinContactProperty"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_16.svg" alt=""></span>联系物业</a>
			</div>
			<div class="tabitem">
				<a href="/property_system/wx/v1.0.0/joinPasswordOpenDoor"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_17.svg" alt=""></span>密码开门</a>
				<a href="/property_system/wx/v1.0.0/joinQrcodeOpenDoor"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_18.svg" alt=""></span>二维码开门</a>
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_19.svg" alt=""></span>人脸识别</a>
				<a href="/property_system/wx/v1.0.0/joinSmartCard"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_20.svg" alt=""></span>智能卡片</a>
				<a href="/property_system/wx/v1.0.0/joinIncomeHouseNumber"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_21.svg" alt=""></span>收入房号</a>
				<a href="/property_system/wx/v1.0.0/joinAuthorizationManagement"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_22.svg" alt=""></span>授权管理</a>
				<a href="/property_system/wx/v1.0.0/joinCallTransfer"><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_23.svg" alt=""></span>呼叫转移</a>
			</div>
			<div class="tabitem">
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_24.svg" alt=""></span>家政</a>
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_25.svg" alt=""></span>油烟机清洗</a>
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_26.svg" alt=""></span>家电维修</a>
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_27.svg" alt=""></span>二手市场</a>
			</div>
			<div class="tabitem">
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_28.svg" alt=""></span>我要租房</a>
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_29.svg" alt=""></span>我要出租</a>
			</div>
			<div class="tabitem">
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_30.svg" alt=""></span>论坛</a>
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_31.svg" alt=""></span>即时通讯</a>
			</div>
			<div class="tabitem">
				<a href=""><span class="icon"><img src="/property_system/images/wx/icon/icon_wy_32.svg" alt=""></span>社区商圈</a>
			</div>
		</div>
	</div>
</div>

<!-- 底部菜单STR -->
<div class="h108"></div>
<div class="footermenu">
	<a href="/property_system/wx/v1.0.0/index" class="item home">首页</a>
	<a href="https://mp.weixin.qq.com/s/E_adSHDj9A7TQDEmkQKbyQ" class="item bus">项目招商</a>
	<a href="/property_system/wx/v1.0.0/propertyCenter" class="item wy sel">物业中心</a>
	<a href="/property_system/wx/v1.0.0/my" class="item my">我的</a>
</div>
<script type="text/javascript">
    // 功能模块菜单切换
    selTab('appwrap','item','tabitem',1.96,'hitems');
</script>
</body>
</html>
