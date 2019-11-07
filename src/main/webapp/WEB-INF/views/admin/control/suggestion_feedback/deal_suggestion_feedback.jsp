<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>反馈处理</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/deal_suggestion_feedback.js"></script>
</head>
<body onload="initView()">
	<input type="hidden" value="${state}" id="state">
	<input type="hidden" value="${record_id}" id="record_id">
	<div class="top_div">
		<button class="layui-btn" onclick="saveDeal()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>处理状态</a>
			<select class="layui-select-group" id="feed_state_select">
				<option value="0">待处理</option>
				<option value="1">处理中</option>
				<option value="2">完成</option>
			</select>
		</li>
		<li>
			<a>备注</a>
			<textarea class="layui-textarea" id="remark" placeholder="请输入备注信息">${remark}</textarea>
		</li>
	</ul>
</body>
</html>
