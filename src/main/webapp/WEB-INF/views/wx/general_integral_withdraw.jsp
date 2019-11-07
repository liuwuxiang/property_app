<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>通用积分提现</title>
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
  <script src="/property_system/js/wx/general_integral_withdraw.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body onload="initData(${bank_id},'${bank_name}','${bank_card_number}','${real_name}')">
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <form>
      <%--<div class="ftitle"><a style="color: #A9A9A9;" id="current_general_integral">当前可用188000个通用积分</a>(<span id="exchange_number_tag">10个通用积分=1元人民币</span>)</div>--%>
      <div class="ftitle"><a style="color: #A9A9A9;" id="current_general_integral">当前可用188000个通用积分</a></div>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">提现积分</span>
          <input class="ftext" id="withdraw_number" type="number" placeholder="请填写提现积分个数">
        </label>
        <label class="fitem">
          <span class="fname">金额</span>
          <p class="finfo">可兑换<span id="count_amount">0</span>元人民币</p>
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
          <input class="ftext" type="number" id="bank_card_number" placeholder="请填写银行卡号">
        </label>
        <label class="fitem">
          <span class="fname">姓名</span>
          <input class="ftext" type="text" id="real_name" placeholder="请填写卡户姓名">
        </label>
      </div>
      <div class="fhandle">
        <a class="fsubmit" id="fsubmit" onclick="submitWithdraw()">提现</a>
      </div>
      <div class="fnotice">
        温馨提示<br>
        <a id="withdraw_tip">
        1.10个通用积分=1元人民币；<br>
        2.提现申请时间为每周四10:00-17:30；<br>
        3.提现手续费1%。
        </a>
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
