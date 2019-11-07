<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>业主认证</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/owner_authentication.css">
		<script src="/property_system/js/wx/owner_authentication.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData()">
		<a class="tip_tag">购房电话</a>
		<div class="input_div">
			<input placeholder="请填写购房所留电话" id="buy_house_mobile"/>
		</div>
		<a class="tip_tag">购房姓名</a>
		<div class="input_div">
			<input placeholder="请填写购房人姓名" id="buy_house_name"/>
		</div>
		<a class="tip_tag">所在区域</a>
		<div class="input_choose_div" onclick="chooseAction(0)">
			<a id="provinceAndCity">省/市</a>
			<img src="/property_system/images/wx/icon_more.svg"/>
		</div>
		<a class="tip_tag">小区</a>
		<div class="input_choose_div" onclick="chooseAction(1)">
			<a id="residential_name">选择小区</a>
			<img src="/property_system/images/wx/icon_more.svg"/>
		</div>
		<a class="tip_tag">具体房号</a>
		<div class="input_choose_div" onclick="chooseAction(2)">
			<a id="buildingAndUnitAndHouse">栋号/单元号/房间号</a>
			<img src="/property_system/images/wx/icon_more.svg"/>
		</div>
		
		<input type="button" value="提交认证" class="set_button" onclick="submitAuthentication()"/>

		<!--UI插件-->
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
                        document.getElementById("provinceAndCity").innerText = storage["province_name"]+"/"+storage["city_name"];
                    }
                    if (storage["residential_name"] != undefined || storage["residential_name"] != ""){
                        document.getElementById("residential_name").innerText = storage["residential_name"];
					}
					if (storage["building_number"] != undefined && storage["building_number"] != "" && storage["unit_number"] != undefined && storage["unit_number"] != "" && storage["house_number"] != undefined && storage["house_number"] != ""){
                        document.getElementById("buildingAndUnitAndHouse").innerText = storage["building_number"] + "/" + storage["unit_number"] + "/" + storage["house_number"];
					}

                }
            });
		</script>
		<script src="/property_system/plus/js/jquery-weui.js"></script>
	</body>
</html>
