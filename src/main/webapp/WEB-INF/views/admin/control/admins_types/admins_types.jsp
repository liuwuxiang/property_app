<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>管理员类别</title>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/admins_types.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="addAdminsType()">
	  		<i class="layui-icon">&#xe654;</i> 添加
		</button>
		<button class="layui-btn layui-btn-sm" onclick="setAdminsType()">
	  		<i class="layui-icon">&#xe642;</i> 修改
		</button>
	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">
            <div class="layui-inline">
                <input class="layui-input" placeholder="类别名称" id="type_name" autocomplete="off">
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
