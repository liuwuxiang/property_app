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
    <title>特色内容管理</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/member_level.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/content_management/tese_content_management.js"></script>
</head>
<body onload="initTableAndData()">

<div class="member_level_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addTeseContent()">
        <i class="layui-icon">&#xe654;</i> 新增
    </button>

    <button class="layui-btn layui-btn-sm" onclick="updateTeseContent()" >
        <i class="layui-icon">&#xe642;</i> 修改
    </button>
</div>

<div class="member_level_manager_top_div">
    <%--模糊搜索--%>
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="特色内容名称" id="name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="state">
                <option value="">请选择启用状态</option>
                <option value="0">启用</option>
                <option value="1">禁用</option>
            </select>
        </div>
        <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>
</body>

</html>