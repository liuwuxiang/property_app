<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>城市管理</title>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/city_manager.js"></script>
</head>
<body onload="initTableAndData(${province_id})">
	
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="addCity()">
	  		<i class="layui-icon">&#xe654;</i> 添加
		</button>
		<button class="layui-btn layui-btn-sm" onclick="setCity()">
	  		<i class="layui-icon">&#xe65e;</i> 修改
		</button>
	</div>
	
	<table class="layui-hide" id="members_manager_table">
		
	</table>
</body>
</html>
