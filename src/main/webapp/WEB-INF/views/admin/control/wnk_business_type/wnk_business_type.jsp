<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户标签管理-万能卡</title>
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/wnk_business_type/wnk_business_type.js"></script>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addWnkBusinessType()">
        <i class="layui-icon">&#xe654;</i> 新增
    </button>
    <button class="layui-btn layui-btn-sm" onclick="setWnkBusinessType()">
        <i class="layui-icon">&#xe642;</i> 修改
    </button>
    <button class="layui-btn layui-btn-sm" onclick="deleteWnkBusinessType()">
        <i class="layui-icon">&#xe640;</i> 删除
    </button>
</div>

<div class="members_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="分类名称" id="name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="commdity_charge_way">
                <option value="">请选择资费方式</option>
                <option value="0">按次</option>
                <option value="1">按年</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="会员价格" id="commodifty_price" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="make_wnk_state">
                <option value="">请选择万能卡权益</option>
                <option value="0">不执行</option>
                <option value="1">执行</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="discount_type">
                <option value="">请选择优惠方式</option>
                <option value="0">定价</option>
                <option value="1">折扣</option>
            </select>
        </div>
        <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>
</body>
<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/wnk_business_type/wnk_business_type.js"></script>
</html>
