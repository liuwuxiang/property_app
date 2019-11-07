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

    <form id="order-from">
        <input type="hidden" name="userId">
        <input type="hidden" name="goodsId">
        <input type="hidden" name="count">
        <input type="hidden" name="price">
        <div class="mui-card">
            <!--页眉，放置标题-->
            <div class="mui-card-header">收货地址</div>
            <!--内容区-->
            <div class="mui-card-content">
                <form class="mui-input-group">
                    <div class="mui-input-row">
                        <label>收件人姓名</label>
                        <input name="username" type="text" class="mui-input-clear" placeholder="请输入收件人姓名">
                    </div>
                    <div class="mui-input-row">
                        <label>收件人电话</label>
                        <input name="phone" type="number" class="mui-input-clea" placeholder="请输入收件人电话">
                    </div>
                    <div class="mui-input-row">
                        <label>收 货 地 址</label>
                        <input name="address" type="text" class="mui-input-clea" placeholder="请输入收件人地址">
                    </div>
                </form>
            </div>
        </div>
    </form>

    <div>
        <div class="js-pay">确认订单</div>
    </div>


</div>

</body>
<script type="text/javascript" src="${ctx}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/mui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/main.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/confirm_order.js"></script>

</html>
