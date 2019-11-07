<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>管理员管理</title>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">

	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/admins_manager.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="addAdmins()">
	  		<i class="layui-icon">&#xe654;</i> 添加
		</button>
		<button class="layui-btn layui-btn-sm" onclick="setAdmins()">
	  		<i class="layui-icon">&#xe642;</i> 修改
		</button>
		<button class="layui-btn layui-btn-sm" onclick="deleteAdmins()">
	  		<i class="layui-icon">&#xe640;</i> 删除
		</button>
	</div>

    <div class="members_manager_top_div">
        <form class="layui-form" action="" onsubmit="return false">
            <div class="layui-inline">
                <input class="layui-input" placeholder="请输入姓名" id="name" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="请输入手机号/账号" id="system_order_no" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="请输入职位" id="position" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="请输入类别" id="admin_types_name" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input type="text" class="layui-input" id="join_time" placeholder="请选择加入时间">
            </div>
            <button class="layui-btn layui-btn-sm" data-type="reload" onclick="Search()">
                <i class="layui-icon">&#xe615;</i>搜索
            </button>
        </form>
    </div>
	
	<table class="layui-hide" id="members_manager_table">
		
	</table>
</body>
</html>
