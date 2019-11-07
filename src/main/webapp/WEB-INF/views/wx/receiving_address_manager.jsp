<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的收货地址</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/owner_authentication.css">
		<script src="/property_system/js/wx/receiving_address_manager.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData()">
		<a class="tip_tag">收货人</a>
		<div class="input_div">
			<input placeholder="请填写收货人姓名" id="name"/>
		</div>
		<a class="tip_tag">联系电话</a>
		<div class="input_div">
			<input placeholder="请填写收货人联系电话" id="mobile"/>
		</div>
		<a class="tip_tag">收货地址</a>
		<div class="input_choose_div" onclick="chooseAction()">
			<a id="provinAndCity" name="provinAndCity">请选择所在地区</a>
			<img src="/property_system/images/wx/icon_more.svg"/>
		</div>
		<div class="input_div" style="margin-top: 1px;">
			<input placeholder="请填写详细地址" id="detail_address"/>
		</div>
		
		
		<input type="button" value="提交信息" class="set_button" onclick="submitInformation()"/>

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
            window.addEventListener('pageshow', function(e) {
                // 通过persisted属性判断是否存在 BF Cache
                if (e.persisted) {
                    if (storage["province_name"] != undefined && storage["province_name"] != "" && storage["city_name"] != undefined || storage["city_name"] != ""){
                        document.getElementById("provinAndCity").innerText = storage["province_name"]+"/"+storage["city_name"];
                    }

                }
            });
		</script>
		<script src="/property_system/plus/js/jquery-weui.js"></script>
	</body>
</html>
