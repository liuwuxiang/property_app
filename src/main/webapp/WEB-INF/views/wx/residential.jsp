<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>小区选择</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/img/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/city_choose.css">
		<script src="/property_system/js/wx/residential.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData(${city_id})">
		<ul class="content_ul" id="content_ul">
			<li onclick="residentialClick(0,'文化空间')">
				<a>文化空间</a>
			</li>
			<li onclick="residentialClick(0,'文化空间')">
				<a>文化空间</a>
			</li>
			<li onclick="residentialClick(0,'文化空间')">
				<a>文化空间</a>
			</li>
		</ul>
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
