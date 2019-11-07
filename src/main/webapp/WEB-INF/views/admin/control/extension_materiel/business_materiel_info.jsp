<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>推广物料-商家剩余物料情况</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">


</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
        <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
            <div class="layui-inline">
                <input class="layui-input" placeholder="商家名称" id="store_name" autocomplete="off">
            </div>
            <div class="layui-input-inline" style="display: none">
                <select id="business_lower_str">
                    <option value="">是否启用</option>
                    <option value="0">启用</option>
                    <option value="1">停用</option>
                </select>
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
<script src="${ctx}/js/admin/extension_materiel/business_materiel_info.js"></script>
</body>
</html>
