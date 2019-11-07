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
    <link rel="stylesheet" href="${ctx}/js/wangEditor.css">


</head>
<body onload="init()">

<div style="margin: 5% 15%">

    <input type="hidden" id="about_type" value="${type}">

    <div class="layui-form-item">
        <div class="layui-input-inline">
            <h1 id="about_title">法律声明</h1>
        </div>
    </div>

    <div id="editor"></div>

    <button class="layui-btn" type="button" onclick="about_submit()" style="margin-top: 10px">立即提交</button>
</div>

<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script src="${ctx}/js/wx/hui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<%--富文本编辑器--%>
<script src="${ctx}/js/wangEditor.min.js"></script>
<script src="${ctx}/js/admin/about/about.js"></script>
</body>
</html>
