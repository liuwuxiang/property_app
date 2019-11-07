<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/shop_detail.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/mui.min.js"></script>
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/shop_detail.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body onload="initData(${commodity_id})">
<div class="mui-content">
    <!--轮播图-->
    <div id="slider" class="mui-slider" style="height: 200px;">
        <!--轮播图片-->
        <div class="mui-slider-group mui-slider-loop" id="slider_group"></div>
        <!--导航圆点-->
        <div class="mui-slider-indicator" id="slider_dot"></div>
    </div>
    <div class="shop_information_div">
        <a class="shop_name_tag" id="commodity_name"></a>
        <%--<a class="shop_tip_tag">仅售9.9元，价值68元健身体验卡，节假日通用，免费WIFI，男女通用，含淋浴！免费提供储物柜！</a>--%>
    </div>
    <div class="guige_div">
        <a class="guige_tag">商品规格</a>
        <ul id="guige_ul">
            <%--<li class="no_choose_li">1111111111</li>--%>
            <%--<li class="no_choose_li choose_li">2222222222</li>--%>
            <%--<li class="no_choose_li">2222222222</li>--%>
        </ul>
    </div>
    <div class="price_information_div">
        <a class="current_price_tag" id="commodity_price"></a>
        <%--<a class="rack_rate_tag">门市价:￥68</a>--%>
        <input type="button" value="立即订购" class="order_immediately_button" onclick="buy_shop(0)"/>
        <%--<input type="button" value="使用万能卡" class="order_immediately_button" onclick="toggleMenu()" id="make_wnk_button" style="display:none;"/>--%>
        <input type="button" value="使用万能卡" class="order_immediately_button" onclick="buy_shop(1)" id="make_wnk_button" style="display:none;"/>
    </div>
    <div class="shop_information_div">
        <a class="shop_name_tag">商品描述</a>
        <a class="shop_tip_tag" id="commodity_describe"></a>
    </div>

</div>

<!--使用二维码-->
<div id="menu-wrapper" class="menu-wrapper hidden" style="z-index: 99999;">
    <div id="menu" class="menu">
        <div class="qr_code_div">
            <img src="/property_system/images/wx/qr_code.png" class="qr_code_img" id="wnk_qrcode_img"/>
            <a class="qr_code_tip">使用时,出示此码</a>
            <div class="down_arrow_div" id="down_arrow_div">
                <img src="/property_system/images/wx/down_arrow.png"/>
            </div>
        </div>
    </div>
</div>
<div id="menu-backdrop" class="menu-backdrop"></div>

<script src="/property_system/js/wx/mui.min.js"></script>
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
</body>
</html>
