<%--
  Created by IntelliJ IDEA.
  User: 杨新杰
  Date: 2018/9/28
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>积分商城-商品新增</title>

    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">
    <link rel="stylesheet" href="${ctx}/css/admin/integral/integral_add.css">
    <style>
        .show{
            text-align: left;
            width: 300px;
        }
        .layui-form-label{
            width: 100px;
        }
    </style>
</head>
<body onload="iniBusinessLegalizeData()">

<div style="margin: 5% 20%" >

    <form id="integral-type-form" class="layui-form" onsubmit="return false;">
        <div class="layui-form-item">
            <label class="layui-form-label">ID:</label>
            <div class="layui-input-inline">
                <label id="legalize_id" class="layui-form-label show" style="">${business_id}</label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商家ID:</label>
            <div class="layui-input-inline">
                <label id="business_id" class="layui-form-label show"></label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">店铺名称:</label>
            <div class="layui-input-inline">
                <label id="store_name" class="layui-form-label show"></label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">法人联系电话:</label>
            <div class="layui-input-inline">
                <label id="phone" class="layui-form-label show"></label>
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">商家分类:</label>
            <div class="layui-input-inline">
                <label id="business_type_str" class="layui-form-label show"></label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">申请时间:</label>
            <div class="layui-input-inline">
                <label id="start_time" class="layui-form-label show"></label>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">申请状态:</label>
            <div class="layui-input-inline">
                <label id="legalize_status" class="layui-form-label show"></label>
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">法人身份证正面:</label>
            <img id="id_card_facade_img" src="#" />
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">法人身份证背面:</label>
            <img id="id_card_rear_img" src="#" />
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">营业执照:</label>
            <img id="business_license_img" src="#"/>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">许可证:</label>
            <img id="license_img" src="#"/>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn"                   type="button" onclick="updateBusinessLegalize('1','');" >审核通过</button>
                <button class="layui-btn layui-btn-primary" type="button" onclick="updateBusinessNoLegalize();"     >审核拒绝</button>
            </div>
        </div>
    </form>
</div>

</body>
<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/wx/toast.js"></script>
<script src="${ctx}/js/wx/hui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/admin/legalize_info.js"></script>
</html>
