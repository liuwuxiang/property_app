<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>提现设置</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/withdraw_setting.css">
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/withdraw_setting.js"></script>
</head>
<body onload="initView()">

<input type="hidden" id="withdraw_type" value="${type}">

<div class="top_div">
    <button class="layui-btn" onclick="UpdateWithdraw()">保存</button>
</div>

<ul class="set_member_level_ul">
    <li>
        <a>提现费率(%)</a>
        <input id="service_charge_proportion" type="text"  placeholder="请输入提现手续费,单位为百分比(如:20)" class="layui-input">
    </li>
    <li>
        <a>提现比例</a>
        <input id="withdraw_proportion" type="text"  placeholder="请输入提现比例,多少兑换一元人民币" class="layui-input">
    </li>

    <li id="min_number_li" style="display: none;">
        <a>最低提现数量</a>
        <input id="min_number" type="text"  placeholder="最低提现数量" class="layui-input">
    </li>

    <li id="get_time_li" style="display: none;">
        <a>到账时间(几个工作日内)</a>
        <input id="get_time" type="text"  placeholder="多少个工作日内到账" class="layui-input">
    </li>

    <li>
        <a>是否限制提现时间段</a>
        <form class="layui-form">
            <input type="checkbox" id="is_any_time" title="不限"  lay-filter="is_any_time">
        </form>
    </li>

    <li id="allow_withdraw_time_li_start">
        <a>每月允许提现时间段</a>
        <div class="time_div">
            <a>开始时间:</a>
            <input type="text"  class="layui-input"  id="withdraw_start_time">
            <a>日&nbsp;至&nbsp;</a>
            <input type="text"  class="layui-input"  id="withdraw_end_time">
            <a>日</a>
        </div>
    </li>
    <%--<li id="allow_withdraw_time_li_end">--%>
        <%--<div class="time_div">--%>
            <%--<a>结束时间:</a>--%>
            <%--<input type="text"  class="layui-input"  id="withdraw_end_time_month">--%>
            <%--<a>月&nbsp;</a>--%>
            <%--<input type="text"  class="layui-input"  id="withdraw_end_time_day">--%>
            <%--<a>日</a>--%>
        <%--</div>--%>
    <%--</li>--%>
</ul>

</body>
</html>
