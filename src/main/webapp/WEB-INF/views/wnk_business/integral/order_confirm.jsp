<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/15
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="keywords"        content="">
    <meta name="description"     content="">
    <meta name="copyright"       content="" />
    <meta name="viewport"        content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes"          name="apple-mobile-web-app-capable">
    <meta content="black"        name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">

    <link rel="stylesheet" type="text/css"  href="${ctx}/css/wnk_business/common.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx}/css/wx/hui.css"/>
    <!-- UI插件 -->
    <link rel="stylesheet" href="${ctx}/plus/lib/weui.min.css">
    <link rel="stylesheet" href="${ctx}/plus/css/jquery-weui.css">

    <title>积分系统-首页</title>
</head>

<body class="bgw" onload="webLoad()">

<input type="hidden" id="user_id" value="${user_id}">
<input type="hidden" id="order_id" value="${order_id}">

<!-- 头部STR -->
<div class="head">
    <h1>积分系统-订单确认</h1>
    <a href="javascript:void(0);" onclick="history.go(-1);" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->
    </div>
</div>
<div class="h160"></div>

<div class="weui-form-preview">
    <div class="weui-form-preview__hd">
        <label class="weui-form-preview__label">商品名称</label>
        <em id="goods_name" class="weui-form-preview__value"><!--电动打蛋机--></em>
    </div>
    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">联系人</label>
            <span id="username"  class="weui-form-preview__value"><!--道一--></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">联系电话</label>
            <span id="phone" class="weui-form-preview__value"><!--15925012437--></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">订单号</label>
            <span id="orderId" class="weui-form-preview__value"><!--1539424394757--></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">商品价格</label>
            <span id="price" class="weui-form-preview__value"><!--1539424394757--></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">下单时间</label>
            <span id="creation_time" class="weui-form-preview__value"><!--2018年10月15日 11:23:51--></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">完成时间</label>
            <span id="end_time" class="weui-form-preview__value"><!--2018年10月15日 11:23:51--></span>
        </div>
        <div class="weui-form-preview__item">
            <label class="weui-form-preview__label">订单状态</label>
            <span id="status" class="weui-form-preview__value"><!--已支付--></span>
        </div>
    </div>
    <div class="weui-form-preview__ft">
        <button onclick="integralOrderUse()" type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">订单完成</button>
    </div>
</div>



<div class="page__bd" id="content">


</div>

<script src="${ctx}/js/wnk_business/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/wnk_business/common.js"          ></script>
<script src="${ctx}/js/wnk_business/my.js"              ></script>
<script src="${ctx}/js/wx/hui.js"                       ></script>
<%--UI插件--%>
<script src="${ctx}/plus/lib/fastclick.js"              ></script>
<script src="${ctx}/js/wx/toast.js"                     ></script>
<script src="${ctx}/plus/js/jquery-weui.js"             ></script>
<%--自己的js--%>
<script src="${ctx}/js/wnk_business/integral/order_confirm.js"></script>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
</body>
</html>
