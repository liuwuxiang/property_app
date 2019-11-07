<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>支付密码设置</title>
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
  <script src="/property_system/js/wx/set_pay_pwd.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body onload="initData('${current_code}')">
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <form>

      <div class="ftitle">新密码</div>
      <div class="fgroup one">
        <label class="fitem full">
          <input class="ftext" id="new_pay_pwd" type="password" placeholder="请填写新的支付密码">
        </label>
      </div>

      <div class="ftitle">确认新密码</div>
      <div class="fgroup one">
        <label class="fitem full">
          <input class="ftext" id="new_agin_pay_pwd" type="password" placeholder="请再次确认新的支付密码">
        </label>
      </div>

      <div class="fhandle">
        <a class="fsubmit" onclick="setPayPwd()">设置</a>
      </div>

    </form>
  </div>

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
