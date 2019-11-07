<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>维修</title>
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
    <h1>维修</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
    <a href="/property_system/wx/v1.0.0/joinRepairSubmitRecord" class="submit">维修记录</a>
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
          <span class="fname">维修时间</span>
          <input class="fselect" type="text" value="" placeholder="请选择维修时间" id="selectDatetime">
        </label>
      </div>

      <div class="fgroup">
        <label class="fitem full">
          <span class="iname">维修描述</span>
          <textarea class="ftextarea" placeholder="请填写装修说明"></textarea>
        </label>
      </div>

      <div class="fgroup">
        <div class="picradios">
          <span class="iname">您要选择的类型是?</span>
          <div class="ralisbox">
            <label class="ralist">
              <img src="/property_system/images/wx/radio_pic_01.jpg" alt="">
              <input type="radio" name="service">
              <span>居家维修</span>
            </label>
            <label class="ralist">
              <img src="/property_system/images/wx/radio_pic_02.jpg" alt="">
              <input type="radio" name="service">
              <span>小区报修</span>
            </label>
            <label class="ralist">
              <img src="/property_system/images/wx/radio_pic_03.jpg" alt="">
              <input type="radio" name="service">
              <span>小区卫生</span>
            </label>
            <label class="ralist">
              <img src="/property_system/images/wx/radio_pic_04.jpg" alt="">
              <input type="radio" name="service">
              <span>小区绿化</span>
            </label>
            <label class="ralist">
              <img src="/property_system/images/wx/radio_pic_05.jpg" alt="">
              <input type="radio" name="service">
              <span>小区安全</span>
            </label>
          </div>
        </div>
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
