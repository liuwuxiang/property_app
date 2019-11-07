<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/9
  Time: 15:32
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

<%--初始化的时候模拟点击全部订单按钮. 完成初始化载入--%>
<body class="bgw" onload="optionClick(0)" >

<!-- 头部STR -->
<div class="head">
    <h1>积分系统-订单管理</h1>
    <a href="javascript:void(0);" onclick="history.go(-1);" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->
    <div class="pubtabmenu">
        <div class="wrap">
            <a class="item sel" id="order_all" onclick="optionClick(0)">全部订单</a>
            <a class="item"     id="order_no"  onclick="optionClick(1)">未完成</a>
            <a class="item"     id="order_yes" onclick="optionClick(2)">已完成</a>
        </div>
    </div>
</div>
<div class="h160"></div>




<div class="page__bd" id="content">

    <div class="weui-loadmore weui-loadmore_line">
        <span class="weui-loadmore__tips">暂无数据</span>
    </div>

    <%--<div class="weui-form-preview">--%>
        <%--<div class="weui-form-preview__hd">--%>
            <%--<div class="weui-form-preview__item">--%>
                <%--<label class="weui-form-preview__label">付款金额</label>--%>
                <%--<em class="weui-form-preview__value">¥2400.00</em>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="weui-form-preview__bd">--%>
            <%--<div class="weui-form-preview__item">--%>
                <%--<label class="weui-form-preview__label">商品名称</label>--%>
                <%--<span class="weui-form-preview__value">电动打蛋机</span>--%>
            <%--</div>--%>
            <%--<div class="weui-form-preview__item">--%>
                <%--<label class="weui-form-preview__label">买家名称</label>--%>
                <%--<span class="weui-form-preview__value">名字名字名字</span>--%>
            <%--</div>--%>
            <%--<div class="weui-form-preview__item">--%>
                <%--<label class="weui-form-preview__label">商品状态</label>--%>
                <%--<span class="weui-form-preview__value">已支付</span>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<br>--%>

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
<script src="${ctx}/js/wnk_business/integral/order.js"></script>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
</body>
</html>
