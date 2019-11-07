<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>新增/修改物业</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<link rel="stylesheet" href="/property_system/css/admin/set_member_level.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/add_property.js"></script>
</head>
<body onload="initView(${type})">
	<div class="top_div">
		<button class="layui-btn" onclick="saveProperty()">保存</button>
	</div>
	<input type="hidden" value="${record_id}" id="record_id">
	<input type="hidden" value="${proviince_id}" id="proviince_id">
	<input type="hidden" value="${city_id}" id="city_id">
	<ul class="set_member_level_ul">
		<li id="account_li">
			<a>物业主账号</a>
			<input type="text" name="title" placeholder="请输入物业主账号" id="account" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>所属省份</a>
			<select class="layui-select-group" id="province_select" lay-filter="province_select" onchange="provinceChange()">
				<%--<option value="0">1</option>--%>
			</select>
		</li>
		<li>
			<a>所属城市</a>
			<select class="layui-select-group" id="city_choose_select" lay-filter="city_choose_select" onchange="cityChange()">
				<%--<option value="0">1</option>--%>
			</select>
		</li>
		<li>
			<a>物业名称</a>
			<input type="text" name="title" placeholder="请输入物业名称" id="residentials_name" value="${residentials_name}" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>物业地址</a>
			<input type="text" name="title" placeholder="请输入物业详细地址" id="address" value="${address}" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>联系人</a>
			<input type="text" name="title" placeholder="请输入物业联系人" id="contact_name" value="${contact_name}" autocomplete="off" class="layui-input">
		</li>
		<li>
			<a>联系电话</a>
			<input type="text" name="title" placeholder="请输入物业联系电话" id="contact_mobile" value="${contact_mobile}" autocomplete="off" class="layui-input">
		</li>
	</ul>
</body>
</html>
