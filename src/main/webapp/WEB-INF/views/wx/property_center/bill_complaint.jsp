<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>投诉</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common3.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
</head>
<body>
  <!-- 头部STR -->
  <div class="head">
    <h1>投诉</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
  </div>
  <div class="h88"></div>
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <form>

      <div class="fgroup one">
        <label class="fcheckbox">
          <input type="radio" name="sex" checked>
          <span class="status"></span>
          <span class="fname">账单金额不符</span>
        </label>
        <label class="fcheckbox">
          <input type="radio" name="sex">
          <span class="status"></span>
          <span class="fname">此账单已缴费</span>
        </label>
        <label class="fcheckbox">
          <input type="radio" name="sex">
          <span class="status"></span>
          <span class="fname">账户被盗，钱不是我付的</span>
        </label>
      </div>
      <div class="fhandle">
        <a href="" class="fsubmit">提交</a>
      </div>
    </form>
  </div>
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
</body>
</html>
