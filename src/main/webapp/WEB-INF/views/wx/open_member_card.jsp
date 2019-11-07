<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>${title}</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
    <script src="/property_system/js/wx/mui.min.js"></script>
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/open_member_card.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <script src="/property_system/js/wx/open_member_card.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body onload="initData(${type},${member_card_level},${open_card_type})">
<!-- 头部END -->

<!-- 表单STR -->
<div class="pubform">
    <form>
        <div class="ftitle">卡片类型</div>
        <div class="fgroup" id="wnk_buy_meal_div">
            <%--<label class="fradio" style="height: 50px;" id="yinka_member_card">--%>
                <%--<span class="icon" style="background-image: url(/property_system/images/wx/qingchuncard.png);"></span>--%>
                <%--<span class="fname">青春万能卡-999积分</span>--%>
                <%--<input type="radio" name="card_type" id="qingchuncard_radio">--%>
                <%--<span class="status"></span>--%>
            <%--</label>--%>
            <%--<label class="fradio" style="height: 50px;" id="yinka_member_card">--%>
                <%--<span class="icon" style="background-image: url(/property_system/images/wx/chaojicard.png);"></span>--%>
                <%--<span class="fname">超级万能卡-2000积分</span>--%>
                <%--<input type="radio" name="card_type" id="cjcard_radio" checked>--%>
                <%--<span class="status"></span>--%>
            <%--</label>--%>

            <%--<label class="fradio" style="height: 50px;" id="jinka_member_card">--%>
                <%--<span class="icon" style="background-image: url(/property_system/images/wx/jinka_icon.png);"></span>--%>
                <%--<span class="fname">金卡-5000积分</span>--%>
                <%--<input type="radio" name="card_type" id="jinka_radio">--%>
                <%--<span class="status"></span>--%>
            <%--</label>--%>
        </div>
        <div class="ftitle">支付方式</div>
        <div class="fgroup">
            <label class="fradio" style="height: 50px;">
                <span class="icon" style="background-image: url(/property_system/images/wx/general_integral.png);"></span>
                <span class="fname">通用积分</span>
                <input type="radio" name="pay" id="general_integral_radio" checked>
                <span class="status"></span>
            </label>

            <label class="fradio" style="height: 50px;">
                <span class="icon" style="background-image: url(/property_system/images/wx/consumption_integral.png);"></span>
                <span class="fname">消费积分</span>
                <input type="radio" name="pay" id="consumption_integral_radio">
                <span class="status"></span>
            </label>

            <label class="fradio" style="height: 50px;">
                <span class="icon wxpay"></span>
                <span class="fname">微信支付</span>
                <input type="radio" name="pay" id="wx_pay_radio">
                <span class="status"></span>
            </label>
        </div>
        <div class="fhandle">
            <a class="fsubmit" onclick="arrgenHandelMemberCard(0)" id="business_button">商家办理</a>
            <a class="fsubmit" onclick="arrgenHandelMemberCard(1)" style="margin-top: 10px;">个人办理</a>
        </div>
    </form>
</div>
<!-- 表单END -->

<!--我的二维码-->
<%--<div id="my_qrcode_div" class="box mui-popover mui-popover-action mui-popover-bottom mobile_set_check_div">--%>
    <%--<div class="wc_qrcode_div">--%>
        <%--<div class="qrcode_div">--%>
            <%--<a style="display: block;display: block;height: 30px;line-height: 30px;color: #000000;font-size: 18px;margin-top: 10px;width: 100%;">卡片权益说明</a>--%>
            <%--<div style="clear: both;"></div>--%>
            <%--<a style="display: block;display: block;height: 30px;line-height: 30px;color: #000000;font-size: 17px;margin-top: 10px;text-align: left;margin-left: 4%;">①青春万能卡:</a>--%>
            <%--<a style="display: block;display: block;height: 30px;color: #A9A9A9;font-size: 15px;margin-top: 3px;text-align: left;margin-left: 4%;">享受权益:一年（卡片有效期）内健身无限次、电影50次，美发15 次，vip商家折扣。</a>--%>

            <%--<a style="display: block;display: block;height: 30px;line-height: 30px;color: #000000;font-size: 17px;margin-top: 30px;text-align: left;margin-left: 4%;">②超级万能卡:</a>--%>
            <%--<a style="display: block;display: block;height: 30px;color: #A9A9A9;font-size: 15px;margin-top: 3px;text-align: left;margin-left: 4%;">享受权益:两年（卡片有效期）内观影、健身、美发、洗车、vip商家折扣使用总次数500次</a>--%>

            <%--<input type="button" value="阅读并同意" class="agree_button" onclick="arrgenHandelMemberCard()">--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<script type="text/javascript">
    // 显示二维码
    selShow("ewm","ewmshow");
</script>
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
