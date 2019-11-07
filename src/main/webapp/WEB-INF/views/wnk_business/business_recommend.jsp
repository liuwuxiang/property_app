<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/18
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>商家推荐</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/download/g.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/download/download.css">
    <script type="text/javascript" src="${ctx}/js/wnk_business/business_recommend.js"></script>
</head>

<body class="appdownload-body">

<input type="hidden" value="${mobile}" id="mobile"/>

<div class="mb-stars"></div>
<div class="page-bg-btm mobile"></div>

<div class="appdownload-wrap">
    <div class="appdownload-logo">
        <img src="${ctx}/images/dowload/logo.jpg" style="border-radius: 50% !important;" width="80" height="80">
        <p class="appname">辰蓝科技</p>
    </div>
    <!--提示：页面不存在-->
    <!--提示：暂不支持的机型-->
    <div class="appdownload-btns">
        <a class="btn item-ios" target="_blank" onclick="register(1)">
            <span class="ico ico-ios"></span>
            <span class="txt">用户注册</span>
        </a>
        <a class="btn item-ios"  target="_blank" onclick="register(0)">
            <span class="ico ico-android"></span>
            <span class="txt">商家注册</span>
        </a>
    </div>
    <p class="appdownload-copyright">Copyright © All Rights Reserved</p>
</div>
<script type="text/javascript" src="${ctx}/js/wx/jquery-1.9.1.min.js"></script>
</body>
</html>