<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>已下架商户管理-万能卡</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/members.css">

    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/lower_business.js"></script>
</head>
<body onload="initTableAndData()">

<div class="members_manager_top_div">
    <button class="layui-btn layui-btn-sm" onclick="setBusiness()">
        <i class="layui-icon">&#xe642;</i> 修改
    </button>
    <button class="layui-btn layui-btn-sm" onclick="lowerBusinessStore()">
        <i class="layui-icon">&#xe6c6;</i> 上架
    </button>
    <button class="layui-btn layui-btn-sm" onclick="editBusinessLabel()">
        <i class="layui-icon">&#xe642;</i> 编辑特色或服务内容
    </button>
    <button class="layui-btn layui-btn-sm" onclick="chargeBusinessMoneyOrIntegral()">
        <i class="layui-icon">&#xe65e;</i> 充值
    </button>
</div>

<div class="members_manager_top_div">
    <%--已下架商户模糊搜索--%>
    <form class="layui-form" action="" onsubmit="return false;" style="margin-top: 10px">
        <div class="layui-inline">
            <input class="layui-input" placeholder="店铺" id="store_name" autocomplete="off">
        </div>
        <div class="layui-input-inline">
            <select id="wnk_business_type">
                <option value="">请选择分类</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select id="wnk_business_store_state">
                <option value="">请选择状态</option>
                <option value="0">启用</option>
                <option value="1">停用</option>
            </select>
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="余额" id="balance" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input class="layui-input" placeholder="联系电话" id="contact_mobile" autocomplete="off">
        </div>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="join_time" placeholder="请选择加入时间">
        </div>
        <button class="layui-btn layui-btn-sm" data-type="reload" onclick="Search()">
            <i class="layui-icon">&#xe615;</i>搜索
        </button>
    </form>
</div>

<table class="layui-hide" id="members_manager_table">

</table>
</body>
</html>
