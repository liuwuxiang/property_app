<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/29
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>积分商城-积分兑换列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/mui.min.css"  />
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/category.css" />
</head>
<body>
<input type="hidden" value="${id}" id="typeId">
<div class="mui-col-sm-12 mui-col-xs-12">

    <div id="time" class="font-headr mui-col-sm-6 mui-col-xs-6">
        新品
    </div>
    <div id="price" class="font-headr mui-col-sm-6 mui-col-xs-6">
        价格
        <span id="price-sort" class="cell-filter"></span>
    </div>

    <div id="yes" class="content mui-col-sm-12 mui-col-xs-12" >

        <div class="list-row mui-col-sm-12 mui-col-xs-12">
            <ul id="yes-ul">
                <!--
                <li>
                    <div name="goods" class="goods">
                        <div class="media mui-col-sm-6 mui-col-xs-6">
                            <img src="${ctx}/images/wx/integral_mall/huodong.jpg"/>
                        </div>

                        <div class="info mui-col-sm-6 mui-col-xs-6">
                            <span class="type">腾讯王卡1</span>
                            <span class="name">腾讯王卡 含20元话费腾讯王卡 含20元话费 </span>
                            <span class="customer-level"></span>
                            <p class="customer-price">
                                <span class="text-highlight b">39</span>
                                <span>积分</span>
                            </p>
                        </div>
                    </div>
                </li>
                -->
            </ul>
        </div>

    </div>


    <div id="no" class="content mui-col-sm-12 mui-col-xs-12" style="display: none;">
        <div class="no-goods">
            <span class="ic"></span>
            <div class="text">精彩商品敬请期待~</div>
        </div>
    </div>


</div>

<script type="text/javascript" src="${ctx}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/mui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/main.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/category.js"></script>

<script>
    mui.init()
</script>
</body>

</html>