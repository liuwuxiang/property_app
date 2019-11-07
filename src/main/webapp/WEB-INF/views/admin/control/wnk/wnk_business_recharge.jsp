<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>会员充值</title>
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">

    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/wnk/wnk_business_recharge.js"></script>

</head>
<body onload="initData()">
<form class="layui-form" action="" onsubmit="return false;" style="margin-top: 20px;margin-left: 15%">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商户手机号</label>
            <div class="layui-input-inline">
                <input type="tel" id="mobile" name="title"  placeholder="请输入商户手机号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">充值数量</label>
            <div class="layui-input-inline">
                <input type="text" id="amount" name="title" placeholder="请输入充值数量" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">充值类型</label>
        <div class="layui-input-inline">
            <select id="type" lay-filter="aihao">
                <option value="">请选择充值类型</option>
                <option value="0" selected="selected">余额充值</option>
                <option value="1">等级积分充值</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="button" onclick="recharge()">充值</button>
        </div>
    </div>
</form>

</body>
</html>
