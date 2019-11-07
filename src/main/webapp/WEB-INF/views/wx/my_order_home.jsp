<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>我的订单</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="/property_system/css/wx/my_order.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/bottom_navition.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/my_order.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="optionClick(0)">
<!-- Tab菜单 STR -->
<div class="pubtabmenu">
    <div class="wrap">
        <a class="item" id="no_pay_order" onclick="optionClick(0)">待支付</a>
        <a class="item" id="no_make_order" onclick="optionClick(1)">待使用</a>
        <a class="item" id="finish_order" onclick="optionClick(2)">已完成</a>
        <a class="item" id="quanyi_order" onclick="optionClick(3)">权益订单</a>
    </div>
</div>
<a class="top_people_static_tag" id="top_people_static_tag">消费次数：0次</a>
<a class="top_number_static_tag" id="top_number_static_tag">金额：0元</a>
<!-- Tab菜单 END -->
<ul class="order_in_progress_ul" id="order_in_progress_ul">
    <li class="order_li" onclick="joinOrderDetail(1,0)">
        <div class="li_top_div">
            <a class="li_top_day_tag">24</a>
            <a class="li_order_no_tag">商家名称</a>
        </div>
        <div class="li_commodites_div">
            <a class="commodities_number_tag">商品(10)</a>
            <ul class="commodities_ul">
                <li>
                    <a class="commodity_name_tag">美式奥尔良鸡腿原味咖喱饭</a>
                    <a class="commodity_number_tag">x1</a>
                    <a class="price_tag">35.00</a>
                </li>
                <li>
                    <a class="commodity_name_tag">美式奥尔良鸡腿原味咖喱饭</a>
                    <a class="commodity_number_tag">x1</a>
                    <a class="price_tag">35.00</a>
                </li>
            </ul>
        </div>
        <div class="li_bottom_div">
            <a class="line_order_time">2018/09/10 15:30:10</a>
            <a class="order_price_tag">￥70.00</a>
        </div>
    </li>
    <li class="order_li" onclick="joinOrderDetail(1,1)">
        <div class="li_top_div">
            <a class="li_top_day_tag">24</a>
            <a class="li_order_no_tag">NO.1111111</a>
        </div>
        <div class="li_commodites_div">
            <a class="commodities_number_tag">基础信息</a>
            <div class="user_div">
                <a>商家分类：111</a>
                <a>商家名称：男</a>
            </div>
        </div>
        <div class="li_bottom_div">
            <a class="line_order_time">2018/09/10 15:30:10</a>
            <a class="order_price_tag">成功</a>
        </div>
    </li>
</ul>

<link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
<link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>
<script src="/property_system/plus/lib/fastclick.js"></script>
<script src="/property_system/js/wx/toast.js"></script>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
<script src="/property_system/plus/js/jquery-weui.js"></script>
<!-- 底部菜单STR -->
<div class="h108"></div>
<div class="footermenu">
    <a href="/property_system/wx/v1.0.0/index" class="item home">首页</a>
    <a href="https://mp.weixin.qq.com/s/x__L4FX98tu0-cd22xkZMA" class="item card_introduction">卡片简介</a>
    <a href="/property_system/wx/v1.0.0/joinWnkMyOrderHome" class="item bus sel">我的订单</a>
    <%--<a href="/property_system/wx/v1.0.0/propertyCenter" class="item wy">物业中心</a>--%>
    <a href="/property_system/wx/v1.0.0/my" class="item my">我的</a>
</div>
</body>
</html>
