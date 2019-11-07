<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
		<link rel="stylesheet" href="/property_system/css/wx/my_information.css">
		<script src="/property_system/js/wx/mui.min.js"></script>
		<script src="/property_system/js/wx/jquery-1.10.2.min.js"></script>
		<script src="/property_system/js/wx/my_information.js"></script>
		<script src="/property_system/js/wx/ajaxfileupload.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
		<link href="/property_system/css/wx/common.css" rel="stylesheet" type="text/css" />
		<script src="/property_system/js/wx/common.js"></script>
	</head>
	<body onload="initData()">

		<input type="file" accept="image/*" style="display: none;" name="ajaxFile" id="header_file" onchange="chooseHeaderChangeFile()"/>

		<div class="content_div">
			<ul>
				<li class="header_li" id="header_li" onclick="headerChoose()">
					<a class="header_tag">头像</a>
					<img src="/property_system/images/wx/icon_more.svg" class="header_arrow"/>
					<img src="" id="header_img" class="header_img"/>
				</li>
				<li onclick="setNickName()">
					<a class="li_title">昵称</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="nick_name">未设置</a>
				</li>
				<li onclick="setMobileCheckCen(0)">
					<a class="li_title">手机号</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="mobile">未设置</a>
				</li>
				<li onclick="setMobileCheckCen(1)">
					<a class="li_title">邮箱</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="email">未设置</a>
				</li>
				<li onclick="setSex()">
					<a class="li_title">性别</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="sex">保密</a>
				</li>
				<li id="ewm">
					<a class="li_title">我的二维码</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<img src="/property_system/images/wx/icon_ewm.svg" class="li_img"/>
				</li>
				<li onclick="joinRealNameAuthentication()">
					<a class="li_title">实名认证</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content"></a>
				</li>
				<li onclick="joinSecurityCenter()">
					<a class="li_title">安全中心</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content"></a>
				</li>
				<li onclick="joinNextView(0)">
					<a class="li_title">业主认证</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="owner_authrntication">未认证</a>
				</li>
				<li onclick="joinNextView(1)">
					<a class="li_title">车主认证</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content" id="card_authrntication">未认证</a>
				</li>
				<li onclick="joinReceivingAddress()">
					<a class="li_title">收货地址</a>
					<img src="/property_system/images/wx/icon_more.svg" class="li_arrow"/>
					<a class="li_content"></a>
				</li>
			</ul>
		</div>
		<!--号码修改验证-->
		<div id="mobile_set_check_div" class="box mui-popover mui-popover-action mui-popover-bottom mobile_set_check_div">
			<div class="number_set_duv">
				<a class="check_title_tag" id="check_title_tag"></a>
				<a class="check_content_tag" id="check_content_tag"></a>
				<div class="code_input_div">
					<input type="number" placeholder="请填写验证码" id="check_code"/>
					<input type="button" value="获取验证码" onclick="getCode()"/>
				</div>
				<div class="check_button_div">
					<input type="button" value="取消" class="close_button" onclick="closeMobileCheckCen()"/>
					<input type="button" value="确定" class="submit_button" onclick="numberCheckConfirmAction()"/>
				</div>
			</div>
		</div>
		
		<!--我的二维码-->
		<%--<div id="my_qrcode_div" class="box mui-popover mui-popover-action mui-popover-bottom mobile_set_check_div">--%>
			<%--<div class="wc_qrcode_div">--%>
				<%--<div class="qrcode_div">--%>
					<%--<img src="" class="qrcode_header" id="qrcode_header"/>--%>
					<%--<div class="qrcode_nick_name_div">--%>
						<%--<a id="qrcode_nick_name"></a>--%>
						<%--<img src="" id="qrcode_sex"/>--%>
					<%--</div>--%>
					<%--<a class="qrcode_mobile" id="qrcode_mobile"></a>--%>
					<%--<div class="qrcode_photo_div">--%>
						<%--<img src="" id="qrcode_photo" class="qrcode_photo"/>--%>
					<%--</div>--%>
					<%--<a class="qrcode_tip">扫一扫二维码加入我们</a>--%>
				<%--</div>--%>
				<%--<img src="/property_system/images/wx/close.png" class="qrcode_close_button" onclick="closeQrcodeDiv()"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<!-- 二维码弹出 -->
		<div class="ewmshow" id="ewmshow">
			<div class="wrap">
				<img src="" id="qrcode_header_img" alt="" class="img">
				<span class="name boy" id="qrcode_nick_name"></span><!-- 女生替换class boy为girl  -->
				<p class="tel" id="qrcode_mobile"></p>
				<img src="/property_system/images/wx/ewm.png" id="qrcode_photo_url" alt="" class="ewm">
				<span class="desc">扫一扫二维码加入我们</span><br/>
				<span class="desc">长按二维码分享</span>
			</div>
			<%--<button style="width: 100px;height: 35px;margin-top: 10px;background: #2993F8;color:white;border:0;border-radius: 8px;">分享</button>--%>
			<a href="javascript:;" class="close"></a>
		</div>
		<!-- UI插件 -->
		<link rel="stylesheet" href="/property_system/plus/lib/weui.min.css">
		<link rel="stylesheet" href="/property_system/plus/css/jquery-weui.css">
		<%--<script src="/property_system/plus/lib/jquery-2.1.4.js"></script>--%>
		<script src="/property_system/plus/lib/fastclick.js"></script>
		<script src="/property_system/js/wx/toast.js"></script>
		<script>
            // 显示二维码
            selShow("ewm","ewmshow");
		</script>
		<script>
            $(function() {
                FastClick.attach(document.body);
            });
		</script>
		<script src="/property_system/plus/js/jquery-weui.js"></script>
	</body>
</html>
