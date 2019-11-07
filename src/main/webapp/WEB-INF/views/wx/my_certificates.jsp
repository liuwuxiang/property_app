<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>我的证书</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
    <link href="/property_system/css/wx/my_certificate.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
    <script src="/property_system/js/wx/common.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
    <script src="/property_system/js/wx/my_certificates.js"></script>
</head>
<body class="bgw" onload="getMyCertificate()">

<!-- 明细STR -->
<div class="condetaile">
    <ul class="consumption_detail_ul" id="consumption_detail_ul">
        <li onclick="lookCertificatePhoto('123')">
            <div class="li_left">
                <img src="../images/6000002131343400.jpg"/>
            </div>
            <div class="li_right">
                <a class="certicate_number">NO.6000000307635744</a>
                <a class="certificate_time">2018-07-09 13:00:00</a>
            </div>
        </li>
    </ul>
</div>
<!-- 明细END -->

<!-- 积分为空 -->
<!-- 积分为空 -->
<div class="publicnull" id="publicnull_tip">
    <span class="icon jf"></span>
    <p class="text">暂无证书</p>
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



<script type="text/javascript">
    // tab菜单顶部固定
    pubtabmenu("pubtabmenu",0.88);
</script>

</body>
</html>
