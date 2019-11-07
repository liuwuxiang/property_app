<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/28
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>平台积分设置</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">
    <link rel="stylesheet" href="${ctx}/css/admin/integral/integral_add.css">
    <link rel="stylesheet" href="${ctx}/css/admin/rose_exchange/rose_exchange.css">



</head>
<body onload="init()">

<div class="top_div">
    <button class="layui-btn" onclick="saveSetInformation()">保存</button>
</div>

<ul class="add_course_ul">
    <li>
        <a class="title_tag">兑换比例：</a>
        <input type="number" name="title" id="exchange_bili" autocomplete="off" class="layui-input">
        <a class="content_tip">个积分兑换1元人民币<span id="exchange_name"></span></a>
    </li>
    <li>
        <a class="title_tag">开放时间：</a>
        <a class="content_tip">每年</a>
        <input type="number" name="title"  id="exchange_start_time_month" autocomplete="off" class="layui-input">
        <a class="content_tip">月 - </a>
        <input type="number" name="title"  id="exchange_start_time_day" autocomplete="off" class="layui-input">
        <a class="content_tip">日 至 </a>
        <input type="number" name="title"  id="exchange_stop_time_month" autocomplete="off" class="layui-input">
        <a class="content_tip">月 - </a>
        <input type="number" name="title"  id="exchange_stop_time_day" autocomplete="off" class="layui-input">
        <a class="content_tip">日</a>
    </li>
    <li>
        <a class="title_tag">最低兑换量：</a>
        <input type="number" name="title" id="min_exchange_number" autocomplete="off" class="layui-input">
        <a class="content_tip">个积分起兑换</a>
    </li>
</ul>

<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script src="${ctx}/js/wx/hui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/admin/integral_management/integral_management.js"></script>
</body>
</html>
