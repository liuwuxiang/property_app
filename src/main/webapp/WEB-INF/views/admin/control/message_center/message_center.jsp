<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>消息中心</title>
    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="/property_system/css/admin/layui.css">
    <link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/members.css">
    <script src="/property_system/js/admin/layui.js"></script>
    <script src="/property_system/js/admin/lay/modules/jquery.js"></script>
    <script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/message_center.js"></script>
</head>
<body onload="initTableAndData()">
	<%--操作--%>
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="addMessage()">
	  		<i class="layui-icon">&#xe642;</i> 新建消息
		</button>
	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">
            <div class="layui-inline">
                <select id="receiving_object">
                    <option value="">请选择消息对象</option>
                    <option value="0">物业中心</option>
                    <option value="1">所有会员</option>
                    <option value="2">普通会员</option>
                    <option value="3">铂金会员</option>
                    <option value="4">钻石会员</option>
                </select>
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="标题" id="title" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="内容" id="content" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input type="text" class="layui-input" id="send_time" placeholder="请选择时间">
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
