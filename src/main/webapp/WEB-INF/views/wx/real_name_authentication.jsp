<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>实名认证</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/real_name_authentication.css">
		<script src="/property_system/js/wx/real_name_authentication.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
	</head>
	<body onload="initData()">
		<div class="content_div">
			<ul>
				<%--<li onclick="joinNextView(0)">--%>
					<%--<a class="li_title">业主认证</a>--%>
					<%--<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>--%>
					<%--<a class="li_content" id="owner_authrntication">未认证</a>--%>
				<%--</li>--%>
				<%--<li onclick="joinNextView(1)">--%>
					<%--<a class="li_title">车主认证</a>--%>
					<%--<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>--%>
					<%--<a class="li_content" id="card_authrntication">未认证</a>--%>
				<%--</li>--%>
				<li onclick="joinNextView(2)">
					<a class="li_title">身份证认证</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="id_card_authrntication">未认证</a>
				</li>
				<li onclick="joinNextView(3)">
					<a class="li_title">银行卡信息</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content"></a>
				</li>
			</ul>
	</div>

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
