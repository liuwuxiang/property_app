<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>新增/修改管理员类别</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/add_admins_type.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/add_admins_type.js"></script>
</head>
<body>
	<div class="top_div">
		<button class="layui-btn" onclick="saveAction()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>类别名称</a>
			<input type="text" id="type_name" name="title" placeholder="请输入管理员类别名称" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>类别权限</a>
			<form class="layui-form">
				<%--<div class="checkbox_div">--%>
					<%--<input type="checkbox" name="like[write]" title="会员管理">--%>
				<%--</div>--%>
				
			</form>
			
		</li>
	</ul>
	
</body>
</html>
