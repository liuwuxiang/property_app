<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户认证-待审核认证</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">

    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/legalize/legalize.js"></script>

    <style>
        .show_img {
            width: 100%;
            height: auto;
        }

        .show_img > img {
            width: 100%;
            height: auto;
        }
    </style>

</head>
<body onload="initTableAndData()">
<%--操作--%>
<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="updateBusinessLegalize('1','');">
        <i class="layui-icon">&#xe642;</i> 认证通过
    </button>
    <button class="layui-btn layui-btn-sm" onclick="updateBusinessNoLegalize();">
        <i class="layui-icon">&#x1006;</i> 认证拒绝
    </button>
    <button class="layui-btn layui-btn-sm" onclick="showBusinessLegalizeInfo()">
        <i class="layui-icon">&#xe615;</i> 查看
    </button>
</div>

<%--搜索--%>
<div class="members_manager_top_div">
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="商家ID" id="business_id" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="店铺" id="store_name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select  id="wnk_business_type">
                <option value="">请选择店铺分类</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="法人联系电话" id="phone" autocomplete="off">
        </div>

        <div class="layui-input-inline">
            <select id="legalize_status">
                <option value="">请选择审核状态</option>
                <option value="-1">未上传认证信息</option>
                <option value="0">未认证</option>
                <option value="1">认证通过</option>
                <option value="2">审核拒绝</option>
            </select>
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="start_time" placeholder="请选择审核时间">
        </div>
        <button class="layui-btn" data-type="reload" onclick="SearchBusinessLegalize()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table" lay-filter="test">

</table>

</body>
</html>
