<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/29
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>积分商城-确认订单</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/mui.min.css"/>
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/confirm_order.css"/>
</head>
<body>
<input type="hidden" id="id" value="${id}">
<div class="mui-content">
    <div class="mui-card">
        <!--页眉，放置标题-->
        <div class="mui-card-header">商品详情</div>
        <!--内容区-->
        <div class="mui-card-content">
            <img src="${ctx}/images/wx/integral_mall/huodong.jpg" width="100%" height="120px"/>
        </div>
        <!--页脚，放置补充信息或支持的操作-->
        <div class="mui-card-footer">
            共1件商品,合计扣除<span>999</span>积分
        </div>
    </div>

    <div class="mui-card">
        <ul class="mui-table-view">
            <li class="mui-table-view-cell">订单号:<span id="order_id"></span></li>
            <li class="mui-table-view-cell">订单状态:<span id="status"></span></li>
            <li class="mui-table-view-cell">快递商家:<span id="express_name"></span></li>
            <li class="mui-table-view-cell">快递单号:<span id="express_id"></span></li>
        </ul>
    </div>

    <div class="mui-card">
        <ul class="mui-table-view">
            <li class="mui-table-view-cell">收货人:<span id="username"></span></li>
            <li class="mui-table-view-cell">联系方式:<span id="phone"></span></li>
            <li class="mui-table-view-cell">收货地址:<span id="address"></span></li>
        </ul>
    </div>

</div>

</body>
<script type="text/javascript" src="${ctx}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/mui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/main.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/order_detailed.js"></script>

</html>