<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>推广物料-物料管理</title>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">


    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/extension_materiel/extension_materiel.js"></script>
</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="setExtensionMateriel('1','');">
        <i class="layui-icon">&#xe642;</i> 修改
    </button>

    <button class="layui-btn layui-btn-sm" onclick="giftExtensionMateriel();">
        <i class="layui-icon">&#xe642;</i> 赠送物料
    </button>
    <%--<button class="layui-btn layui-btn-sm" onclick="updateBusinessNoLegalize();">--%>
        <%--<i class="layui-icon">&#xe65e;</i> 套餐管理--%>
    <%--</button>--%>
</div>

<div class="members_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="物料类型" id="name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="limit_times">
                <option value="">请选择限制购买次数</option>
                <option value="0">限制</option>
                <option value="1">不限制</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="限制次数" id="buy_number" autocomplete="off">
        </div>

        <button class="layui-btn" data-type="reload" onclick="Search()"><i class="layui-icon">&#xe615;</i>搜索</button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>
</body>
</html>
