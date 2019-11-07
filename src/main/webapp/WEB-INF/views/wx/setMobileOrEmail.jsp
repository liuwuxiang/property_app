<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>绑定手机</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/setMobileOrEmail.css">
		<script src="/property_system/js/wx/setMobileOrEmail.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData(${type})">
		<a class="tip_tag" id="tip_tag">绑定手机号可以最大程度的保障您的账户安全</a>
		<div class="number_div">
			<a id="number_tag">手机号</a>
			<input type="text" placeholder="请填写新的手机号" id="number_input"/>
		</div>
		<div class="code_div">
			<a>验证码</a>
			<input type="number" placeholder="请填写验证码" id="code_input"/>
			<input type="button" value="获取验证码" onclick="getCode()"/>
		</div>
		<input type="button" value="绑定手机" class="bind_button" id="bind_button" onclick="bindAction()"/>

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
