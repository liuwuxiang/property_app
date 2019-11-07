<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>银币兑换订单</title>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/soliver_coin_exchange_orders.js"></script>
</head>
<body onload="initTableAndData()">
	
	<%--<div class="members_manager_top_div">--%>
		<%--<button class="layui-btn layui-btn-sm" onclick="setMemberLevel()">--%>
	  		<%--<i class="layui-icon">&#xe642;</i> 查询--%>
		<%--</button>--%>
	<%--</div>--%>
    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">
            <div class="layui-inline">
                <input class="layui-input" placeholder="兑换用户" id="user_mobile" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="消耗积分(个)" id="consume_integral_number" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="获得银币(个)" id="silver_coin_number" autocomplete="off">
            </div>
            <div class="layui-input-inline">
                <select id="type">
                    <option value="">请选择兑换方式</option>
                    <option value="0">消费积分</option>
                    <option value="1">通用积分</option>
                </select>
            </div>


            <div class="layui-inline">
                <input type="text" class="layui-input" id="exchange_date" placeholder="请选择兑换时间">
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
