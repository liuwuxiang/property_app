<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>修改会员等级</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/set_credit_rating.js"></script>
</head>
<body onload="initView(${state})">
	<input type="hidden" value="${record_id}" id="record_id">
	<div class="top_div">
		<button class="layui-btn" onclick="saveCreditRating()">保存</button>
	</div>
	
	<ul class="set_member_level_ul">
		<li>
			<a>扣信类型</a>
			<input type="text" name="title" placeholder="请输入扣信类型名称" autocomplete="off" class="layui-input" value="${clasp_type_name}" readonly="readonly">
		</li>
		<li>
			<a>扣信对象</a>
			<input type="text" name="title" placeholder="请输入扣信对象" autocomplete="off" class="layui-input" value="${clasp_object}" readonly="readonly">
		</li>
		<li>
			<a>扣信额度</a>
			<input type="number" name="title" placeholder="请输入扣除多少信用值" value="${clasp_value}" id="clasp_value" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>是否启用</a>
			<form class="layui-form">
				<input type="checkbox" name="like[write]" id="is_qiyong" lay-filter = "is_qiyong" title="启用" checked="checked">
			</form>
		</li>
	</ul>
</body>
</html>
