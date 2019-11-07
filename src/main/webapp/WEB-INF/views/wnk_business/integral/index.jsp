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

    <meta name="keywords"    content="">
    <meta name="description" content="">
    <meta name="copyright"   content="" />
    <meta name="viewport"    content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes"          name="apple-mobile-web-app-capable">
    <meta content="black"        name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">

    <link rel="stylesheet" type="text/css"  href="${ctx}/css/wnk_business/common.css"/>
    <link rel="stylesheet" type="text/css"  href="${ctx}/css/wx/hui.css"/>
    <!-- UI插件 -->
    <link rel="stylesheet" href="${ctx}/plus/lib/weui.min.css">
    <link rel="stylesheet" href="${ctx}/plus/css/jquery-weui.css">
    <%--页面专属CSS--%>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wnk_business/integral/index.css">

    <title>积分系统-首页</title>
</head>
<body class="bgw">

<!-- 头部STR -->
<div class="head">
    <h1>积分系统-首页</h1>
    <a href="javascript:void(0);" onclick="history.go(-1);" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->
</div>
<div class="h88"></div>

<%-- 功能列表STR --%>
<div class="memmenu" style="margin-top: 0px;border-width: 1px;">
    <a href="${ctx}/wnk_business/joinIntegralType"  class="item member goodstype">商品分类管理</a>
    <a href="${ctx}/wnk_business/joinIntegralGoods" class="item member goods"    >商品管理</a>
    <a href="${ctx}/wnk_business/joinIntegralOrder" class="item member myorder"  >订单管理</a>
</div>



<script src="${ctx}/js/wnk_business/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/wnk_business/common.js"          ></script>
<script src="${ctx}/js/wnk_business/my.js"              ></script>
<script src="${ctx}/js/wx/hui.js"                       ></script>
<%--UI插件--%>
<script src="${ctx}/plus/lib/fastclick.js"              ></script>
<script src="${ctx}/js/wx/toast.js"                     ></script>
<script src="${ctx}/plus/js/jquery-weui.js"             ></script>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
</body>
</html>
