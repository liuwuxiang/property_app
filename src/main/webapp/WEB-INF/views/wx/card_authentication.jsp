<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>车主认证</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/img/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/owner_authentication.css">
		<script src="/property_system/js/wx/card_authentication.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="iniData()">
		<a class="tip_tag">车牌号</a>
		<div class="input_div">
			<input placeholder="请填写车牌号" id="card_number"/>
		</div>
		<a class="tip_tag">电话号码</a>
		<div class="input_div">
			<input placeholder="请填写车主手机号" id="car_owner_mobile"/>
		</div>
		<a class="tip_tag">真实姓名</a>
		<div class="input_div">
			<input placeholder="请填写车辆登记真实姓名" id="car_owner_name"/>
		</div>
		
		<input type="button" value="提交认证" class="set_button" onclick="submitCardAuthentication()"/>

		<!--UI插件-->
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
