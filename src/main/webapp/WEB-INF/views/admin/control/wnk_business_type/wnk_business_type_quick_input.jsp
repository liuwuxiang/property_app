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
    <button class="layui-btn layui-btn-sm" onclick="insertBusinessTypeQuickInput()">
        <i class="layui-icon">&#xe654;</i> 新增
    </button>
    <button class="layui-btn layui-btn-sm" onclick="updateBusinessTypeQuickInput()">
        <i class="layui-icon">&#xe642;</i> 修改
    </button>
</div>

<div class="members_manager_top_div">
    <%--模糊查询搜索--%>
        <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
            <div class="layui-inline">
                <input class="layui-input" placeholder="请输入内容" id="name" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="请输入所属分类" id="business_type_name" autocomplete="off">
            </div>

            <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
        </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table></body>

<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/wnk_business_type/wnk_business_type_quick_input.js"></script>
</html>
