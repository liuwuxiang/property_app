<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>会员等级管理</title>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/member_level.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/member_level.js"></script>
</head>
<body onload="initTableAndData()">
	<%--操作--%>
	<div class="member_level_manager_top_div">
        <button class="layui-btn layui-btn-sm" onclick="setMemberLevel()">
            <i class="layui-icon">&#xe642;</i> 修改
        </button>

	</div>

    <div class="member_level_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">
            <div class="layui-inline">
                <input class="layui-input" placeholder="等级名称" id="name" autocomplete="off">
            </div>

            <div class="layui-input-inline">
                <select id="is_default_level">
                    <option value="">请选择默认等级</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="消耗兑换币积分" id="recharge_consumption_integral" autocomplete="off">
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
