<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>新增/修改银行等级</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/add_bank.js"></script>
</head>
<body onload="initView(${type})">
	<input type="hidden" value="${record_id}" id="record_id">
	<div class="top_div">
		<button class="layui-btn" onclick="saveBank()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>银行名称</a>
			<input type="text" name="title" placeholder="请输入银行名称" id="bank_name" value="${bank_name}" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>银行ID/Code</a>
			<input type="text" name="title" placeholder="请输入银行Code" id="bank_code" value="${bank_code}" autocomplete="off" class="layui-input">
		</li>
	</ul>
</body>
</html>
