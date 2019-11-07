<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>${title}积分充值</title>
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
  <script src="/property_system/js/wx/integral_recharge.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
 
</head>
<body onload="initData(${type})">
  <!-- 头部END -->

  <!-- 表单STR -->
  <div class="pubform">
    <form>
      <div class="ftitle">充值金额</div>
      <%--<div class="ftitle">充值金额(<span id="top_tip">1元=1个${title}</span>)</div>--%>
      <div class="fgroup">
        <label class="fitem price" style="height: 40px;">
          <span class="fname" style="height: 40px;line-height: 40px;">¥</span>
          <input class="ftext" type="number" placeholder="充值金额" id="recharge_amount" style="height: 40px;">
        </label>
        <div class="jfbar" style="height: 35px;">可获得<span id="integral_number">0</span>个${title}</div>
      </div>
      <div class="ftitle">支付方式</div>
      <div class="fgroup">
        <label class="fradio" style="height: 50px;">
          <span class="icon wxpay"></span>
          <span class="fname">微信支付</span>
          <input type="radio" name="pay" checked>
          <span class="status"></span>
        </label>

        <label class="fradio" style="height: 50px;">
          <span class="icon alipay"></span>
          <span class="fname">支付宝支付</span>
          <input type="radio" name="pay">
          <span class="status"></span>
        </label>
      </div>
      <div class="fhandle">
        <a class="fsubmit" onclick="rechargeAction()">充值</a>
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
      $(function(){
          $('#recharge_amount').bind('input propertychange', function() {
              inputChange($(this).val());
          });

      })
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
</body>
</html>
