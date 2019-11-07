<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商家充值订单</title>
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/business_recharge_orders.js"></script>
</head>
<body onload="initTableAndData()">
<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="memberRecharge()">
        <i class="layui-icon">&#xe65e;</i> 积分充值
    </button>
</div>

<div class="members_manager_top_div">
    <%--搜索--%>
    <form class="layui-form" action="" onsubmit="return false;">
        <div class="layui-inline">
            <input class="layui-input" placeholder="充值商家" id="business_id" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="充值金额" id="amount" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="state">
                <option value="">请选择充值状态</option>
                <option value="0">待支付</option>
                <option value="1">已支付</option>
                <option value="2">支付失败</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="订单号" id="order_no" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="create_time" placeholder="请选择充值时间">
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
