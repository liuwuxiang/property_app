<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/29
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
    <meta charset="UTF-8">
    <title>积分商城-我的订单</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/mui.min.css" />
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/record.css" />
</head>

<body>

<input type="hidden" id="business_id" value="${business_id}">
<div class="mui-content">
    <div class="mui-col-sm-12 mui-col-xs-12">

        <div class="record mui-col-sm-12 mui-col-xs-12">
            <ul id="content">
                <!--
                <li class="mui-col-sm-12 mui-col-xs-12">
                    <div class="mui-card ">
                        <div class="mui-card-header">
                            <span class="order-id">订单号:59999999999999</span>
                            <span class="order-id-status">交易成功</span>
                        </div>
                        <div class="mui-card-content">
                            <div class="order-goods-img">
                                <img src="${ctx}/images/wx/integral_mall/huodong.jpg">
                            </div>
                            <div class="order-goods-trader">
                                <span class="order-goods-trader-name">89折快车劵</span>
                            </div>
                        </div>
                        <div class="mui-card-footer">
                            <div>
                                <input type="text" value="订单追踪">
                            </div>
                        </div>
                    </div>
                </li>

                <li class="mui-col-sm-12 mui-col-xs-12">
                    <div class="mui-card ">
                        <div class="mui-card-header">
                            <span class="order-id">订单号:59999999999999</span>
                            <span class="order-id-status">交易成功</span>
                        </div>
                        <div class="mui-card-content">
                            <div class="order-goods-img">
                                <img src="${ctx}/images/wx/integral_mall/huodong.jpg">
                            </div>
                            <div class="order-goods-trader">
                                <span>滴滴囚车</span>
                                <span class="order-goods-trader-name">89折快车劵</span>
                            </div>
                        </div>
                        <div class="mui-card-footer">
                            <div>
                                <span>共<span>1</span>件商品,实付: <span>5</span> 积分</span>
                            </div>
                            <div>
                                <input type="text" value="订单追踪">
                            </div>
                        </div>
                    </div>
                </li>
                -->
            </ul>
        </div>

        <!--没有记录-->
        <div class="no-record" style="display: none">
            <div class="empty-msg">你还没有订单记录~</div>
            <div> 赶紧去
                <a href="javascript:void(0)">兑换</a>&nbsp;吧！ </div>
        </div>

    </div>
</div>
</body>
<script src="${ctx}/js/wx/jquery.js"               ></script>
<script src="${ctx}/js/wx/integral_mall/mui.min.js"></script>
<script src="${ctx}/js/wx/integral_wnk/record.js" ></script>
<script>
    mui.init();
</script>
</html>