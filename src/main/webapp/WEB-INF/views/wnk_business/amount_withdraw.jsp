<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>提现</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
  <script src="/property_system/js/wnk_business/amount_withdraw.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部STR -->
  <div class="head">
    <h1>提现</h1>
    <a onclick="back()" class="back"></a>
    <!--<a href="" class="msg sel"></a>  sel表示有消息-->  
  </div>
  <div class="h88"></div>
  
  <!-- 通用表单 -->
  <div class="pubform">
    <form>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">提现金额</span>
          <input class="ftext" type="number" placeholder="请填写提现金额" id="withdraw_number">
        </label>
        <label class="fitem">
          <span class="fname">到账金额</span>
          <p class="finfo">到账<span id="receipts_amount">0</span>元人民币</p>
        </label>       
      </div>
      <div class="ftitle">提现银行卡信息</div>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">开户行</span>
          <input class="fselect" type="text" placeholder="请选择开户行" id="selectBank">
        </label>
        <label class="fitem">
          <span class="fname">银行卡号</span>
          <input class="ftext" type="number" placeholder="请填写银行卡号" id="bank_card_number">
        </label>
        <label class="fitem">
          <span class="fname">姓名</span>
          <input class="ftext" type="text" placeholder="请填写卡户姓名" id="real_name">
        </label>
      </div>
      <div class="fhandle">
        <a onclick="submitWithdraw()" class="fsubmit">提现</a>
      </div>
      <div class="fnotice">
        温馨提示<br>
        <a id="withdraw_tip">
          1.提现申请时间为每周四10:00-17:30；<br>
          2.提现手续费1%。
        </a>
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
          $('#withdraw_number').bind('input propertychange', function() {
              inputChange($(this).val());
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
</body>
</html>
