<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>物业管理</title>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/property_manager.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="addProperty()">
	  		<i class="layui-icon">&#xe654;</i> 添加
		</button>
		<button class="layui-btn layui-btn-sm" onclick="setProperty()">
	  		<i class="layui-icon">&#xe642;</i> 修改
		</button>
	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">
            <div class="layui-inline">
                <input class="layui-input" placeholder="主账号" id="account" autocomplete="off">
            </div>

            <div class="layui-inline" >
                <input class="layui-input" placeholder="城市" id="city" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="物业名称" id="residential_name" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="详细地址" id="address" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="联系人" id="contact_name" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="联系电话" id="contact_mobile" autocomplete="off">
            </div>

            <div class="layui-inline" style="padding: .3rem 0">
                <input type="text" class="layui-input" id="create_time" placeholder="请选择入驻时间">
            </div>

            <button class="layui-btn layui-btn-sm "  data-type="reload" onclick="Search()">
                <i class="layui-icon">&#xe615;</i>搜索
            </button>

        </form>
    </div>
	
	<table class="layui-hide" id="members_manager_table">
		
	</table>
</body>
</html>
