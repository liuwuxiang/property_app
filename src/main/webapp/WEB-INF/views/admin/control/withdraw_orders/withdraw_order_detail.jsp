<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>提现订单信息</title>
	<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="/property_system/css/admin/layui.css">
	<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="/property_system/css/admin/suggestion_feedback_detail.css">
	<script src="/property_system/js/admin/layui.js"></script>
	<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
	<script src="/property_system/js/admin/lay/modules/layer.js"></script>
	<script src="/property_system/js/admin/withdraw_order_detail.js"></script>
</head>
<body>
	<input type="hidden" value="${record_id}" id="record_id">
	<div class="top_div">
		<button class="layui-btn" onclick="passWithdraw()">同意</button>
		<button class="layui-btn" onclick="noPasswithdraw()">拒绝</button>
		<button class="layui-btn" onclick="alreadyPresented()">已提现</button>
	</div>
	<div class="photo_div">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>收款信息</legend>
		</fieldset>
		<ul class="message_ul">
			<li>
				<a>收款姓名：${name}</a>
			</li>
			<li>
				<a>收款银行：${bank_name}</a>
			</li>
			<li>
				<a>收款账号：${bank_id_card_number}</a>
			</li>
		</ul>
	</div>
	<div class="photo_div">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>提现信息</legend>
		</fieldset>
		<ul class="message_ul">
			<li>
				<a>提现金额：${tixianjine}元</a>
			</li>
			<li>
				<a>手续费：${shouxufei}元</a>
			</li>
			<li>
				<a>到账金额：${daozhangamount}元</a>
			</li>
			<li>
				<a>消耗积分：${xiaohaojifen}个</a>
			</li>
			<li>
				<a>申请时间：${submit_date}</a>
			</li>
			<li>
				<a>处理时间：${loan_date}</a>
			</li>
			<li>
				<a>订单号：${order_no}</a>
			</li>
			<li>
				<a>状态：${state}</a>
			</li>
		</ul>
	</div>
</body>
</html>
