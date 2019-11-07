<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>身份证认证</title>
  <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/img/logo.jpg" rel="shortcut icon" />
  
  <link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wx/id_card_authentication.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wx/jquery-1.10.2.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <script src="/property_system/js/wx/id_card_authentication.js"></script>
  <script src="/property_system/js/wx/ajaxfileupload.js"></script>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
  
</head>
<body onload="initData()">
  <!-- 头部END -->

  <!-- 通用表单 -->
  <div class="pubform">
    <form>     

      <div class="ftitle">电话号码</div>
      <div class="fgroup one">
        <label class="fitem full">
          <input class="ftext" type="number" id="mobile" placeholder="请填写手机号码">
        </label>
      </div>

      <div class="ftitle">真实姓名</div>
      <div class="fgroup one">
        <label class="fitem full">
          <input class="ftext" type="text" id="real_name" placeholder="请填写真实姓名">
        </label>
      </div>

      <div class="ftitle">身份证号</div>
      <div class="fgroup one">
        <label class="fitem full">
          <input class="ftext" type="text" id="id_card_number" placeholder="请填写身份证号码">
        </label>
      </div>

      <%--<div class="ftitle">身份证有效截止日期</div>--%>
      <%--<div class="fgroup one">--%>
        <%--<label class="fitem full">--%>
          <%--<input class="fselect" type="text" placeholder="请选择身份证有效截止日期" id="selectDatetime">--%>
        <%--</label>--%>
      <%--</div>--%>

      <%--<div class="ftitle">手持身份证正面照片</div>--%>
      <%--<div class="fgroup">--%>
        <%--<!-- 图片上传 -->--%>
        <%--<div class="fuploadimg" id="fuploadimg">--%>
          <%--<!-- <p class="loadimgitem"><img src="images/img_head.jpg" alt=""><span class="delte"></span></p> -->--%>
          <%--<!-- 上传按钮，点击打开选择从相册或者拍照 -->--%>
          <%--<label class="floadimgbut" id="floadimgbut" onclick="imgChoose()"></label>--%>
          <%--<input type="file" accept="image/*" style="display: none;" name="ajaxFile" id="header_file" onchange="chooseHeaderChangeFile()"/>--%>
        <%--</div>--%>
      <%--</div>--%>
      <div class="fhandle">
        <a class="fsubmit" onclick="submitAuthentication()">提交认证</a>
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
    $(function() {
      FastClick.attach(document.body);
    });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
  <script type="text/javascript">
      // 日期选择
      $("#selectDatetime").datetimePicker({
        times: function () {
          return null;
        },
        onChange: function (picker, values, displayValues) {
          console.log(values);
        },
      });

      

  </script>
</body>
</html>
