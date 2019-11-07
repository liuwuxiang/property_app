<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>业主认证</title>
	<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/ower_certification.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
        <button class="layui-btn layui-btn-sm" onclick="reviewPass()">
            <i class="layui-icon">&#xe642;</i> 审核通过
        </button>
        <button class="layui-btn layui-btn-sm" onclick="reviewNoPass()">
            <i class="layui-icon">&#x1006;</i> 审核失败
        </button>
	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">
            <div class="layui-inline" >
                <input class="layui-input" placeholder="购房电话" id="buy_house_mobile" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="购房姓名" id="buy_house_name" autocomplete="off">
            </div>

            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="城市" id="city" autocomplete="off">
            </div>

            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="小区" id="residential_name" autocomplete="off">
            </div>

            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="房号" id="house_number" autocomplete="off">
            </div>

            <div class="layui-input-inline">
                <select id="state">
                    <option value="">请选择审核状态</option>
                    <option value="0">待审核</option>
                    <option value="1">认证通过</option>
                    <option value="2">审核未通过</option>
                </select>
            </div>

            <div class="layui-inline">
                <input type="text" class="layui-input" id="submit_date_str" placeholder="请选择提交时间">
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
