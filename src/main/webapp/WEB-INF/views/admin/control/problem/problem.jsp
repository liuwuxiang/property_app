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
    <title>常见问题-常见问题管理</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/member_level.css">
</head>
<body onload="initTableAndData()">

<div class="member_level_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addProblem()">
        <i class="layui-icon">&#xe654;</i> 新增
    </button>
    <button class="layui-btn layui-btn-sm" onclick="editProblem()">
        <i class="layui-icon">&#xe642;</i> 编辑
    </button>
    <button class="layui-btn layui-btn-sm" onclick="deleteProblem()">
        <i class="layui-icon">&#xe640;</i> 删除
    </button>

</div>

<div class="member_level_manager_top_div">
    <%--搜索--%>
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="问题标题" id="title" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="open_way_str">
                <option value="">请选择打开方式</option>
                <option value="0">富文本</option>
                <option value="1">链接</option>
                <option value="2">视频</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="问题内容" id="content" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="type_str">
                <option value="">请选择类别</option>
                <option value="0">所有</option>
                <option value="1">用户</option>
                <option value="1">商家</option>
            </select>
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="creation_time" placeholder="请选择创建时间">
        </div>
        <button class="layui-btn" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>

<table class="layui-hide" id="member_level_manager_table">

</table>
</body>
<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/problem/problem.js"></script>
</html>