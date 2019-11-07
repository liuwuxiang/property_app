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
    <title>积分商城-订单详情页</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/mui.min.css"/>
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/confirm_order.css"/>
</head>
<body>
<input type="hidden" id="business_id" value="${business_id}">
<input type="hidden" id="order_id"    value="${order_id}">
<div class="mui-content">
    <div class="mui-card">
        <!--页眉，放置标题-->
        <div class="mui-card-header">商品详情</div>
        <!--内容区-->
        <div class="mui-card-content">
            <img id="goods_img" src="${ctx}/images/wx/integral_mall/huodong.jpg" width="100%" height="100%"/>
        </div>
        <!--页脚，放置补充信息或支持的操作-->
        <div class="mui-card-footer">
            共1件商品,合计扣除<span id="goods_price">999</span>积分
        </div>
    </div>


    <div class="mui-card">
        <ul class="mui-table-view">
            <li class="mui-table-view-cell">联  系  人:<span id="username"></span></li>
            <li class="mui-table-view-cell">联系方式:<span id="phone"></span></li>
            <li class="mui-table-view-cell">订单状态:<span id="status"></span></li>
        </ul>
    </div>

    <div class="mui-card">
        <div class="mui-card-header">订单二维码</div>
        <!--内容区-->
        <div class="mui-card-content">
            <img id="order_qrcode" src="${ctx}/images/wx/integral_mall/huodong.jpg" width="100%" height="100%">
        </div>
    </div>

</div>

</body>
<script type="text/javascript" src="${ctx}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/mui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_wnk/order_detailed.js"></script>

</html>