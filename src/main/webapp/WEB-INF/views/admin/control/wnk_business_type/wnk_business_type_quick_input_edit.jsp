<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>快速输入</title>
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">


</head>
<body onload="initData()">
<form class="layui-form" action="" onsubmit="return false;" style="margin-top: 20px;margin-left: 15%">
    <input type="hidden" id="quickId" value="${quickId}">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">输入内容</label>
            <div class="layui-input-inline">
                <input type="text" id="name"  placeholder="请输入快速输入内容" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商家分类</label>
        <div class="layui-input-inline">
            <select id="business_type" lay-filter="aihao">
                <%--<option value="">请选择充值类型</option>--%>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="button" onclick="recharge()">提交</button>
        </div>
    </div>
</form>

<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
<script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/wnk_business_type/wnk_business_type_quick_input_edit.js"></script>
</body>
</html>
