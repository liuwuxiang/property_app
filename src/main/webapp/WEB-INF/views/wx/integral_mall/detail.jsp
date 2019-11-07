<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/28
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
    <meta charset="UTF-8">
    <title>积分商城-详情页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/mui.min.css" />
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/detail.css" />
</head>
<body>

<input type="hidden" value="${id}" id="id">


<div class="mui-col-sm-12 mui-col-xs-12">
    <!--商品描述-->
    <div class="container-main">
        <!--头部图片-->
        <div class="detail-header">
            <img src="" />
        </div>

        <!--商品简单描述-->
        <div class="product-infos">
            <p class="product-name"><!-- 商品名称 --></p>
            <div>
                <span class="text-highlight"><!-- 积分 --></span> 积分
            </div>
        </div>

        <!--商家-->
        <div class="shop-mod">
            <div class="shop-meta ">
                <span class="meta-name">商家</span>
                <div class="meta-body"><!-- 商家名称 --></div>
            </div>
        </div>

        <div class="content">
            <!--具体描述-->
            <div class="desc-main">
                <div class="rich-txt">
                    <!--
                    <p><b>商品介绍</b>：</p>
                    <p>联通500M流量，全国联通用户通用，<span style="color: rgb(227, 55, 55);">仅限联通手机号用户兑换</span></p>
                    <p>积分商城兑换后到滴滴出行APP流量中心兑换充值即可到账绑定手机号。</p>
                    <p><br></p>
                    <p><b>使用范围</b>：</p>
                    <p>全国联通手机号用户</p>
                    <p><br></p>
                    <p><b>兑换查看</b>：</p>
                    <p>兑换后流量充值查看（2种查看方式）:<br></p>
                    <p>1）立即兑换-确认兑换-查看详情-点开兑换流量页面-流量中心-</p>
                    <p>2）页面关闭后流量充值查看:</p>
                    <p>滴滴出行APP-个人中心(左上角小头像）-底部流量中心-点击<<b></b></p>
                    <p>具体充值操作图解如下：</p>
                    <p><span style="color: rgb(226, 139, 65);">注：如果</span></p>
                    <p><br></p>
                    <p><b>注意事项</b>：</p>
                    <p>仅限联通手机号用户参与，移动、电信及虚拟运营商用户暂不能参与本次活动；</p>
                    <p>每个用户每天限兑换一次。</p> -->

                </div>
            </div>

            <div class="important-tip">
                <span class="hd">重要说明</span>
                <div class="bd"> 商品兑换流程请仔细参照商品详情页的“兑换流程”、“注意事项”与“使用时间”，除商品本身不能正常兑换外，商品一经兑换，一律不退还积分。（如商品过期、兑换流程操作失误、仅限新用户兑换） </div>
            </div>

        </div>

    </div>

    <div class="container-footer">
        <!-- 兑换 -->
        <div id="pay-bar" class="pay-bar">
            <div class="pay-bar-cell1">
                <div class="pay-btn js-pay disabled">积分不足</div>
            </div>
        </div>

    </div>

</div>
</body>
<script src="${ctx}/js/wx/integral_mall/mui.min.js"></script>
<script src="${ctx}/js/wx/jquery.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/wx/integral_mall/detail.js"></script>
<script>
    mui.init();
</script>
</html>