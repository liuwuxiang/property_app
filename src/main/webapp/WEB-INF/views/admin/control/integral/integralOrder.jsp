<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/28
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>积分商城-订单管理</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/member_level.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/integralOrder.js"></script>
</head>
<body onload="initTableAndData()">

<div class="member_level_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="setMemberLevel()">
        <i class="layui-icon">&#xe605;</i> 发货
    </button>
</div>

<div class="member_level_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="订单ID" id="order_id" autocomplete="off">
        </div>
        <div class="layui-inline" style="display: none">
            <input class="layui-input" placeholder="商品名称" id="name" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="购买数量" id="count" autocomplete="off">
        </div>
        <div class="layui-inline" style="display: none">
            <input class="layui-input" placeholder="收件人ID" id="user_id" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="startTime" placeholder="请选择下单时间">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="收件人姓名" id="username" autocomplete="off">
        </div>
        <div class="layui-inline" style="display: none">
            <input class="layui-input" placeholder="收件人联系方式" id="phone" autocomplete="off">
        </div>

        <div class="layui-inline" style="display: none">
            <input class="layui-input" placeholder="收件人地址" id="address" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="status">
                <option value="">请选择订单状态</option>
                <option value="0">已付款</option>
                <option value="1">已发货</option>
                <option value="2">已收货</option>
            </select>
        </div>
        <div class="layui-inline" style="display: none">
            <input class="layui-input" placeholder="快递商家" id="express_name" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="快递单号" id="express_id" autocomplete="off">
        </div>

        <button class="layui-btn" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
        </div>
    </form>
</div>

<table class="layui-hide" id="member_level_manager_table">

</table>
</body>
</html>