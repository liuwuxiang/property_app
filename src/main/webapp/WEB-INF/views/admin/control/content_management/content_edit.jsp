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
    <script src="${ctx}/js/admin/content_management/content_edit.js"></script>

</head>
<body onload="initDate();">

<div style="margin: 5% 15%">

    <input type="hidden" id="id" value="${id}">
    <form id="integral-form" class="layui-form">

        <div class="layui-form-item">
            <label class="layui-form-label">内容名称:</label>
            <div class="layui-input-inline">
                <input type="text" id="content_name" required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">内容分类</label>
            <div class="layui-input-block">
                <input type="radio" name="content_type" value="0" title="服务内容" checked>
                <input type="radio" name="content_type" value="1" title="特色内容">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">是否启用</label>
            <div class="layui-input-block">
                <input type="radio" name="content_status" value="0" title="是" checked>
                <input type="radio" name="content_status" value="1" title="否">
            </div>
        </div>


        <input type="hidden" id="content_type" value="${type}">
        <input type="hidden" id="content_id"   value="${content_id}">

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button"  onclick="submitContentLabel();"   >立即提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
