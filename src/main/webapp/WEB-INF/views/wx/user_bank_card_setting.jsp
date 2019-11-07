<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>银行卡信息</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/user_bank_card_setting.js"></script>
</head>
<body onload="initData(${bank_id},'${bank_name}','${bank_card_number}','${real_name}')">
<!-- 头部END -->

<!-- 通用表单 -->
<div class="pubform">
    <form>
        <div class="ftitle">提现银行卡信息</div>
        <div class="fgroup">
            <label class="fitem">
                <span class="fname">开户行</span>
                <input class="fselect" type="text" placeholder="请选择开户行" id="selectBank">
            </label>
            <label class="fitem">
                <span class="fname">银行卡号</span>
                <input class="ftext" type="number" id="bank_card_number" placeholder="请填写银行卡号">
            </label>
            <label class="fitem">
                <span class="fname">姓名</span>
                <input class="ftext" type="text" id="real_name" placeholder="请填写卡户姓名">
            </label>
        </div>
        <div class="fhandle">
            <a class="fsubmit" id="fsubmit" onclick="settingBankCard()">设置</a>
        </div>
    </form>
</div>

<!-- UI插件 -->
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
<script src="/property_system/plus/js/city-picker.js"></script>
<script type="text/javascript">
    $(function(){
        $('#bank_card_number').bind('input propertychange', function() {
            bankCardNumberinputChange($(this).val());
        });

    })
</script>
</body>
</html>
