<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>轮播图管理</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/member_level.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/carousel_img/carousel_img.js"></script>

    <style>
        <%--设置表格显示图片高度 Start--%>
        .laytable-cell-1-img_url_path{
            height: 100%;
            max-width: 100%;
        }
        .layui-table-cell{
            text-align: center;
        }
        <%--设置表格显示图片高度 End--%>

    </style>

</head>
<body onload="initTableAndData()">

<div class="member_level_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addIntegralGoods()">
        <i class="layui-icon">&#xe654;</i> 新增
    </button>

    <button class="layui-btn layui-btn-sm" onclick="setIntegralGoodsEdit()" >
        <i class="layui-icon">&#xe642;</i> 修改
    </button>

</div>

<div class="member_level_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="跳转链接" id="url" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="position">
                <option value="">请选择轮播图位置</option>
                <option value="0">全部</option>
                <option value="1">首页</option>
                <option value="2">附近</option>
                <option value="3">平台积分商城</option>
                <option value="4">商家积分商城</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="is_checked">
                <option value="">请选择启用状态</option>
                <option value="0">启用</option>
                <option value="1">禁用</option>
            </select>
        </div>
        <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
    </form>
</div>

<table class="layui-hide" id="member_level_manager_table">

</table>
</body>

</html>