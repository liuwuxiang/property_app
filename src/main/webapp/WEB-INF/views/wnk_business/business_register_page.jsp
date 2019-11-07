<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>商户注册</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <!--标准mui.css-->
    <link rel="stylesheet" href="/property_system/css/wnk_business/mui.min.css">
    <link rel="stylesheet" href="/property_system/css/wnk_business/mui.picker.css">
    <link rel="stylesheet" href="/property_system/css/wnk_business/mui.poppicker.css">
    <link href="/property_system/css/wnk_business/business_register_page.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wnk_business/jquery-1.10.2.min.js"></script>
    <script src="/property_system/js/wnk_business/business_register_page.js"></script>
    <script src="/property_system/js/wnk_business/ajaxfileupload.js"></script>
    <script src="/property_system/js/wnk_business/general_lib.js"></script>
    <script src="/property_system/js/wnk_business/mui.min.js"></script>
    <script src="/property_system/js/wnk_business/mui.picker.js"></script>
    <script src="/property_system/js/wnk_business/mui.poppicker.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wnk_business/common.js"></script>
</head>
<body class="bgw" onload="initData()">

<!-- 头部STR -->
<div class="head">
    <h1>商户注册</h1>
    <a onclick="back()" class="back"></a>
    <a onclick="businessRegister()" class="submit">提交</a>
</div>
<div class="h88"></div>

<div class="mui-content">

    <div class="weui-cells__title">推荐信息:</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">推荐码</label></div>
            <div class="weui-cell__bd">
                <input id="recommend_mobile" value="${mobile}" class="weui-input" type="tel" maxlength="11" placeholder="请输入推荐商家手机号码">
            </div>
        </div>
    </div>

    <div class="weui-cells__title">基础信息:</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">店铺名称</label></div>
            <div class="weui-cell__bd">
                <input id="store_name" class="weui-input" type="text" placeholder="请输入店铺名称">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">店铺分类</label></div>
            <div class="weui-cell__bd">
                <input id="business_type_select" class="weui-input" type="text" placeholder="请选择店铺分类" readonly="readonly" onclick="businessTypeClick()">
                <input id="business_type_select_value" type="hidden" />
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">店铺区域</label></div>
            <div class="weui-cell__bd">
                <input id="area_select" class="weui-input" type="text" placeholder="请选择店铺区域" readonly="readonly" onclick="areaClick()">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">店铺地址</label></div>
            <div class="weui-cell__bd">
                <input id="address" class="weui-input" type="text" placeholder="请选择店铺地址">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">登录手机号</label></div>
            <div class="weui-cell__bd">
                <input id="login_account" class="weui-input" type="tel" maxlength="11" placeholder="请输入登录手机号">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">联系人</label></div>
            <div class="weui-cell__bd">
                <input id="contact_name" class="weui-input" type="text" placeholder="请输入联系人姓名">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">联系电话</label></div>
            <div class="weui-cell__bd">
                <input id="mobile" class="weui-input" type="tel"  maxlength="11" placeholder="请输入联系电话">
            </div>
        </div>
    </div>

    <div class="weui-cells__title">店铺描述</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <textarea id="store_describe" class="weui-textarea" placeholder="请输入店铺描述" rows="3"></textarea>
                <div class="weui-textarea-counter"><span id="textMaxNuber">0</span>/200</div>
            </div>
        </div>
    </div>

</div>

<!-- UI插件 -->
<link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
<link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
<script src="/property_system/plus/lib/fastclick.js"></script>
<script src="/property_system/js/wx/toast.js"></script>
<script>
    $('#store_describe').bind('input propertychange', function() {
        var val = $(this).val();
        var len = 0;
        for(var i = 0; i < val.length; i++) {
            var a = val.charAt(i);
            if(a.match(/[^\x00-\xff]/ig) != null) {
                len += 1;
            } else {
                len += 1;
            }
        }
        $('#textMaxNuber').html(len);
    })
</script>
<script src="/property_system/plus/js/jquery-weui.js"></script>
</body>
</html>
