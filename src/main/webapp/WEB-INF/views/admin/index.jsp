<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>物业系统后台</title>
		<script src="/property_system/js/admin/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="/property_system/css/admin/layui.css">
		<link rel="stylesheet" href="/property_system/css/admin/layer/modules/layer/default/layer.css">
		<script src="/property_system/js/admin/layui.js"></script>
		<script src="/property_system/js/admin/lay/modules/jquery.js"></script>
		<script src="/property_system/js/admin/lay/modules/layer.js"></script>
		<script src="/property_system/js/admin/index.js"></script>


		<style>
			.right_tip {
				margin-right: 25px;
				margin-top: 10px;
				margin-bottom: 10px;

			}
			.right_tip > ul {
				margin-left: 20px;
			}

			.right_tip > ul > li {
				margin-top: 5px;
				list-style:decimal outside;
			}
		</style>


	</head>
<body class="layui-layout-body" onload="getMenus()">
	<div class="layui-layout layui-layout-admin">
	  <div class="layui-header">
	    <div class="layui-logo">物业系统后台</div>
	    <ul class="layui-nav layui-layout-right">
	      <li class="layui-nav-item">
	        <a href="javascript:;">
	          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
	          ${name}
	        </a>
	        <dl class="layui-nav-child">
	          <dd><a onclick="setAdminInformation()">基本资料</a></dd>
	          <dd><a onclick="setAdminPWD()">安全设置</a></dd>
	        </dl>
	      </li>
	      <li class="layui-nav-item"><a onclick="exitLogin()">退出</a></li>
	    </ul>
	  </div>


	  <div class="layui-side layui-bg-black">
	    <div class="layui-side-scroll">
	      <ul class="layui-nav layui-nav-tree" id="admins_menus"  lay-filter="test">
	        <%--<li class="layui-nav-item">--%>
	          <%--<a class="" href="javascript:;">会员管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/memberLevelManager" target="option">会员等级管理</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/memberManager" target="option">会员管理</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">认证审核</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/owerCertificationManager" target="option">业主认证</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/cardCertificationManager" target="option">车主认证</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/realNameCertificationManager" target="option">身份证认证</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">信用评级</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/creditRatingManager" target="option">信用评级</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">银行管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/bankManager" target="option">银行管理</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">城市管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	          	<%--<dd><a href="/property_system/admin/provinceManager" target="option">省份管理</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/cityManager" target="option">城市管理</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">物业管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/propertyManager" target="option">物业管理</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">消息中心</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/messageCenter" target="option">消息中心</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">建议反馈</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/suggestionFeedbackManager" target="option">建议反馈</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">财务管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/rechargeOrdersManager" target="option">充值订单</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/soliverCoinExchangeOrders" target="option">银币兑换订单</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/withdrawOrdersManager" target="option">提现订单</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">统计管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/statistics" target="option">统计管理</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">系统设置</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/integralSetting" target="option">积分设置</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/withdrawSetting" target="option">提现设置</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/help" target="option">帮助说明</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	        <%--<li class="layui-nav-item">--%>
	          <%--<a href="javascript:;">管理员管理</a>--%>
	          <%--<dl class="layui-nav-child">--%>
	            <%--<dd><a href="/property_system/admin/adminsTypes" target="option">管理员类别管理</a></dd>--%>
	            <%--<dd><a href="/property_system/admin/adminsManager" target="option">管理员管理</a></dd>--%>
	          <%--</dl>--%>
	        <%--</li>--%>
	      </ul>
	    </div>
	  </div>
	  
	  <div class="layui-body">
	    <iframe id="option" name="option" src="/property_system/admin/statistics" style="overflow: scroll;" scrolling="yes" frameborder="no" width="100%" height="100%"></iframe>
	  </div>
	  
	  <div class="layui-footer">
	    <!-- 底部固定区域 -->
	    © 辰蓝科技 - 2018
	  </div>
	</div>
	
	<script>
	//JavaScript代码区域
	layui.use('element', function(){
	  var element = layui.element;
	  
	});
	</script>
</body>
</html>
