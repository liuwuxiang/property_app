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
    <link href="/property_system/css/wnk_business/shop_information.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wnk_business/add_commodity.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wnk_business/jquery-1.10.2.min.js"></script>
    <script src="/property_system/js/wnk_business/business_register.js"></script>
    <script src="/property_system/js/wnk_business/ajaxfileupload.js"></script>
    <script src="/property_system/js/wnk_business/general_lib.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wnk_business/common.js"></script>
</head>
<body class="bgw" onload="initData()">
<input type="file" accept="image/*" style="display: none;" name="ajaxFile" id="header_file" onchange="chooseHeaderChangeFile()"/>
<input type="file" accept="image/*" style="display: none;" name="ajaxFile2" id="header_file2" onchange="chooseHeaderChangeFile2()"/>
<input type="hidden" value="${business_id}" id="business_id">
<!-- 头部STR -->
<div class="head">
    <h1>商户注册</h1>
    <a onclick="back()" class="back"></a>
    <a onclick="businessRegister()" class="submit">提交</a>
</div>
<div class="h88"></div>

<ul class="commodity_information_ul">

    <li>
        <a class="value_name_tip">推荐人</a>
        <input type="number" value="${mobile}" placeholder="请输入推荐人手机号" class="text_input" id="recommend_mobile"/>
    </li>
    <li>
        <a class="value_name_tip">店铺名称</a>
        <input type="text" value="" placeholder="请输入店铺名称" class="text_input" id="store_name"/>
    </li>
    <li>
        <a class="value_name_tip">店铺分类</a>
        <select id="business_type_select">

        </select>
    </li>
    <li>
        <a class="value_name_tip">店铺区域</a>
        <input type="text" value="" placeholder="请输入店铺区域(如:东区/西区)" class="text_input" id="area"/>
    </li>
    <li>
        <a class="value_name_tip">店铺地址</a>
        <input type="text" value="" placeholder="请输入店铺地址" class="text_input" id="address"/>
    </li>
    <li>
        <a class="value_name_tip">登录手机号</a>
        <input type="number" value="" placeholder="请输入登录手机号" class="text_input" id="login_account"/>
    </li>
    <li>
        <a class="value_name_tip">联系人</a>
        <input type="text" value="" placeholder="请输入店铺联系人" class="text_input" id="contact_name"/>
    </li>
    <li>
        <a class="value_name_tip">联系电话</a>
        <input type="number" value="" placeholder="请输入店铺联系电话" class="text_input" id="mobile"/>
    </li>
    <li>
        <a class="value_name_tip">店铺描述</a>
        <textarea class="text_textarea" placeholder="请输入店铺描述" id="store_describe"></textarea>
    </li>
    <li>
        <a class="value_name_tip photo_choose_tag">营业执照</a>
        <label class="choose_photo_button" id="floadimgbut" onclick="choosePhoto(0)">选择图片</label>
        <%--<input type="button" value="选择图片" class="choose_photo_button" onclick="choosePhoto(0)"/>--%>
        <ul class="lunbo_photo_ul" id="yingyezhizhao_ul">
            <li id="photo0">
                <img src="" class="photo_img" id="yingyezhizhao_img"/>
            </li>
        </ul>
    </li>
    <li>
        <a class="value_name_tip photo_choose_tag">门头及店内照片</a>
        <label class="choose_photo_button" onclick="choosePhoto(1)">选择图片</label>
        <%--<input type="button" value="选择图片" class="choose_photo_button" onclick="choosePhoto(1)"/>--%>
    </li>
</ul>
<ul class="lunbo_photo_ul" id="lunbo_photo_ul">
    <%--<li id="photo0">--%>
    <%--<img src="/property_system/images/wnk_business/integral_card_background.png" class="photo_img"/>--%>
    <%--<img src="/property_system/images/wnk_business/delete_icon.png" class="delete_button" onclick="deletePhotoLi(0)"/>--%>
    <%--</li>--%>
</ul>

<!-- UI插件 -->
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
