<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>实名认证信息</title>
	<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/real_name_certification_message.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/real_name_certification_message.js"></script>
</head>
<body>
	<input type="hidden" id="record_id" value="${record_id}">
	<div class="top_div">
		<button class="layui-btn" onclick="reviewPass()">审核通过</button>
		<button class="layui-btn" onclick="reviewNoPass()">审核不通过</button>
	</div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>认证基础信息</legend>
	</fieldset>
	<ul class="message_ul">
		<li>
			<a>电话号码：${mobile}</a>
		</li>
		<li>
			<a>真实姓名：${real_name}</a>
		</li>
		<li>
			<a>身份证号：${id_card_number}</a>
		</li>
		<li>
			<a>身份证有效截止日期：${card_effective_deadline}</a>
		</li>
		<li>
			<a>提交日期：${submit_date}</a>
		</li>
		<li>
			<a>当前状态：${state}</a>
		</li>
	</ul>
	
	<div class="photo_div">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 40px;">
		  <legend>身份证正面照片</legend>
		</fieldset>
		<img src="${handheld_identity_card_photo}"/>
	</div>
</body>
</html>
