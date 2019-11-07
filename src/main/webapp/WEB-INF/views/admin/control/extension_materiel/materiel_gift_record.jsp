<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>推广物料-赠送记录</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">


</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="backMaterielGift()">
        <i class="layui-icon">&#xe642;</i> 撤销赠送
    </button>
</div>

<div class="members_manager_top_div">

    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="操作人" id="admin_name" autocomplete="off">
        </div>
        <div class="layui-inline" >
            <input class="layui-input" placeholder="受赠商家" id="business_name" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="受赠商家账号" id="business_mobile" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="materiel_id">
                <option value="">物料名称类型</option>
            </select>
        </div>
        <div class="layui-inline" style="display: none">
            <input class="layui-input" placeholder="赠送数量" id="gift_number" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="撤回数量" id="back_number" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="back_status">
                <option value="">撤回状态</option>
                <option value="0">未撤回</option>
                <option value="1">已撤回</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="赠送时间" id="gift_time_str" autocomplete="off">
        </div>
        <button class="layui-btn" data-type="reload" onclick="SearchBusinessLegalize()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>

</div>

<table class="layui-hide" id="members_manager_table"></table>

<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/extension_materiel/materiel_gift_record.js"></script>
</body>
</html>
