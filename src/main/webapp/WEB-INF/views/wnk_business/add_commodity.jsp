<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Language" content="zh-CN">
  <title>添加商品</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="copyright" content="" />
  <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <!--标准mui.css-->
  <link rel="stylesheet" href="/property_system/css/wnk_business/mui.min.css">
  <link href="/property_system/css/wnk_business/add_commodity.css" rel="stylesheet" type="text/css" />
  <link href="/property_system/css/wnk_business/common.css" rel="stylesheet" type="text/css" />
  <%--<script src="/property_system/js/wnk_business/jquery-1.9.1.min.js"></script>--%>
    <script src="/property_system/js/wnk_business/jquery-1.10.2.min.js"></script>
    <script src="/property_system/js/wnk_business/add_commodity.js"></script>
    <script src="/property_system/js/wnk_business/ajaxfileupload.js"></script>
  <script src="/property_system/js/wnk_business/common.js"></script>
  <script src="/property_system/js/wnk_business/general_lib.js"></script>
    <script src="/property_system/js/wx/hui.js"></script>
    <link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
</head>
<body class="bgw" onload="initData()">
<input type="file" accept="image/*" style="display: none;" name="ajaxFile" id="header_file" onchange="chooseHeaderChangeFile()"/>
  <!-- 头部STR -->
  <div class="head">
    <h1>添加商品</h1>
    <a onclick="back()" class="back"></a>
    <a onclick="addCommodity()" class="submit">保存</a>
  </div>
  <div class="h88"></div>
  <ul class="commodity_information_ul">
  	<li>
  		<a class="value_name_tip">商品名称</a>
  		<input type="text" value="" placeholder="请输入商品名称" class="text_input" id="name"/>
  	</li>
  	<li>
  		<a class="value_name_tip">商品价格</a>
  		<input type="number" value="" placeholder="请输入商品价格(整数)" class="text_input" id="price"/>
  	</li>
    <li>
        <a class="value_name_tip">商品分类</a>
        <select id="type_select">
            <%--<option value="1">1</option>--%>
        </select>
    </li>
  	<li>
  		<a class="value_name_tip">商品描述</a>
  		<textarea class="text_textarea" placeholder="请输入商品描述" id="describe"></textarea>
  	</li>
  	<li>
  		<a class="value_name_tip commodity_line_tag">是否上架</a>
  		<a class="line_commodity_state" id="line_commodity_state">(上架)</a>
  		<div class="mui-switch mui-active is_line_switch mui_switch_line">
			<div class="mui-switch-handle"></div>
		</div>
  	</li>
      <li>
          <a class="value_name_tip commodity_line_tag">允许万能卡消费</a>
          <a class="line_commodity_state" id="is_make_wnk">(允许)</a>
          <div class="mui-switch mui-active is_line_switch mui_switch_wnk">
              <div class="mui-switch-handle"></div>
          </div>
      </li>
  	<li>
  		<a class="value_name_tip photo_choose_tag">商品图片</a>
        <label class="choose_photo_button" onclick="choosePhoto()">选择图片</label>
  		<%--<input type="button" value="选择图片" class="choose_photo_button" onclick="choosePhoto()"/>--%>
  		<div style="clear: both;"></div>
  		<img src="" class="commodity_photo" id="commodity_photo"/>
  	</li>
  </ul>

<script src="/property_system/js/wnk_business/mui.min.js"></script>
<script>
	mui.init({
		swipeBack:true //启用右滑关闭功能
	});
	mui('.mui_switch_line').each(function() { //循环所有toggle
		/**
		* toggle 事件监听
		*/
		this.addEventListener('toggle', function(event) {
			
		//event.detail.isActive 可直接获取当前状态
		document.getElementById("line_commodity_state").innerHTML = event.detail.isActive?"(上架)":"(下架)";
		commodityState = event.detail.isActive?0:1;
		});
	});
    mui('.mui_switch_wnk').each(function() { //循环所有toggle
        /**
         * toggle 事件监听
         */
        this.addEventListener('toggle', function(event) {

            //event.detail.isActive 可直接获取当前状态
            document.getElementById("is_make_wnk").innerHTML = event.detail.isActive?"(允许)":"(不允许)";
            is_make_wnk = event.detail.isActive?1:0;
        });
    });
</script>

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

</body>
</html>
