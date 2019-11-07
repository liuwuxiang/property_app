<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>帮助设置</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/admin/set_member_level.css">

    <link rel="stylesheet" href="${ctx}/js/wangEditor.css">
    <%--富文本编辑器--%>
    <script src="${ctx}/js/wangEditor.min.js"></script>
    <script src="${ctx}/js/admin/layui.js"></script>
    <script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
    <script src="${ctx}/js/admin/lay/modules/layer.js"></script>
    <script src="${ctx}/js/admin/help.js"></script>
</head>
<body onload="initView()">


<div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">通用积分</li>
        <li>消费积分</li>
        <li>关于我们</li>
    </ul>
    <div class="layui-tab-content">
        <!--
            作者：offline
            时间：2018-07-22
            描述：通用积分帮助说明
        -->
        <div class="layui-tab-item layui-show">
            <div class="top_div">
                <button class="layui-btn">保存</button>
            </div>
            <ul class="set_member_level_ul">
                <li>
                    <a>通用积分如何使用</a>
                    <textarea id="ty_make" style="display: none;"></textarea>
                </li>
                <li>
                    <a>通用积分如何获得</a>
                    <textarea id="ty_get" style="display: none;"></textarea>
                </li>
                <li>
                    <a>通用积分扣减规则</a>
                    <textarea id="ty_kj" style="display: none;"></textarea>
                </li>
            </ul>
        </div>

        <!--
              作者：offline
              时间：2018-07-22
              描述：消费积分帮助说明
          -->
        <div class="layui-tab-item">
            <div class="top_div">
                <button class="layui-btn">保存</button>
            </div>
            <ul class="set_member_level_ul">
                <li>
                    <a>消费积分如何使用</a>
                    <textarea id="xf_make" style="display: none;"></textarea>
                </li>
                <li>
                    <a>消费积分如何获得</a>
                    <textarea id="xf_get" style="display: none;"></textarea>
                </li>
                <li>
                    <a>消费积分扣减规则</a>
                    <textarea id="xf_kj" style="display: none;"></textarea>
                </li>
            </ul>
        </div>

        <!--
              作者：offline
              时间：2018-07-22
              描述：关于我们说明
          -->
        <div class="layui-tab-item">
            <div class="top_div">
                <button class="layui-btn">保存</button>
            </div>
            <ul class="set_member_level_ul">
                <li>
                    <a>关于我们说明</a>
                    <textarea id="about_us" style="display: none;"></textarea>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
