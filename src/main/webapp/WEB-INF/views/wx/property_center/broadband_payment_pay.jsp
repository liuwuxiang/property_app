<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>宽带缴费</title>
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
    <h1>宽带缴费</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
  </div>
  <div class="h88"></div>



  <div class="pubform">
    <form>
      <div class="publine"></div>
      <div class="fgroup">
        <label class="fitem">
          <span class="fname">用户编号</span>
          <p class="ftext">087118082708768</p>
        </label>
        <label class="fitem">
          <span class="fname">缴费单位</span>
          <p class="finfo">中国电信股份有限公司</p>
        </label>
      </div>
      
      <div class="biginput">
        <p class="name">宽带号码</p>
        <label class="bigprice">
          <b class="bname">¥</b>
          <input type="number" class="bprice" name="" placeholder="0.00">
        </label>
      </div>

      <div class="fhandle">
        <a href="" class="fsubmit">立即缴费</a>
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
            values: ['昆明市电信宽带费', '玉溪市电信宽带费', '楚雄州电信宽带费', '大理市电信宽带费']
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
