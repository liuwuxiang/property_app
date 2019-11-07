<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>万能卡商家开卡协议</title>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/member_level.css">
</head>
<body onload="initTableAndData()">

<div class="member_level_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="insertBusinessOpenCardProtocol()">
        <i class="layui-icon">&#xe654;</i> 新增
    </button>

    <button class="layui-btn layui-btn-sm" onclick="updateBusinessOpenCardProtocol()" >
        <i class="layui-icon">&#xe642;</i> 修改
    </button>
</div>

<div class="member_level_manager_top_div">
    <%--搜索--%>
    <form class="layui-form" action="" onsubmit="return false;">

        <div class="layui-inline">
            <input class="layui-input" placeholder="商家名称" id="business_name" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="协议内容" id="content" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="is_checked_str">
                <option value="">请选择启用状态</option>
                <option value="0">启用</option>
                <option value="1">禁用</option>
            </select>
        </div>

        <button class="layui-btn layui-btn-sm" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>
<table class="layui-hide" id="member_level_manager_table"></table>


<script src="${ctx}/js/admin/lay/layui245/layui.all.js"></script>
<script src="${ctx}/js/admin/wnk/wnk_business_open_card_protocol.js"></script>
</body>
</html>