<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>实名认证</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wx/student_authentication.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/adult_authentication.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <!-- 头部END -->

  <!-- 表单STR -->
  <div class="pubform">
    <form>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">真实姓名</span>
          <input class="ftext" type="text" placeholder="请输入您的姓名" id="real_name">
        </label>
        <label class="fitem">
          <span class="fname">身份证号</span>
          <input class="ftext" type="text" placeholder="请输入您的身份证号" id="id_card_number">
        </label>
        <label class="fitem">
          <span class="fname">手机号</span>
          <input class="ftext" type="text" placeholder="请输入您的手机号" id="mobile">
        </label>
        <label class="fitem">
          <span class="fname">验证码</span>
          <input class="ftext" type="text" placeholder="请填写验证码" id="code">
          <a onclick="getCode()" class="getcode">获取验证码</a>
        </label>
      </div>
      <div class="checkbox_div">
      	<img src="/property_system/images/wx/check.png" class="check_img" id="check_img" onclick="isAuthorization()"/>
      	<a class="span_tag">阅读并同意<a href="/property_system/wx/v1.0.0/joinCertificateAuthorization" class="href_tag">实名认证授权书</a></a>
      </div>
      <div class="fhandle">
        <a onclick="submitAuthentication()" class="fsubmit">提交认证</a>
      </div>
      	<a class="tip_tag">实名认证有助于账号安全及参与专属优惠活动</a>
    </form>
  </div>
  <!-- 表单END -->

  <script src="/property_system/js/wx/mui.min.js"></script>
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
