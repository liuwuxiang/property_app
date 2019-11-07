<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>反馈详情</title>
	<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/suggestion_feedback_detail.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/suggestion_feedback_detail.js"></script>
</head>
<body onload="initData()">
	<input type="hidden" value="${record_id}" id="record_id">
	<div class="top_div">
		<button class="layui-btn" onclick="dealFeedback()">处理</button>
	</div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>反馈基础信息</legend>
	</fieldset>
	<ul class="message_ul">
		<li>
			<a>联系人：${name}</a>
		</li>
		<li>
			<a>联系电话：${mobile}</a>
		</li>
		<li>
			<a>反馈时间：${submit_date}</a>
		</li>
		<li>
			<a>状态：${state}</a>
		</li>
	</ul>
	<ul class="message_ul">
		<li>
			<a>备注：${remark}</a>
		</li>
	</ul>
	<div class="photo_div">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>反馈内容</legend>
		</fieldset>
		<ul class="message_ul">
			<li>
				<a>${content}</a>
			</li>
		</ul>
	</div>
	<div class="photo_div">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 40px;">
		  <legend>反馈照片</legend>
		</fieldset>
		<ul class="photo_ul" id="photo_ul">
			<%--<li>--%>
				<%--<img src="/property_system/images/admin/zp.png"/>--%>
			<%--</li>--%>
		</ul>
		
	</div>
</body>
</html>
