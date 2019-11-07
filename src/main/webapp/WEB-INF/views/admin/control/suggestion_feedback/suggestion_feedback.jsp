<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>建议反馈</title>
    <script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
	<link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
	<link rel="stylesheet" href="${ctx}/css/admin/members.css">

	<script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
	<script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>
	<script src="${ctx}/js/admin/suggestion_feedback.js"></script>

</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="lookDetail()">
	  		<i class="layui-icon">&#xe615;</i> 查看
		</button>
		<button class="layui-btn layui-btn-sm" onclick="dealFeedback()">
	  		<i class="layui-icon">&#xe642;</i> 处理
		</button>
	</div>

    <div class="members_manager_top_div">
        <%--搜索--%>
        <form class="layui-form" action="" onsubmit="return false;">

            <div class="layui-inline">
                <input class="layui-input" placeholder="联系人" id="user_name" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="联系电话" id="user_mobile" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="反馈内容" id="content" autocomplete="off">
            </div>

            <div class="layui-inline">
                <input type="text" class="layui-input" id="feedback_date" placeholder="请选择反馈时间">
            </div>

            <div class="layui-inline">
                <select id="state">
                    <option value="">请选择处理状态</option>
                    <option value="0">待处理</option>
                    <option value="1">处理中</option>
                    <option value="2">完成</option>
                </select>
            </div>

            <div class="layui-inline">
                <input class="layui-input" placeholder="备注" id="remark" autocomplete="off">
            </div>

            <button class="layui-btn layui-btn-sm "  data-type="reload" onclick="Search()">
                <i class="layui-icon">&#xe615;</i>搜索
            </button>

        </form>
    </div>
	
	<table class="layui-hide" id="members_manager_table" lay-filter="test"></table>
</body>
</html>
