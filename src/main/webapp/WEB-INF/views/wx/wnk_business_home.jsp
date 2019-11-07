<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商家主页</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/wnk_business_home.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/wnk_business_home.js"></script>
</head>
<body onload="initDataAndImg(${business_id})">
<div class="mui-content">
    <!--轮播图-->
    <div id="slider" class="mui-slider" style="height: 200px;">
        <!--轮播图片-->
        <div class="mui-slider-group mui-slider-loop" id="slider_group"></div>
        <!--导航圆点-->
        <div class="mui-slider-indicator" id="slider_dot"></div>
    </div>
    <!-- Tab菜单 STR -->
    <div class="pubtabmenu" id="pubtabmenu">
        <div class="wrap">
            <a class="item" onclick="listOptionInit(0)" id="sp_item">商品信息</a>
            <a class="item" onclick="listOptionInit(1)" id="sj_jj_item">商家简介</a>
            <%--<a class="item" onclick="listOptionInit(2)" id="jf_sc_item">积分商城</a>--%>
        </div>
    </div>

    <div class="shopping_information_div" id="shopping_information_div">
        <div class="mui-content mui-row mui-fullscreen">
            <div class="mui-col-xs-3">
                <div id="segmentedControls" class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-vertical">
                </div>
            </div>
            <div id="segmentedControlContents" class="mui-col-xs-9" style="border-left: 1px solid #c8c7cc;">
            </div>
        </div>
    </div>

    <div class="business_information_div" id="business_information_div">
        <a id="brief" style="display: block;width: 98%;margin-left: 1%;text-align: left;height: auto;margin-top: 5px;font-size: 17px;"></a>
        <a id="address" style="display: block;width: 98%;margin-left: 1%;text-align: left;height: auto;margin-top: 10px;font-size: 15px;color: #A9A9A9;"></a>
    </div>

</div>

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
