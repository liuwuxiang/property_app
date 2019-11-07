<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>${title}积分兑换</title>
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
  <script src="/property_system/js/wx/integral_exchange.js"></script>
  <style type="text/css">body,html{min-height:100%; }</style>
  <script src="/property_system/js/wx/hui.js"></script>
  <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body onload="initData(${type})">
  <!-- 头部END -->

  <!-- 表单STR -->
  <div class="pubform" style="margin-top: 10px;">
    <form>
      <div class="ftitle">${title}兑换(<span id="top_tip">10个${title}=1个银币</span>)</div>
      <div class="fgroup">
        <label class="fitem"  style="height: 40px;">
          <span class="fname"  style="height: 40px;line-height: 40px;">银币</span>
          <input class="fselect" type="text" id="exchange_number" placeholder="请选择兑换银币的个数" style="height: 40px;">
        </label>
        <div class="jfbar">需消耗<span id="integral_number">0</span>个${title}</div>
      </div>
      <div class="fhandle">
        <a class="fsubmit" onclick="exchangeAction()">兑换</a>
      </div>
    </form>
  </div>
  <!-- 表单END -->

  <!-- UI插件 -->
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
  <script src="/property_system/plus/js/city-picker.js"></script>
  <script>
      $(function() {
          FastClick.attach(document.body);
      });
//      $(function(){
//          $('#exchange_number').bind('input propertychange', function() {
//              inputChange($(this).val());
//          });
//
//      })
      $("#exchange_number").picker({
          title: "请选择兑换银币的个数",
          cols: [
              {
                  textAlign: 'center',
                  values: ['20', '50']
              }
          ],
          onChange: function(p, v, dv) {
              console.log(p, v, dv);
              inputChange(v);
          },
          onClose: function(p, v, d) {
              console.log("close");
          }
      });
  </script>
</body>
</html>
