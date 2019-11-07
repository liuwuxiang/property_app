<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>玫瑰兑换设置</title>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/rose_exchange/rose_exchange.css">
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/integral/withdraw_setting.js"></script>
</head>
<body onload="initView()">

<div class="top_div">
    <button class="layui-btn" onclick="saveSetInformation()">保存</button>
</div>

<ul class="add_course_ul">
    <li>
        <a class="title_tag">兑换比例：</a>
        <input type="number" name="title" id="exchange_bili" autocomplete="off" class="layui-input">
        <a class="content_tip">个平台积分兑换1<span id="exchange_name"></span></a>
    </li>
    <li>
        <a class="title_tag">兑换时间：</a>
        <a class="content_tip">每月</a>
        <input type="number" name="title" id="start_day" autocomplete="off" class="layui-input"><a class="content_tip">号 - </a>
        <input type="number" name="title" id="end_day" autocomplete="off" class="layui-input"><a class="content_tip">号</a>
    </li>
    <li>
        <a class="title_tag">最低兑换量：</a>
        <input type="number" name="title" id="min_exchange_number" autocomplete="off" class="layui-input">
        <a class="content_tip">个平台积分起兑换</a>
    </li>
</ul>

</body>
</html>
