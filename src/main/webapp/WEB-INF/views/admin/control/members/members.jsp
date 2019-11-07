<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>会员管理-首页</title>

    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
	<link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
	<script src="${ctx}/js/admin/members.js"></script>
</head>
<body onload="initTableAndData()">
	<%--操作--%>
	<div class="members_manager_top_div">
        <button class="layui-btn layui-btn-sm" onclick="setMemberLevel()">
            <i class="layui-icon">&#xe642;</i> 编辑会员信息
        </button>

	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">

            <div class="layui-inline">
                <input class="layui-input" placeholder="手机号" id="mobile" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="昵称" id="nick_name" autocomplete="off">
            </div>

            <div class="layui-input-inline">
                <select id="sex">
                    <option value="">请选择性别</option>
                    <option value="0">男</option>
                    <option value="1">女</option>
                    <option value="2">保密</option>
                </select>
            </div>

            <div class="layui-input-inline">
                <select id="member_card_level">
                    <option value="">请选择开卡类型</option>
                    <option value="-1">未开卡</option>
                    <option value="0">银卡会员</option>
                    <option value="1">金卡会员</option>
                </select>
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="通用积分余额" id="general_integral" autocomplete="off">
            </div>

            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="玫瑰余额" id="silver_coin_balance" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input type="text" class="layui-input" id="register_time" placeholder="请选择注册时间">
            </div>

            <div class="layui-inline" style="display: none">
                <input type="text" class="layui-input" id="recommend_user_name" placeholder="推荐人" autocomplete="off">
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
