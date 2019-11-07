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
    <link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">

    <script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>

    <script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>

    <%--<script src="/property_system/js/admin/layui.js"></script>--%>
    <%--<script src="/property_system/js/admin/lay/modules/jquery.js"></script>--%>
    <%--<script src="/property_system/js/admin/lay/modules/layer.js"></script>--%>
    <script src="/property_system/js/admin/member_recharge.js"></script>

</head>
<body onload="initData()">
<form class="layui-form" action="" onsubmit="return false;" style="margin-top: 20px;margin-left: 15%">
    <!--
    <div class="layui-input-inline">
        <a>会员手机号</a>
        <input type="text" name="title" placeholder="请输入会员手机号" id="mobile" autocomplete="off" class="layui-input">
    </div>

    <div class="layui-input-inline">
        <a>积分充值数量</a>
        <input type="number" name="title" placeholder="请输入充值多少通用积分" id="amount" autocomplete="off"
               class="layui-input">
    </div>
    -->

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">会员手机号</label>
            <div class="layui-input-inline">
                <input type="tel" id="mobile" name="title"  placeholder="请输入会员手机号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">积分充值数量</label>
            <div class="layui-input-inline">
                <input type="text" id="amount" name="title" placeholder="请输入充值多少积分" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">积分类型</label>
        <div class="layui-input-inline">
            <select id="type" lay-filter="aihao">
                <option value="">请选择积分类型</option>
                <option value="0" selected="selected">通用积分</option>
                <option value="1">平台积分</option>
            </select>
        </div>
    </div>

    <%--<a>积分类型</a>--%>
    <%--<div class="layui-input-inline">--%>
        <%--<select id="pay_state">--%>
            <%--<option value="">请选择积分类型</option>--%>
            <%--<option value="0">通用积分</option>--%>
            <%--<option value="1">平台积分</option>--%>
        <%--</select>--%>
    <%--</div>--%>

    <%--<div class="top_div">--%>
        <%--<button class="layui-btn" onclick="recharge()">充值</button>--%>
    <%--</div>--%>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="button" onclick="recharge()">充值</button>
        </div>
    </div>
</form>

</body>
</html>
