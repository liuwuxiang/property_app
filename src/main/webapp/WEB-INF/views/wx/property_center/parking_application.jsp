<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>物业中心</title>
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
    <h1>车位申请</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
    <a href="/property_system/wx/v1.0.0/joinParkingApplicationRecord" class="submit">申请记录</a>
  </div>
  <div class="h88"></div>

  <div class="pubform">
    <form>
      <div class="fgroup">
        <div class="fitem">
          <span class="fname">车牌号</span>
          <div class="minbox">
            <input class="minselect" id='selectBank' value="云D">
            <input class="ftext" type="text" placeholder="请填写车辆车牌号码">
          </div>
        </div>
        <label class="fitem">
          <span class="fname">车主姓名</span>
          <input class="ftext" type="text" placeholder="请填写车辆车主姓名">
        </label>
        <label class="fitem">
          <span class="fname">联系电话</span>
          <input class="ftext" type="number" placeholder="请填写车辆联系电话">
        </label>
      </div>

      <a href="" class="choosehouse">
        <p class="name">文化空间D栋1901</p>
        <p class="desc">张凡，187****5223</p>
      </a>

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
    // 单列选择
    $("#selectBank").picker({
      title: "请选择",
      cols: [
        {
          textAlign: 'center',
          values: ['云A', '云B', '云C', '云D']
        }
      ],
      onChange: function(p, v, dv) {
        console.log(p, v, dv);
      },
      onClose: function(p, v, d) {
        console.log("close");
      }
    });
  </script>
</body>
</html>
