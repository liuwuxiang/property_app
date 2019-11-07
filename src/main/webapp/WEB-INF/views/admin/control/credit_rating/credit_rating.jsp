<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>信用评级</title>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/credit_rating.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
        <button class="layui-btn layui-btn-sm" onclick="setCreditRating()">
            <i class="layui-icon">&#xe642;</i> 修改
        </button>
	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">

            <div class="layui-inline">
                <input class="layui-input" placeholder="扣信类型" id="clasp_type_name" autocomplete="off">
            </div>

            <div class="layui-input-inline">
                <select id="clasp_object">
                    <option value="">请选择扣信对象</option>
                    <option value="0">会员</option>
                    <option value="1">物业</option>
                </select>
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="扣信额度" id="clasp_value" autocomplete="off">
            </div>

            <div class="layui-input-inline">
                <select id="state">
                    <option value="">请选择审核状态</option>
                    <option value="0">停用</option>
                    <option value="1">启用</option>
                </select>
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
