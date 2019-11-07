<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>生活缴费</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" href="/property_system/css/wx/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="/property_system/css/wx/app.css"/>
		<link rel="stylesheet" type="text/css" href="/property_system/css/wx/index-waimai.css"/>
		<link rel="stylesheet" type="text/css" href="/property_system/css/wx/living_payment.css"/>
		<script src="/property_system/js/wx/mui.min.js"></script>
		<script src="/property_system/js/wx/living_payment.js"></script>
		<script src="/property_system/js/wx/hui.js"></script>
		<link href="/property_system/css/wx/hui.css" rel="stylesheet" type="text/css" />
		
	</head>

	<body>
		<div class="mui-content">
		<!--
            	作者：offline
            	时间：2018-03-01
            	描述：分类按钮
            -->
            <ul class="course_type">
            		<li onclick="billChoose(0)">
            			<img src="/property_system/images/wx/icon_2/shuifei.png"/>
            			<a>水费</a>
            		</li>
            		<li onclick="billChoose(1)">
            			<img src="/property_system/images/wx/icon_2/dianfei.png"/>
            			<a>电费</a>
            		</li>
            		<li onclick="billChoose(2)">
            			<img src="/property_system/images/wx/icon_2/tianranqi.png"/>
            			<a>天然气</a>
            		</li>
            		<li onclick="billChoose(3)">
            			<img src="/property_system/images/wx/icon_2/dianshi.png"/>
            			<a>电视</a>
            		</li>
            		<li onclick="billChoose(4)">
            			<img src="/property_system/images/wx/icon_2/kuandai.png"/>
            			<a>宽带</a>
            		</li>
            		<li onclick="billChoose(5)">
            			<img src="/property_system/images/wx/icon_2/wuyefei.png"/>
            			<a>物业费</a>
            		</li>
            </ul>
            
            <div class="line_div"></div>
            <div class="bill_div">
            		<a class="bill_name" id="bill_name">水费账单</a>
            		<ul class="bill_ul">
            			<li>
            				<a class="bill_li_name">2018年05月账单</a>
            				<a class="bill_li_amount">￥15</a>
            				<div style="clear: both;"></div>
            				<input type="button" value="缴费"/>
            			</li>
            			<li>
            				<a class="bill_li_name">2018年04月账单</a>
            				<a class="bill_li_amount">￥15</a>
            				<div style="clear: both;"></div>
            				<input type="button" value="缴费"/>
            			</li>
            			<li>
            				<a class="bill_li_name">2018年03月账单</a>
            				<a class="bill_li_amount">￥15</a>
            				<div style="clear: both;"></div>
            			</li>
            			<li>
            				<a class="bill_li_name">2018年02月账单</a>
            				<a class="bill_li_amount">￥15</a>
            				<div style="clear: both;"></div>
            			</li>
            			<li>
            				<a class="bill_li_name">2018年01月账单</a>
            				<a class="bill_li_amount">￥15</a>
            				<div style="clear: both;"></div>
            			</li>
            		</ul>
            </div>
		</div>
	</body>
</html>