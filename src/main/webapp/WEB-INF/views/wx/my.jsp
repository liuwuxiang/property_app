<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人中心</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="/property_system/images/wx/logo.jpg" rel="shortcut icon" />
		<link href="/property_system/css/wx/bottom_navition.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
		<link rel="stylesheet" href="/property_system/css/wx/my.css">
		<link rel="stylesheet" type="text/css" href="/property_system/css/wx/app.css"/>
		<link rel="stylesheet" type="text/css" href="/property_system/css/wx/index-waimai.css"/>
		<script src="/property_system/js/wx/mui.min.js"></script>
		<script src="/property_system/js/wx/echarts-all.js"></script>
		<script src="/property_system/js/wx/my.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
		<script src="/property_system/js/wx/common.js"></script>
	</head>
	<body onload="lineChartInit('${login_session}')">
		<!--顶部视图-->
		<div class="top_div" onclick="myInformation()">
			<div class="header_img_div">
				<img src="/property_system/images/wx/img_head.jpg" id="header_img"/>
			</div>
			<div class="user_message_div">
				<!--昵称-->
				<div class="nickname_div" id="nickname_div">
					<a id="nick_name_tag">业主名称/昵称</a>
					<img src="" id="user_sex_img"/>
				</div>
				<!--推荐人-->
				<a class="recommend_name" id="recommend_name"></a>
				<!--会员等级-->
				<div class="member_level_name">
					<img src="/property_system/images/wx/icon_vip_03.png" id="member_level_icon"/>
					<a id="member_level_name">钻石会员</a>
				</div>
			</div>
			<img src="/property_system/images/wx/icon_more.svg" class="headr_arrow"/>
		</div>
		<!--中间视图-->
		<div class="between_div">
			<div class="integral_div">
				<div class="xfjf_div">
					<div class="message_div" onclick="joinConsumptionIntegral()">
						<a class="jf_balance_tag" id="consumption_balance_tag">0</a>
						<a class="jf_tag">消费积分</a>
					</div>
					<div class="line_div"></div>
				</div>
				<div class="tyjf_div">
					<div class="message_div" onclick="joinGeneralIntegral()">
						<a class="jf_balance_tag" id="general_balance_tag">0</a>
						<a class="jf_tag">通用积分</a>
					</div>
					<div class="line_div"></div>
				</div>
				<div class="card_div" onclick="joinCardBag()">
					<div class="message_div">
						<img src="/property_system/images/wx/icon_card.svg"/>
						<a class="jf_tag">卡包</a>
					</div>
				</div>
			</div>
		</div>
		<!--选项视图-->
		<div class="option_div">
			<ul>
				<li onclick="joinMemberCenter()">
					<img src="/property_system/images/wx/icon_menu_mem.svg" class="list_icon"/>
					<a class="list_title">会员中心</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title" id="li_member_level_name_tip"></a>
				</li>
				<%--<li onclick="joinMyMemberCard()">--%>
					<%--<img src="/property_system/images/wx/member_card_icon.png" class="list_icon"/>--%>
					<%--<a class="list_title">我的会员卡</a>--%>
					<%--<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>--%>
					<%--<a class="list_tip_title" id="li_member_card_name_tip"></a>--%>
				<%--</li>--%>
				<li onclick="joinMyTeam()">
					<img src="/property_system/images/wx/icon_menu_team.svg" class="list_icon"/>
					<a class="list_title">我的团队</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title" id="my_team_number">成员0位</a>
				</li>
				<li onclick="joinMyIndustry()">
					<img src="/property_system/images/wx/icon_menu_mony.svg" class="list_icon"/>
					<a class="list_title">我的产业</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title"></a>
				</li>

				<li onclick="joinMyIntegral()">
					<img src="/property_system/images/wx/integral.png" class="list_icon"/>
					<a class="list_title">我的积分</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title"></a>
				</li>

				<li onclick="joinMyWnkOrder()">
					<img src="/property_system/images/wx/my_wnk_order_icon.png" class="list_icon"/>
					<a class="list_title">我的订单</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title"></a>
				</li>



				<li onclick="joinCredit()">
					<img src="/property_system/images/wx/xinyong.png" class="list_icon"/>
					<a class="list_title">信用评级</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title"></a>
				</li>
				<li onclick="joinMessageCenter()">
					<img src="/property_system/images/wx/message.png" class="list_icon"/>
					<a class="list_title">消息中心</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title"></a>
				</li>
				<li onclick="joinSystemSetting()">
					<img src="/property_system/images/wx/icon_menu_set.svg" class="list_icon"/>
					<a class="list_title">系统设置</a>
					<img src="/property_system/images/wx/icon_more.svg" class="list_arrow"/>
					<a class="list_tip_title"></a>
				</li>
			</ul>
		</div>
		<!--收益视图-->
		<div class="profit_div">
			<div class="profit_top_div">
				<a>收益</a>
				<input type="button" value="近一周" class="profit_click_button active_profit_click_button" id="nearly_a_week" onclick="profitDateScreen(0)"/>
				<input type="button" value="近一月" class="profit_click_button" id="nearly_one_month" onclick="profitDateScreen(1)"/>
			</div>
			<div class="chart" id="lineChart"></div>
		</div>

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
                    getUserBaseInformation();
                }
            });
		</script>
		<script src="/property_system/plus/js/jquery-weui.js"></script>

	<!-- 底部菜单STR -->
	<div class="h108"></div>
	<div class="footermenu">
		<a href="/property_system/wx/v1.0.0/index" class="item home">首页</a>
		<a href="https://mp.weixin.qq.com/s/x__L4FX98tu0-cd22xkZMA" class="item card_introduction">卡片简介</a>
		<a href="/property_system/wx/v1.0.0/joinWnkMyOrderHome" class="item bus">我的订单</a>
		<%--<a href="/property_system/wx/v1.0.0/propertyCenter" class="item wy">物业中心</a>--%>
		<a href="/property_system/wx/v1.0.0/my" class="item my sel">我的</a>
	</div>
	</body>
</html>
