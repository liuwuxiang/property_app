<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>银行管理</title>
	<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/member_level.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/bank_manager.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="member_level_manager_top_div">
        <button class="layui-btn layui-btn-sm" onclick="addBank()">
            <i class="layui-icon">&#xe654;</i> 添加
        </button>
        <button class="layui-btn layui-btn-sm" onclick="setBank()">
            <i class="layui-icon">&#xe642;</i> 修改
        </button>
        <button class="layui-btn layui-btn-sm" onclick="deleteBank()">
            <i class="layui-icon">&#xe640;</i> 删除
        </button>

	</div>

    <div class="member_level_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">


            <div class="layui-inline">
                <input class="layui-input" placeholder="银行名称" id="name" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="银行ID/Code" id="code" autocomplete="off">
            </div>

            <button class="layui-btn layui-btn-sm" data-type="reload" onclick="Search()">
                <i class="layui-icon">&#xe615;</i>搜索
            </button>
        </form>
    </div>
	
	<table class="layui-hide" id="member_level_manager_table">
		
	</table>
</body>
</html>
