<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>积分设置</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/integral_setting.js"></script>
</head>
<body>
	<div class="top_div">
		<button class="layui-btn" onclick="saveInformation()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>积分与银币兑换比例(多少个积分可兑换一个银币)</a>
			<input type="text" name="title" placeholder="请输入多少个积分可兑换一个银币(如：2000)" value="${integral_coin}" id="integral_coin" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>积分与人民币兑换比例(多少个积分可兑换一元人民币)</a>
			<input type="text" name="title" placeholder="请输入多少个积分可兑换一元人民币(如：2000)" value = "${integral_rmb}" id="integral_rmb" autocomplete="off" class="layui-input">
		</li>
	</ul>
</body>
</html>
