<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>统计</title>
	<link rel="stylesheet" href="${ctx}/css/admin/layui.css">
	<link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
	<link rel="stylesheet" href="${ctx}/css/admin/statistics.css">


	<script src="${ctx}/js/wx/jquery-1.9.1.min.js"></script>
	<script src="${ctx}/js/admin/layui.js"></script>
	<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
	<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
	<script src="${ctx}/js/admin/statistics.js"></script>
</head>
<body onload="initDate();">
	<!--用户统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
	  <legend>用户统计</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="user_total_number" style="background: #009688;">16</a>
			<a class="number_tag" style="color: #009688;">总用户数</a>
		</li>
		<li>
			<a class="number_value" id="today_user_number" style="background: #009688;">16</a>
			<a class="number_tag" style="color: #009688;">今日新增用户</a>
		</li>
		<li>
			<a class="number_value" id="silver_user_number" style="background: #009688;">16</a>
			<a class="number_tag" style="color: #009688;">银卡会员</a>
		</li>
		<li>
			<a class="number_value" id="gold_user_number" style="background: #009688;">16</a>
			<a class="number_tag" style="color: #009688;">金卡会员</a>
		</li>
	</ul>

	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
		<legend>商户统计</legend>
	</fieldset>
	<ul class="statistics_ul" id="business_number_ul" >
		<li>
			<a class="number_value" id="business_total_number" style="background: #009688;">16</a>
			<a class="number_tag"  style="color: #009688;">总商户数</a>
		</li>
		<li>
			<a class="number_value"  id="business_today_number" style="background: #009688;">16</a>
			<a class="number_tag" style="color: #009688;">今日新增商户</a>
		</li>
	</ul>
	
	<%--<!--物业统计-->--%>
	<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">--%>
	  <%--<legend>物业统计</legend>--%>
	<%--</fieldset>--%>
	<%--<ul class="statistics_ul">--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #5FB878;">16</a>--%>
			<%--<a class="number_tag" style="color: #5FB878;">物业总数</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #5FB878;">16</a>--%>
			<%--<a class="number_tag" style="color: #5FB878;">今日新增物业</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #5FB878;">16</a>--%>
			<%--<a class="number_tag" style="color: #5FB878;">已停用物业</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #5FB878;">16</a>--%>
			<%--<a class="number_tag" style="color: #5FB878;">已启用物业</a>--%>
		<%--</li>--%>
	<%--</ul>--%>
	
	<!--用户充值统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
	  <legend>充值统计(用户)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="user_balance_sum_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">充值总额</a>
		</li>
		<li>
			<a class="number_value" id="user_balance_today_sum_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">今日充值</a>
		</li>
		<li>
			<a class="number_value" id="user_balance_total_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">充值订单数</a>
		</li>
		<li>
			<a class="number_value" id="user_balance_today_total_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">今日充值订单</a>
		</li>
	</ul>

	<!--用户充值统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
		<legend>充值统计(商家)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="business_balance_sum_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">充值总额</a>
		</li>
		<li>
			<a class="number_value" id="business_balance_today_sum_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">今日充值</a>
		</li>
		<li>
			<a class="number_value" id="business_balance_total_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">充值订单数</a>
		</li>
		<li>
			<a class="number_value" id="business_balance_today_total_number" style="background: #1E9FFF;">16</a>
			<a class="number_tag" style="color: #1E9FFF;">今日充值订单</a>
		</li>
	</ul>
	
	<!--玫瑰兑换统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
	  <legend>玫瑰兑换统计(用户)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="user_rose_sum_number" style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">兑换玫瑰总数</a>
		</li>
		<li>
			<a class="number_value"  id="user_rose_today_sum_number"  style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">今日兑换玫瑰</a>
		</li>
		<li>
			<a class="number_value" id="user_rose_total_number" style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">兑换玫瑰订单数</a>
		</li>
		<li>
			<a class="number_value" id="user_rose_today_total_number" style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">今日兑换玫瑰订单数</a>
		</li>
	</ul>

	<!--玫瑰兑换统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
		<legend>玫瑰兑换统计(商家)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="business_rose_sum_number" style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">兑换玫瑰总数</a>
		</li>
		<li>
			<a class="number_value"  id="business_rose_today_sum_number"  style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">今日兑换玫瑰</a>
		</li>
		<li>
			<a class="number_value" id="business_rose_total_number" style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">兑换玫瑰订单数</a>
		</li>
		<li>
			<a class="number_value" id="business_rose_today_total_number" style="background: #FFB800;">16</a>
			<a class="number_tag" style="color: #FFB800;">今日兑换玫瑰订单数</a>
		</li>
	</ul>
	
	<!--提现统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
	  <legend>提现统计(用户)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="user_withdraw_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">提现总金额</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_today_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">今日提现金额</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_order_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">提现总单数</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_today_order_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">今日提现单数</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_disposedOf_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">已处理金额</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_untreated_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">待处理金额</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_disposedOf_order_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">已处理单数</a>
		</li>
		<li>
			<a class="number_value" id="user_withdraw_untreated_order_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">待处理单数</a>
		</li>
	</ul>

	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
		<legend>提现统计(商家)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="business_withdraw_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">提现总金额</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_today_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">今日提现金额</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_order_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">提现总单数</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_today_order_sum_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">今日提现单数</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_disposedOf_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">已处理金额</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_untreated_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">待处理金额</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_disposedOf_order_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">已处理单数</a>
		</li>
		<li>
			<a class="number_value" id="business_withdraw_untreated_order_number" style="background: #2F4056;">16</a>
			<a class="number_tag" style="color: #2F4056;">待处理单数</a>
		</li>
	</ul>
	
	<%--<!--业主认证统计-->--%>
	<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">--%>
	  <%--<legend>业主认证统计</legend>--%>
	<%--</fieldset>--%>
	<%--<ul class="statistics_ul">--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #FF5722;">16</a>--%>
			<%--<a class="number_tag" style="color: #FF5722;">认证总记录</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #FF5722;">16</a>--%>
			<%--<a class="number_tag" style="color: #FF5722;">已处理记录</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #FF5722;">16</a>--%>
			<%--<a class="number_tag" style="color: #FF5722;">待处理记录</a>--%>
		<%--</li>--%>
	<%--</ul>--%>
	<%----%>
	<%--<!--车主认证统计-->--%>
	<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">--%>
	  <%--<legend>车主认证统计</legend>--%>
	<%--</fieldset>--%>
	<%--<ul class="statistics_ul">--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #48D1CC;">16</a>--%>
			<%--<a class="number_tag" style="color: #48D1CC;">认证总记录</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #48D1CC;">16</a>--%>
			<%--<a class="number_tag" style="color: #48D1CC;">已处理记录</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #48D1CC;">16</a>--%>
			<%--<a class="number_tag" style="color: #48D1CC;">待处理记录</a>--%>
		<%--</li>--%>
	<%--</ul>--%>
	<%----%>
	<%--<!--实名认证统计-->--%>
	<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">--%>
	  <%--<legend>实名认证统计</legend>--%>
	<%--</fieldset>--%>
	<%--<ul class="statistics_ul">--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #00FA9A;">16</a>--%>
			<%--<a class="number_tag" style="color: #00FA9A;">认证总记录</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #00FA9A;">16</a>--%>
			<%--<a class="number_tag" style="color: #00FA9A;">已处理记录</a>--%>
		<%--</li>--%>
		<%--<li>--%>
			<%--<a class="number_value" style="background: #00FA9A;">16</a>--%>
			<%--<a class="number_tag" style="color: #00FA9A;">待处理记录</a>--%>
		<%--</li>--%>
	<%--</ul>--%>
	
	<!--建议反馈统计-->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
	  <legend>建议反馈统计(用户)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="total_number_user" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">反馈总数</a>
		</li>
		<li>
			<a class="number_value" id="wait_number_user" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">待处理反馈</a>
		</li>
		<li>
			<a class="number_value" id="processing_number_user" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">处理中反馈</a>
		</li>
		<li>
			<a class="number_value" id="complete_number_user" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">已处理记录</a>
		</li>
	</ul>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
		<legend>建议反馈统计(商家)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="total_number_business" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">反馈总数</a>
		</li>
		<li>
			<a class="number_value" id="wait_number_business" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">待处理反馈</a>
		</li>
		<li>
			<a class="number_value" id="processing_number_business" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">处理中反馈</a>
		</li>
		<li>
			<a class="number_value" id="complete_number_business" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">已处理记录</a>
		</li>
	</ul>

	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
		<legend>商品订单记录(商家)</legend>
	</fieldset>
	<ul class="statistics_ul">
		<li>
			<a class="number_value" id="wnk_order_totle_number" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">订单总数</a>
		</li>
		<li>
			<a class="number_value" id="wnk_order_complete_number" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">已完成订单</a>
		</li>
		<li>
			<a class="number_value" id="wnk_order_Tobeused_number" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">待支付订单</a>
		</li>
		<li>
			<a class="number_value" id="wnk_order_Tobepaid_number" style="background: #6495ED;">16</a>
			<a class="number_tag" style="color: #6495ED;">待使用订单</a>
		</li>
	</ul>
</body>
</html>
