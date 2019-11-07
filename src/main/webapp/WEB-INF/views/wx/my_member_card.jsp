<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>我的会员卡</title>
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
    <script src="/property_system/js/wx/my_member_card.js"></script>
</head>
<%--<body class="bgw" onload="initData()">--%>
<body class="bgw" onload="initData()">
<!-- 会员权益STR -->
<div class="memberqy">
    <div class="qyhead">
        <span class="vip member_card1" id="member_card_level"></span><!-- 普通会员 vip1  铂金会员 vip2 -->
        <span class="name" id="member_level_name">银卡会员</span>
        <p class="star" id="star">
            <%--<i class="sel"></i>--%>
            <%--<i class="sel"></i>--%>
            <%--<i class="sel"></i>--%>
            <%--<i class="sel"></i>--%>
            <%--<i></i>--%>
        </p>
        <a href="/property_system/wx/v1.0.0/joinUpgradeMyMemberCard" class="uplev" id="uplev">升级</a>
    </div>

    <!-- 权益内容 -->
    <div class="qycontent">
        <div class="info">
            <p style="text-align:justify;" id="contentInsert" contenteditable="true">
            </p>
        </div>
    </div>

    <!-- 文章为空 -->
    <%--<div class="publicnull">--%>
    <%--<span class="icon article"></span>--%>
    <%--<p class="text">暂无使用特权</p>--%>
    <%--</div>--%>
    <%--</div>--%>
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
