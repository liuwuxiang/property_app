<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>新增/修改城市</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/add_city.js"></script>
</head>
<body onload="initView(${type})">
	<input type="hidden" value="${province_id}" id="province_id">
	<input type="hidden" value="${city_id}" id="city_id">
	<div class="top_div">
		<button class="layui-btn" onclick="saveCity()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>城市名称</a>
			<input type="text" name="title" placeholder="请输入城市名称" value="${city_name}" id="city_name" autocomplete="off" class="layui-input">
		</li>
	</ul>
</body>
</html>
