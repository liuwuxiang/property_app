<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>密保问题设置</title>
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
  <script src="/property_system/js/wx/set_security_question.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />


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
</head>
<body onload="initData('${current_code}')">
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <form>

      <div class="ftitle">问题一</div>
      <div class="fgroup one">
        <label class="fitem">
          <span class="fname">问题</span>
          <input class="fselect" type="text" placeholder="请选择密保问题" id="selectQuestion1">
        </label>
        <label class="fitem">
          <span class="fname">答案</span>
          <input class="ftext" type="text" placeholder="请填写" id="question_answer_one">
        </label>
      </div>

      <div class="ftitle">问题二</div>
      <div class="fgroup one">
        <label class="fitem">
          <span class="fname">问题</span>
          <input class="fselect" type="text" placeholder="请选择密保问题" id="selectQuestion2">
        </label>
        <label class="fitem">
          <span class="fname">答案</span>
          <input class="ftext" type="text" placeholder="请填写" id="question_answer_two">
        </label>
      </div>

      <div class="ftitle">问题三</div>
      <div class="fgroup one">
        <label class="fitem">
          <span class="fname">问题</span>
          <input class="fselect" type="text" placeholder="请选择密保问题" id="selectQuestion3">
        </label>
        <label class="fitem">
          <span class="fname">答案</span>
          <input class="ftext" type="text" placeholder="请填写" id="question_answer_three">
        </label>
      </div>


      <div class="fhandle">
        <a onclick="settingQuestionAnswer()" class="fsubmit">设置</a>
      </div>

    </form>
  </div>


</body>
</html>
