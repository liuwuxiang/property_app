<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户充值订单</title>
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/recharge_orders.js"></script>
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
            <input class="layui-input" placeholder="充值用户" id="username" autocomplete="off">
        </div>

        <div class="layui-input-inline">
            <select id="pay_recharge_type">
                <option value="">请选择充值方式</option>
                <option value="0">线上充值</option>
                <option value="1">线下充值</option>
            </select>
        </div>

        <div class="layui-input-inline">
            <select id="pay_type">
                <option value="">请选择支付方式</option>
                <option value="0">微信支付</option>
                <option value="1">支付宝支付</option>
                <option value="2">线下支付</option>
            </select>
        </div>

        <div class="layui-input-inline">
            <select id="pay_state">
                <option value="">请选择充值状态</option>
                <option value="0">订单创建</option>
                <option value="1">待支付</option>
                <option value="2">支付成功</option>
                <option value="3">支付失败</option>
            </select>
        </div>

        <div class="layui-inline">
            <input class="layui-input" placeholder="订单号" id="system_order_no" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="recharge_time" placeholder="请选择充值时间">
        </div>
        <div style="padding: .3rem 0"></div>
        <button class="layui-btn layui-btn-sm" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>
</body>
</html>
