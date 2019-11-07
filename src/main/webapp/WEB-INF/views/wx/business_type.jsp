<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>万能卡权益</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
    <link rel="stylesheet" href="/property_system/css/wx/business_type.css">
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/mui.min.js"></script>
    <script src="/property_system/js/wx/business_type.js"></script>
</head>

<body onload="initData()">
<div class="mui-content">
    <div class="my_member_card_div" id="memberqy">
        <img src="/property_system/images/wx/yinka_background.png" class="top_background_img" id="top_background_img">
        <a class="card_number_tag" id="card_number_tag">NO.</a>
        <div class="xx_div" id="xx_div">
            <%--<img src="/property_system/images/wx/yinka_xx.png"/>--%>
        </div>
        <a class="term_validity_tag" id="term_validity_tag">有效期至：</a>
    </div>
    <!--二维码按钮凸起视图-->
    <div class="qr_code_tuqi_div" id="qr_code_tuqi_div"></div>
    <!--二维码按钮视图-->
    <div class="qr_code_button_div" id="qr_code_button_div" onclick="toggleMenu()">
        <img src="/property_system/images/wx/qr_code.png"/>
    </div>
    <!--商家分类视图-->
    <div class="business_type_div" id="business_type_div">
        <ul class="business_type_ul" id="business_type_ul">
            <li class="business_type_ul_li">
                <input type="hidden" value="0" id="zk_swith_2"/>
                <div class="business_type_li_div" onclick="businessTypeClick(2)">
                    <img src="/property_system/images/wx/dianyingyuan.png" class="li_background_img"/>
                    <div class="li_top_div">
                        <img src="/property_system/images/wx/logo.jpg"/>
                        <a>电影院</a>
                    </div>
                    <a class="surplus_tip" id="surplus_tip">剩余:1次</a>
                </div>
                <ul class="business_ul" id="business_ul_2">
                    <li onclick="joinBusinessHome(id)">
                        <a class="business_area"></a>
                        <a class="business_name">海克力斯健身(万达店)</a>
                    </li>
                </ul>
            </li>

            <li class="business_type_ul_li">
                <input type="hidden" value="0" id="zk_swith_1"/>
                <div class="business_type_li_div" onclick="businessTypeClick(1)">
                    <img src="/property_system/images/wx/jianshen_background.png" class="li_background_img"/>
                    <div class="li_top_div">
                        <img src="/property_system/images/wx/logo.jpg"/>
                        <a>健身</a>
                    </div>
                    <a class="surplus_tip" id="surplus_tip1">剩余:1次</a>
                </div>
                <ul class="business_ul" id="business_ul_1">
                    <%--<li onclick="joinBusinessHome()">--%>
                        <%--<a>海克力斯健身(万达店)</a>--%>
                    <%--</li>--%>
                </ul>
            </li>

            <li class="business_type_ul_li">
                <input type="hidden" value="0" id="zk_swith_5"/>
                <div class="business_type_li_div" onclick="businessTypeClick(5)">
                    <img src="/property_system/images/wx/meifa_background.jpg" class="li_background_img"/>
                    <div class="li_top_div">
                        <img src="/property_system/images/wx/logo.jpg"/>
                        <a>美发</a>
                    </div>
                    <a class="surplus_tip" id="surplus_tip5">剩余:1次</a>
                </div>
                <ul class="business_ul" id="business_ul_5">
                    <%--<li onclick="joinBusinessHome()">--%>
                    <%--<a>海克力斯健身(万达店)</a>--%>
                    <%--</li>--%>
                </ul>
            </li>

            <li class="business_type_ul_li">
                <input type="hidden" value="0" id="zk_swith_0"/>
                <div class="business_type_li_div" onclick="businessTypeClick(0)">
                    <img src="/property_system/images/wx/mianfeixiche.jpg" class="li_background_img"/>
                    <div class="li_top_div">
                        <img src="/property_system/images/wx/logo.jpg"/>
                        <a>免费洗车</a>
                    </div>
                    <a class="surplus_tip" id="surplus_tip2">剩余:1次</a>
                </div>
                <ul class="business_ul" id="business_ul_0">
                    <%--<li onclick="joinBusinessHome()">--%>
                        <%--<a>海克力斯健身(万达店)</a>--%>
                    <%--</li>--%>
                </ul>
            </li>

            <li class="business_type_ul_li">
                <input type="hidden" value="0" id="zk_swith_3"/>
                <div class="business_type_li_div" onclick="businessTypeClick(3)">
                    <img src="/property_system/images/wx/vip_business_background.png" class="li_background_img"/>
                    <div class="li_top_div">
                        <img src="/property_system/images/wx/logo.jpg"/>
                        <a>vip商家</a>
                    </div>
                    <a class="surplus_tip" id="surplus_tip3">剩余:1次</a>
                </div>
                <ul class="business_ul" id="business_ul_3">
                    <%--<li onclick="joinBusinessHome()">--%>
                        <%--<a>海克力斯健身(万达店)</a>--%>
                    <%--</li>--%>
                </ul>
            </li>
        </ul>
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


<link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
<link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
<%--<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>--%>
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