<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>投诉建议</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.10.2.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/suggestion_feedback.js"></script>
  <script src="/property_system/js/wx/ajaxfileupload.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body>

  <!-- 通用表单 -->
  <div class="pubform">
    <form>
      <div class="ftitle">您的建议反馈是我们前进的动力</div>
      <div class="fgroup">
        <label class="fitem full">
          <textarea class="ftextarea" id="ftextarea" placeholder="请填写您的建议反馈"></textarea>
        </label>
        <!-- 图片上传 -->
        <div class="fuploadimg" id="fuploadimg">

          <%--<p class="loadimgitem"><img src="/property_system/images/wx/img_head.jpg" alt=""><span class="delte"></span></p>--%>
          <!-- 上传按钮，点击打开选择从相册或者拍照 -->
          <label class="floadimgbut" id="floadimgbut" onclick="imgChoose()"></label>
          <input type="file" accept="image/*" style="display: none;" name="ajaxFile" id="header_file" onchange="chooseHeaderChangeFile()"/>
        </div>
      </div>
      <div class="fhandle">
        <a onclick="feedback()" class="fsubmit">反馈</a>
      </div>

    </form>
  </div>

  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
  <%--<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>--%>
  <script src="/property_system/plus/lib/fastclick.js"></script>
  <script src="/property_system/js/wx/toast.js"></script>
  <script>
      //            $(function() {
      //                FastClick.attach(document.body);
      //            });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
</body>
</html>
