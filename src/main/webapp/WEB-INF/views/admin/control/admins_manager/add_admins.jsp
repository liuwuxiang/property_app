<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>新增/修改管理员</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/add_admins.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
</head>
<body onload="initView()">
	<div class="top_div">
		<button class="layui-btn" onclick="saveAdminInformation()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>姓名</a>
			<input type="text" id="name" name="title" placeholder="请输入管理员姓名" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>电话(电话为管理员登录账号)</a>
			<input type="text" id="mobile" name="title" placeholder="请输入管理员电话" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>职位</a>
			<input type="text" id="postion" name="title" placeholder="请输入管理员职位" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>管理员类别</a>
			<select class="layui-select-group" id="layui-select-group">
				<%--<option>物业中心</option>--%>
			</select>
		</li>
	</ul>
</body>
</html>
