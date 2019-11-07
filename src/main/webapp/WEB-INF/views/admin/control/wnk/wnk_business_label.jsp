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
    <title>积分商城-商品新增</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">
    <link rel="stylesheet" href="${ctx}/css/admin/integral/integral_add.css">


    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/wx/toast.js"></script>
    <script src="${ctx}/js/wx/hui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
    <script src="${ctx}/js/wx/integral_mall/main.js"></script>
    <script src="${ctx}/js/admin/wnk_business_label.js"></script>

</head>
<body onload="init();">

<div style="margin: 5% 15%">

    <input type="hidden" id="business_id" value="${business_id}">


    <form id="integral-form" class="layui-form">

        <div class="layui-form-item">
            <label class="layui-form-label">特色内容</label>
            <div class="layui-input-block" id="tese_div">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">服务内容</label>
            <div class="layui-input-block" id="fuwu_div">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" onclick="submitBusinessLabel()">立即提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>

    </form>
</div>

</body>
</html>
