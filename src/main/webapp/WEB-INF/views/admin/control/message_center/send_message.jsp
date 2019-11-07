<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>发送消息</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/send_message.js"></script>
</head>
<body onload="initView()">
	<div class="top_div">
		<button class="layui-btn" onclick="sendMessage()">发送</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>标题</a>
			<input type="text" name="title" placeholder="请输入消息标题" id="message_title" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>消息对象</a>
			<select class="layui-select-group" id="message_object_select">
				<option value="0">物业中心</option>
				<option value="1">所有会员</option>
				<option value="2">普通会员</option>
				<option value="3">铂金会员</option>
				<option value="4">钻石会员</option>
			</select>
		</li>
		<li>
			<a>内容</a>
			<textarea class="layui-textarea" id="message_content" placeholder="请输入消息内容"></textarea>
		</li>
	</ul>
</body>
</html>
