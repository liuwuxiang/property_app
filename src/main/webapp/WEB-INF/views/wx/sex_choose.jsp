<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改性别</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/sex_choose.css">
		<script src="/property_system/js/wx/sex_choose.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData()">
		<ul class="sex_ul">
			<li id="male" onclick="sexChooseAction(0)">
				<div>
					<img src="/property_system/images/wx/icon_checkbox.png"/>
					<a>男</a>
				</div>
			</li>
			<li id="female" onclick="sexChooseAction(1)">
				<div>
					<img src="/property_system/images/wx/icon_checkbox.png"/>
					<a>女</a>
				</div>
			</li>
			<li id="secrecy" onclick="sexChooseAction(2)">
				<div>
					<img src="/property_system/images/wx/icon_checkbox.png"/>
					<a>保密</a>
				</div>
			</li>
		</ul>
		
		<input type="button" value="确定" class="set_button" onclick="setUseSex()"/>

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
