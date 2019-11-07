<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>提现至通用积分</title>
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
    <script src="/property_system/js/wx/can_withdraw_silver_coin.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
<!-- 头部END -->

<!-- 积分STR -->
<div class="jfhead">
    <div class="desc">
        <span class="tit">可提现</span>
        <p class="num" id="sovile_coin_balance">0</p>
    </div>
    <div class="desc">
        <span class="tit">到账个数</span>
        <p class="num" id="account_number">0</p>
    </div>
    <div class="hadle">
        <a href="javascript:;" class="help"><!-- 占位 --></a>
        <div class="buts">
            <a onclick="oneKeyLifting()" class="b2" style="width:auto;padding-left: 10px;padding-right: 10px;">一键提现</a>
        </div>
    </div>


</div>
<!-- 积分END -->

<a style="display: block;color: #FF69B4;font-size: 12px;margin-left: 10px;">注：60%不可提现,平台管理费为10%,可提现比例为30%</a>
<div class="pubtit_2">
    当前可提现记录(银币)
</div>

<!-- 明细STR -->
<div class="condetaile">
    <ul class="list" id="list">
        <li class="item">
            <div class="left">
                <span class="name">升级为铂金会员</span>
                <span class="time">余额 6000银币</span>
            </div>
            <div class="right">
                <span class="num down">-1800</span>
                <p class="time">2018-05-20 10:59:59</p>
            </div>
        </li>
        <li class="item">
            <div class="left">
                <span class="name">升级为铂金会员</span>
                <span class="time">余额 6000银币</span>
            </div>
            <div class="right">
                <span class="num down">-1800</span>
                <p class="time">2018-05-20 10:59:59</p>
            </div>
        </li>
    </ul>
</div>
<!-- 明细END -->

<!-- 积分为空 -->
<div class="publicnull" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text">暂无可提现收益</p>
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


</body>
</html>
