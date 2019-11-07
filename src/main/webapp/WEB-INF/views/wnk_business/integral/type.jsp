<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/9
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content=""/>
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">

    <link rel="stylesheet" type="text/css" href="${ctx}/css/wnk_business/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/wx/hui.css"/>
    <!-- UI插件 -->
    <link rel="stylesheet" href="${ctx}/plus/lib/weui.min.css">
    <link rel="stylesheet" href="${ctx}/plus/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx}/css/wnk_business/integral/type.css">

    <title>积分系统-商品分类管理</title>
</head>
<body class="bgw">

<!-- 头部STR -->
<div class="head">
    <h1>积分系统-商品分类管理</h1>
    <a href="javascript:void(0);" onclick="history.go(-1);" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->
</div>
<div class="h88"></div>

<div class="page__bd">

    <div id="isNo" class="weui-loadmore weui-loadmore_line" >
        <span class="weui-loadmore__tips">暂无分类</span>
    </div>

    <div id="isYes" >
        <div class="weui-cells__title">已启用的分类</div>
        <div class="memmenu" style="margin-top: 0px;border-width: 1px;" id="yes">
            <!--
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit?type_id=1" class="item member">分类1</a>
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit?type_id=2" class="item member">分类2</a>
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit?type_id=3" class="item member">分类3</a>
    -->
        </div>
        <div class="weui-cells__title">已禁用的分类</div>
        <div class="memmenu" style="margin-top: 0px;border-width: 1px;" id="no">
            <!--
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit?type_id=4" class="item member">分类4</a>
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit?type_id=5" class="item member">分类5</a>
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit?type_id=6" class="item member">分类6</a>
    -->
        </div>
    </div>
</div>


<div class="memmenu" style="margin-top: 0px;border-width: 1px;">
    <a href="${ctx}/wnk_business/joinIntegralTypeEdit" class="weui-btn weui-btn_primary ">新增分类</a>
</div>


<script src="${ctx}/js/wnk_business/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/wnk_business/common.js"></script>
<script src="${ctx}/js/wnk_business/my.js"></script>
<script src="${ctx}/js/wx/hui.js"></script>
<%--UI插件--%>
<script src="${ctx}/plus/lib/fastclick.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script src="${ctx}/plus/js/jquery-weui.js"></script>
<%--自己的js--%>
<script src="${ctx}/js/wnk_business/integral/type.js"></script>
<script>
    $(function () {
        FastClick.attach(document.body);
    });
</script>
</body>
</html>
