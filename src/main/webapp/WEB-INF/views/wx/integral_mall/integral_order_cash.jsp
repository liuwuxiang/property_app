<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/27
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html style="font-size: 42.6667px;" class="pixel-ratio-2 retina ios ios-10 ios-10-0 ios-gt-9 ios-gt-8 ios-gt-7 ios-gt-6"><head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>通用积分提现</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="">
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link href="${ctx}/css/wx/common.css" rel="stylesheet" type="text/css">
    <script src="${ctx}/js/wx/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/js/wx/common.js"></script>
    <script src="${ctx}/js/wx/integral_mall/main.js"></script>
    <script src="${ctx}/js/wx/integral_mall/integral_order_cash.js"></script>
    <script src="${ctx}/js/wx/hui.js"></script>
    <link href="${ctx}/css/wx/hui.css" rel="stylesheet" type="text/css">
</head>
<body onload="load()">
<!-- 头部END -->
<!-- 通用表单 -->
<div class="pubform">
    <form id="cash-form">
        <div class="ftitle">
            <a style="color: #A9A9A9;" id="current_general_integral"><%--当前可用5706个平台积分--%></a></div>
        <div class="fgroup">
            <label class="fitem">
                <span class="fname">提现积分</span>
                <input class="ftext" id="withdraw_number" type="number" placeholder="请填写提现积分个数">
            </label>
            <label class="fitem">
                <span class="fname">提现积分</span>
                <p class="finfo">可兑换<span id="count_amount">0</span>个通用积分</p>
            </label>
        </div>
        <div class="fhandle">
            <a class="fsubmit" id="fsubmit" onclick="submitWithdraw()">提现</a>
        </div>
        <div class="fnotice">
            温馨提示<br>
            <a id="withdraw_tip">1.获取通用积分=提现积分个数 * 1%<br>2.提现数量必须为100的倍数</a>
        </div>
    </form>
</div>

<!-- 支付密码弹出 -->
<div class="floatbox" id="payfloatbox">
    <div class="wrap">
        <p class="tit">支付密码</p>
        <div class="box">
            <p class="msg">请填写支付密码进行验证</p>
            <label class="floatinput">
                <input type="password" name="" placeholder="请填写支付密码">
            </label>
        </div>
        <div class="handle">
            <a href="javascript:;" class="close">取消</a>
            <a class="submit" onclick="submitWithdraw()">确定</a>
        </div>
    </div>
</div>

<!-- UI插件 -->
<link rel="stylesheet" href="${ctx}/plus/lib/weui.min.css">
<link rel="stylesheet" href="${ctx}/plus/css/jquery-weui.css">
<script src="${ctx}/plus/lib/jquery-2.1.4.js"></script>
<script src="${ctx}/plus/lib/fastclick.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
<script src="${ctx}/plus/js/jquery-weui.js"></script>
<script src="${ctx}/plus/js/city-picker.js"></script>
<script type="text/javascript">
    $(function(){
        $('#withdraw_number').bind('input propertychange', function() {
            if (parseInt($('#withdraw_number').val()) % 100 == 0){
                $('#count_amount').html(parseInt($('#withdraw_number').val()) / 100)
            } else {
                $('#count_amount').html(0);
            }
        });

    })
    $(function(){
        $('#bank_card_number').bind('input propertychange', function() {
            bankCardNumberinputChange($(this).val());
        });

    })
    // 验证支付密码
    //      selShow("fsubmit","payfloatbox");
</script>
</body></html>