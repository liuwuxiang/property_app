<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>商品分类</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <!--标准mui.css-->
  <link rel="stylesheet" href="/property_system/css/wnk_business/mui.min.css">
  <link href="/property_system/css/wnk_business/type_manager.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
    <script src="/property_system/js/wnk_business/type_manager.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
  <!-- 头部STR -->
  <div class="head">
    <h1>商品分类</h1>
    <a onclick="back()" class="back"></a>
    <a id="addTypeBtn" class="add"></a>  
  </div>
  <div class="h88"></div>
  <ul class="type_ul" id="type_ul">
  	<%--<li>--%>
  		<%--<a class="type_name">分类名称</a>--%>
  		<%--<div class="button_div">--%>
  			<%--<input type="button" value="删除" class="delete_button" onclick="deleteType(0)"/>--%>
  			<%--<input type="button" value="修改" onclick="setType(0)"/>--%>
  		<%--</div>--%>
  	<%--</li>--%>
  </ul>
  
  <script src="/property_system/js/wnk_business/mui.min.js"></script>
  <script type="text/javascript" charset="utf-8">
			//mui初始化
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			document.getElementById("addTypeBtn").addEventListener('tap', function(e) {
				e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
				var btnArray = ['取消', '确定'];
				mui.prompt('', '请输入分类名称', '分类添加', btnArray, function(e) {
					if (e.index == 1) {
                        addType(e.value);
//						info.innerText = '谢谢你的评语：' + e.value;
					} else {
//						info.innerText = '你点了取消按钮';
					}
				})
			});
		</script>
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
</body>
</html>
