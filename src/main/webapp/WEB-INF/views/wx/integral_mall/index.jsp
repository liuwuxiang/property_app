<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/27
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/mui.min.css"/>
    <link rel="stylesheet" href="${ctx}/css/wx/integral_mall/index.css"/>
    <title>积分商城-首页</title>
</head>
<body>

<div class="mui-content">

    <div class="mui-card mui-col-sm-12 mui-col-xs-12">
        <!--名称和积分-->
        <div class="mui-card-header mui-col-sm-12 mui-col-xs-12">

            <div class="mui-card-header mui-card-media head-left mui-col-sm-6 mui-col-xs-6 ">
                <img src="" id='header' onerror="this.src='${ctx}/images/wx/img_head.jpg'"/>
                <div class="mui-media-body">
                    <span class="head-font-name" id="nick_name">道一哥哥</span>
                    <span class="head-font-level">
							<img id="level_icon" src="${ctx}/images/wx/integral_mall/level.jpg"/>
							<span id="level_name">白金会员</span>
                    </span>
                </div>
            </div>

            <div class="head-right mui-col-sm-6 mui-col-xs-6">
                <a href="javascript:void(0);">
                    <span id="user_integral">694</span>积分
                    <span class="mui-icon mui-icon-arrowright" style="font-size: 0.9rem;"></span>
                </a>
            </div>

        </div>
        <!--轮播图片-->
        <div class="mui-card-content mui-col-sm-12 mui-col-xs-12">
            <div class="mui-slider mui-col-sm-12 mui-col-xs-12">
                <div class="mui-slider-group mui-col-sm-12 mui-col-xs-12">
                    <div class="mui-slider-item">
                        <a href="#"><img src="${ctx}/images/wx/integral_mall/lunbo.jpg"/></a>
                    </div>

                    <div class="mui-slider-item mui-col-sm-12 mui-col-xs-12">
                        <a href="#"><img src="${ctx}/images/wx/integral_mall/lunbo.jpg"/></a>
                    </div>
                </div>
            </div>

        </div>
        <!--商品分类列表-->
        <div class="mui-card-footer mui-col-sm-12 mui-col-xs-12">
            <ul id="header-list" class="nav clearfix mui-col-sm-12 mui-col-xs-12">
                <!--
                <li class="mui-col-sm-2 mui-col-xs-2">
                    <a href="category.html?isNull=1">
                        <span class="media" style="background-image: url(${ctx}/images/wx/integral_mall/liebiao.jpg);"></span>
                        <span class="txt">出行券</span>
                    </a>
                </li>
                -->
            </ul>
        </div>
    </div>

    <div class="mui-card mui-col-sm-12 mui-col-xs-12">
        <!--头部显示-->
        <div class="mui-card-header mui-col-sm-12 mui-col-xs-12">推荐专区</div>
        <!--商品列表-->
        <div class="mui-card-content mui-col-sm-12 mui-col-xs-12">
            <section id="section" class="mui-col-sm-12 mui-col-xs-12" style="display: block;">
                <div class="row mui-col-sm-12 mui-col-xs-12">
                    <ul id="goods-ul" class="mui-col-sm-12 mui-col-xs-12">
                        <!--
                        <li class="div-li">
                               <a href="detail.html" class="goods mui-col-sm-4 mui-col-xs-4">
                                   <div class="media weizhi">
                                       <img src="${ctx}/images/wx/integral_mall/huodong.jpg" alt="500M全国联通流量">
                                   </div>
                                   <div class="media info"><span class="type"> 中国联通 </span>
                                       <p class="name">500M全国联通流量</p>
                                       <span class="customer-level">  </span>
                                       <p class="customer-price">
                                           <span class="text-highlight">1199</span>
                                           <span>积分</span>
                                       </p>
                                   </div>
                               </a>
                        </li>
                        -->
                    </ul>

                </div>

            </section>
        </div>
    </div>

</div>

</body>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/mui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/hui.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/toast.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/main.js"></script>
<script type="text/javascript" src="${ctx}/js/wx/integral_mall/index.js"></script>
</html>
