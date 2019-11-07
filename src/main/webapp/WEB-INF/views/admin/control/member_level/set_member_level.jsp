<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改会员等级</title>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">

    <link rel="stylesheet" href="${ctx}/js/wangEditor.css">
    <%--富文本编辑器--%>
    <script src="${ctx}/js/wangEditor.min.js"></script>

    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/set_member_level.js"></script>
</head>
<body onload="initView()">
<input type="hidden" id="level_id" value="${level_id}">
<div class="top_div">
    <button class="layui-btn" onclick="setMemberLevelInformation()">保存</button>
</div>

<ul class="set_member_level_ul">
    <li>
        <a>等级名称</a>
        <input type="text" name="title" placeholder="请输入等级名称" autocomplete="off" class="layui-input"
               value="${level_name}" readonly="readonly">
    </li>
    <li>
        <a>升级条件(使用多少积分兑换银币)</a>
        <input type="number" name="title" placeholder="请输入使用多少积分兑换银币可升级" id="recharge_consumption_integral"
               value="${recharge_consumption_integral}" autocomplete="off" class="layui-input">
    </li>
    <li>
        <a>等级介绍</a>
        <%--<textarea id="course_detail" style="display: none;">${brief_introduction}</textarea>--%>
        <div id="course_detail">${brief_introduction}</div>
    </li>
</ul>

</body>
</html>
