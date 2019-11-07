<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/29
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
    <meta charset="UTF-8">
    <title>积分商城-我的积分</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link href="${ctx}/css/wx/integral_mall/mui.min.css" rel="stylesheet" />
    <link href="${ctx}/css/wx/integral_mall/count.css"   rel="stylesheet"  />
</head>

<body>

<div class="header mui-col-sm-12 mui-col-xs-12">
    <div class="count-box">
        <div class="inner mui-col-sm-12 mui-col-xs-12">
            <span class="title ">积分</span>
            <span class="num"><!-- 积分数量 --></span>
        </div>
    </div>
    <div class="tool-bar mui-col-sm-12 mui-col-xs-12">
        <a href="${ctx}/wx/v1.0.0/myIntegral" class="mui-btn">
            <span class="mui-icon mui-icon mui-icon-bars" style="color: #fc9153;"></span>
            积分商城
        </a>
        <a href="${ctx}/wx/v1.0.0/joinMyIntegralOrder" class="mui-btn">
            <span class="mui-icon mui-icon mui-icon-bars" style="color: #fc9153;"></span>
            我的订单
        </a>
        <a href="${ctx}/wx/v1.0.0/joinMyIntegralCashOrder?goods_id=${id}" class="mui-btn">
            <span class="mui-icon mui-icon mui-icon-bars" style="color: #fc9153;"></span>
            积分提现
        </a>
    </div>

    <a class="rule" href="javascript:;">
        <span class="mui-icon mui-icon mui-icon-help" style="font-size: 1rem;"></span>
        积分规则
    </a>

    <div class="fixed-title mui-col-sm-12 mui-col-xs-12">
        <span class="txt">积分明细</span>
    </div>
</div>

<div class="main mui-col-sm-12 mui-col-xs-12">

    <div class="count-list mui-col-sm-12 mui-col-xs-12">
        <ul id="detail-list" class="count-list-ul mui-col-sm-12 mui-col-xs-12">
            <!--
            <li class="count-item mui-col-sm-12 mui-col-xs-12">

                <div class="mui-col-sm-6 mui-col-xs-6">
                    <p class="name ">成功支付订单</p>
                    <p class="meta">2018-09-25 13:55:58</p>
                </div>

                <span class="nums mui-col-sm-6 mui-col-xs-6">+8</span>
            </li>

            <li class="count-item mui-col-sm-12 mui-col-xs-12">
                <div class="mui-col-sm-6 mui-col-xs-6">
                    <p class="name ">成功支付订单</p>
                    <p class="meta">2018-09-25 13:55:58</p>
                </div>

                <span class="nums mui-col-sm-6 mui-col-xs-6">+8</span>
            </li>
            -->
        </ul>
    </div>
</div>

<script src="${ctx}/js/wx/integral_mall/mui.min.js"></script>
<script src="${ctx}/js/wx/jquery.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/wx/integral_mall/count.js"></script>
<script type="text/javascript">
    mui.init()
</script>
</body>

</html>