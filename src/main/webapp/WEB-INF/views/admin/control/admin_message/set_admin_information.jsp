<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>资料修改</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_admin_information.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/set_admin_information.js"></script>
</head>
<body onload="initView()">
	<div class="top_div">
		<button class="layui-btn" onclick="saveInformation()">保存</button>
	</div>
	
	<ul class="add_course_ul">
		<li>
			<a>姓名</a>
			<input type="text" name="title" placeholder="请输入姓名" id="name" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>手机号</a>
			<input type="number" name="title" placeholder="请输入手机号" id="mobile" autocomplete="off" class="layui-input">
		</li>
	</ul>
	
</body>
</html>
