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
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/wnk_business_tag_manager.js"></script>
</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="addBusinessOneTag()">
        <i class="layui-icon">&#xe654;</i> 添加
    </button>
    <button class="layui-btn layui-btn-sm" onclick="setBusinessTag()">
        <i class="layui-icon">&#xe642;</i> 修改
    </button>
    <button class="layui-btn layui-btn-sm" onclick="deleteBusinessTag()">
        <i class="layui-icon">&#xe640;</i> 删除
    </button>
    <button class="layui-btn layui-btn-sm" onclick="oneTagSort()">
        <i class="layui-icon">&#xe705;</i> 一级标签排序
    </button>
    <button class="layui-btn layui-btn-sm" onclick="twoTagSort()">
        <i class="layui-icon">&#xe705;</i> 二级标签排序
    </button>
    <button class="layui-btn layui-btn-sm" onclick="twoTagManager()">
        <i class="layui-icon">&#xe705;</i> 二级标签管理
    </button>
</div>

<div class="members_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="一级标签" id="name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="state_str">
                <option value="">请选择状态</option>
                <option value="0">停用</option>
                <option value="1">启用</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="关联分类" id="relation_type_name" autocomplete="off">
        </div>

        <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>

</body>
</html>
