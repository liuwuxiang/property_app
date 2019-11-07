<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>投诉物业</title>
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
    <h1>投诉物业</h1>
    <a href="javascript:history.go(-1)" class="back"></a>
  </div>
  <div class="h88"></div>
  <!-- 头部END -->

  <!-- 表单 -->
  <div class="pubform">
    <form>
      <div class="ftitle">被投诉物业</div>
      <div class="fgroup">     
        <label class="fitem full">
          <input class="fselect" type="text" value="文化空间" placeholder="请选择" id="selectxq">
        </label>
      </div>

      <div class="ftitle">投诉描述</div>
      <div class="fgroup">
        <label class="fitem full">
          <textarea class="ftextarea" placeholder="请填写您投诉的内容"></textarea>
        </label>
        
      </div>
      
      <div class="ftitle">上传照片</div>
      <div class="fgroup">
        <!-- 图片上传 -->
        <div class="fuploadimg">
          <p class="loadimgitem"><img src="/property_system/images/wx/img_head.jpg" alt=""><span class="delte"></span></p>
          <!-- 上传按钮，点击打开选择从相册或者拍照 -->
          <label class="floadimgbut" id="floadimgbut"></label>
        </div>
      </div>

      <div class="fhandle">
        <a href="" class="fsubmit">提交</a>
      </div>
    </form>
  </div>

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
    $("#selectxq").picker({
      title: "请选择小区",
      cols: [
        {
          textAlign: 'center',
          values: ['文化空间', '小区2', '小区3', '小区4']
        }
      ],
      onChange: function(p, v, dv) {
        console.log(p, v, dv);
      },
      onClose: function(p, v, d) {
        console.log("close");
      }
    });
    $("#selectfh").picker({
      title: "请选择楼栋",
      cols: [
        {
          textAlign: 'center',
          values: ['楼栋1', '楼栋2', '楼栋3', '楼栋4']
        }
      ],
      onChange: function(p, v, dv) {
        console.log(p, v, dv);
      },
      onClose: function(p, v, d) {
        console.log("close");
      }
    });
    $("#selectzh").picker({
      title: "请选择住户",
      cols: [
        {
          textAlign: 'center',
          values: ['住户1', '住户2', '住户3', '住户4']
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
