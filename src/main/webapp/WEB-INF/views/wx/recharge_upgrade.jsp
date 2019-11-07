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
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/recharge_upgrade.js"></script>
    <style type="text/css">body,html{min-height:100%; }</style>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />

</head>
<body onload="initData(${member_level_id},${integral_number})">
<!-- 头部END -->

<!-- 表单STR -->
<div class="pubform">
    <form>
        <div class="ftitle">积分兑换</div>
        <div class="fgroup">
            <label class="fitem">
                <span class="fname">银币</span>
                <input class="ftext" type="number" id="exchange_number" value="${silver_coin_number}" placeholder="请选择兑换银币的个数" disabled="disabled">
            </label>
            <div class="jfbar">需消耗<span id="integral_number">${integral_number}</span>个积分</div>
        </div>
        <div class="ftitle">支付方式</div>
        <div class="fgroup">
            <label class="fradio" onclick="pay_way_choose(0)">
                <span class="icon consumption"></span>
                <span class="fname">消费积分支付</span>
                <input type="radio" name="pay" checked>
                <span class="status"></span>
            </label>
            <label class="fradio" onclick="pay_way_choose(1)">
                <span class="icon general"></span>
                <span class="fname">通用积分支付</span>
                <input type="radio" name="pay">
                <span class="status"></span>
            </label>
        </div>
        <div class="fhandle">
            <a class="fsubmit" onclick="exchangeAction()">兑换升级</a>
        </div>
    </form>
</div>
<!-- 表单END -->

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
</body>
</html>
