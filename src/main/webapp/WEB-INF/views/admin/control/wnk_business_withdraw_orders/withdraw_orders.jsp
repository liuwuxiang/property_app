<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>商家提现订单</title>
	<link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/layui.css">
	<link rel="stylesheet" href="${ctx}/js/admin/lay/layui245/css/modules/layer/default/layer.css">
	<link rel="stylesheet" href="${ctx}/css/admin/members.css">
	<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
	<script src="${ctx}/js/admin/lay/layui245/layui.js"></script>
	<script src="${ctx}/js/admin/lay/layui245/lay/modules/layer.js"></script>

	<script src="${ctx}/js/admin/wnk_business_withdraw_orders.js"></script>
</head>
<body onload="initTableAndData()">
	
	<div class="members_manager_top_div">
		<button class="layui-btn layui-btn-sm" onclick="lookWithdrawOrderDetail()">
	  		<i class="layui-icon">&#xe615;</i> 查看
		</button>
		<button class="layui-btn layui-btn-sm" onclick="passWithdraw()">
	  		<i class="layui-icon">&#xe605;</i> 同意
		</button>
		<button class="layui-btn layui-btn-sm" onclick="noPasswithdraw()">
	  		<i class="layui-icon">&#x1006;</i> 拒提
		</button>
		<button class="layui-btn layui-btn-sm" onclick="alreadyPresented()">
			<i class="layui-icon">&#x1005;</i> 已提现
		</button>
	</div>

	<%--搜索--%>
    <div class="members_manager_top_div">
        <form class="layui-form" action="" onsubmit="return false;" >
            <div class="layui-inline">
                <input class="layui-input" placeholder="收款姓名" id="bank_card_name" autocomplete="off">
            </div>
            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="收款银行" id="back_name" autocomplete="off">
            </div>
            <div class="layui-inline" >
                <input class="layui-input" placeholder="收款账号" id="bank_card_number" autocomplete="off">
            </div>
            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="提现金额" id="rmb_count_amount" autocomplete="off">
            </div>
            <div class="layui-inline" >
                <input class="layui-input" placeholder="手续费" id="service_charge_amount" autocomplete="off">
            </div>
            <div class="layui-inline" style="display: none">
                <input class="layui-input" placeholder="到账金额" id="real_payment_rmb_amount" autocomplete="off">
            </div>
            <div class="layui-input-inline">
                <select id="order_state">
                    <option value="">请选择提现状态</option>
                    <option value="0">待审核</option>
                    <option value="1">打款中</option>
                    <option value="2">审核失败</option>
                    <option value="3">打款失败</option>
                    <option value="4">已打款</option>
                </select>
            </div>
            <div class="layui-inline">
                <input type="text" class="layui-input" id="start_time" placeholder="请选择申请时间">
            </div>
            <div class="layui-inline">
                <input type="text" class="layui-input" id="shop_time" placeholder="请选择处理时间">
            </div>
            <button class="layui-btn layui-btn-sm" data-type="reload" onclick="SearchBusiness()">
                <i class="layui-icon">&#xe615;</i>搜索
            </button>
        </form>
    </div>

	<table class="layui-hide" id="members_manager_table">
		
	</table>
</body>
</html>
