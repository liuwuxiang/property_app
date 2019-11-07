<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>积分商城-订单管理</title>
    <link rel="stylesheet" href="${ctx}/css/admin/layui.css">
    <link rel="stylesheet" href="${ctx}/css/admin/layer/modules/layer/default/layer.css">
</head>
<body>

<div style="margin-top: 10px">
    <form class="layui-form" id="integral-order-edit">
        <input type="hidden" id="id" name="id" value="${id}" >
        <div class="layui-form-item">
            <label class="layui-form-label">商品分类</label>
            <div class="layui-input-inline">
                <select name="express_name" lay-verify="required">
                    <option value=""></option>
                    <option value="中国邮政">中国邮政</option>
                    <option value="中通快递">中通快递</option>
                    <option value="圆通快递">圆通快递</option>
                    <option value="申通快递">申通快递</option>
                    <option value="百世快递">百世快递</option>
                    <option value="顺丰快递">顺丰快递</option>
                    <option value="韵达快递">韵达快递</option>
                    <option value="天天快递">天天快递</option>
                    <option value="德邦快递">德邦快递</option>
                    <option value="宅急送"  >宅急送</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">快递单号</label>
            <div class="layui-input-inline">
                <input type="text" name="express_id" required  lay-verify="required" placeholder="请输入快递单号" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

</table>
</body>
<script src="${ctx}/js/admin/jquery-1.9.1.min.js"></script>
<script src="${ctx}/js/admin/layui.js"></script>
<script src="${ctx}/js/admin/lay/modules/jquery.js"></script>
<script src="${ctx}/js/wx/integral_mall/main.js"></script>
<script src="${ctx}/js/admin/lay/modules/layer.js"></script>
<script src="${ctx}/js/admin/lay/modules/layedit.js"></script>
<script src="${ctx}/js/admin/integralOrder_edit.js"></script>
</html>