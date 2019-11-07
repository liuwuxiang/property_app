<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>装修申请</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <link href="/property_system/css/wx/common3.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <!-- 头部STR -->
  <div class="head">
    <h1>装修申请</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
    <a href="" class="submit">申请记录</a>
  </div>
  <div class="h88"></div>

  <div class="pubform">
    <form>
      <a href="" class="choosehouse">
        <p class="name">文化空间D栋1901</p>
        <p class="desc">张凡，187****5223</p>
      </a>

      <div class="publine"></div>

      <div class="fgroup">
        <label class="fitem">
          <span class="fname">开始时间</span>
          <input class="fselect" type="text" value="" placeholder="请选择装修开始时间" id="selectDatetime">
        </label>
        <label class="fitem">
          <span class="fname">结束时间</span>
          <input class="fselect" type="text" value="" placeholder="请选择装修开始时间" id="selectDatetimeend">
        </label>
      </div>

      <div class="fgroup">
        <label class="fitem full">
          <span class="iname">装修说明</span>
          <textarea class="ftextarea" placeholder="请填写装修说明"></textarea>
        </label>
      </div>
      
      <div class="fhandle">
        <a href="" class="fsubmit">提交申请</a>
      </div>
    </form>
  </div>

  
 
  <script src="/property_system/js/wx/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wx/common.js"></script>
  <!-- UI插件 -->
  <link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
  <link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">    
  <script src="/property_system/plus/lib/jquery-2.1.4.js"></script>
  <script src="/property_system/plus/lib/fastclick.js"></script>
  <script>
    $(function() {
      FastClick.attach(document.body);
    });
  </script>
  <script src="/property_system/plus/js/jquery-weui.js"></script>
  <script src="/property_system/plus/js/city-picker.js"></script>
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
    // 日期选择
    $("#selectDatetimeend").datetimePicker({
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
