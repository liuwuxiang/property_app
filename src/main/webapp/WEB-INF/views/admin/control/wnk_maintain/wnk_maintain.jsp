<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/14
  Time: 14:40
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
    <title>维护员管理-系统维护员管理-万能卡</title>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/wnk_maintain/wnk_maintain.js"></script>
</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addWnkMaintain()">
        <i class="layui-icon">&#xe654;</i> 添加维护员
    </button>

    <button class="layui-btn layui-btn-sm" onclick="editWnkMaintain()">
        <i class="layui-icon">&#xe642;</i> 编辑维护员
    </button>
</div>

<div class="members_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="维护员姓名" id="maintain_name" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="联系方式" id="maintain_phone" autocomplete="off">
        </div>

        <div class="layui-inline">
            <input class="layui-input" placeholder="所属分类" id="maintain_type" autocomplete="off">
        </div>

        <div class="layui-input-inline">
            <select id="status">
                <option value="">请选择启用状态</option>
                <option value="0">启用</option>
                <option value="1">禁用</option>
            </select>
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="create_time" placeholder="请选择创建时间">
        </div>
        <button class="layui-btn" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table"></table>

</body>
</html>
