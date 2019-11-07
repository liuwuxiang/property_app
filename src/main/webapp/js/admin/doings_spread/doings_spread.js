var storage = window.localStorage;

var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData() {
    layui.use(['table', 'laydate'], function () {
        table = layui.table;
        table.render({
            elem: '#member_level_manager_table',
            cellMinWidth: 80,
            cols: [[
                {type: 'checkbox'},
                {field: 'id',       title: 'ID',sort:true},
                {field: 'title',     title: '活动标题',sort:true},
                {field: 'store_name',      title: '商家名称',sort:true},
                {field: 'ad_type', title: '活动类型',templet:function (e) {
                        return e.ad_type == '0'?'轮播图推广':'系统消息推广';
                    },sort:true},
                {field: 'gallery_img',
                    title: '轮播活动轮播图',
                    event: 'gallery_img',
                    templet:function(e){
                    return '<img src="'+e.img_path+e.gallery_img+'">';
                    }},
                {field: 'gallery_content_img',
                    title: '轮播活动广告页',
                    event: 'gallery_content_img',
                    templet:function(e){
                        return '<img src="'+e.img_path+e.gallery_content_img+'">';
                    }},
                {field: 'system_msg', title: '系统消息推广内容',sort:true},
                {field: 'receive_type', title: '接收对象',templet:function (e) {
                        return e.receive_type == '0' ? '所有商家和用户':e.receive_type == '1'?'所有商家':e.receive_type == '2'?'所有用户':e.receive_type == '3'?'我的会员':'我的商家';
                    },sort:true},
                {field: 'verify_statue', title: '审核状态',templet:function (e) {
                    return e.verify_statue == '0' ? '待审核':e.verify_statue == '1'?'审核通过':'审核失败';
                    },sort:true},
                {field: 'activating_statue', title: '上架状态',templet : function (e) {
                        return e.activating_statue == '0' ? '上架':'下架';
                    },sort:true},
                {field: 'create_time',       title: '发布时间',sort:true},
            ]],
            page: true,
            url: '/property_system/admin/selectDoingsSpreadAll',
            method: 'post',
            response: {
                dataName: 'data',
                msgName: 'msg',
                statusName: 'status',
                statusCode: 0
            }
        });

        //监听行单击事件（单击事件为：rowDouble）
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            var src_url = '';

            if(String(obj.event) === 'gallery_img'){
                src_url += (data.img_path + data.gallery_img);
            }
            if(String(obj.event) === 'gallery_content_img'){
                src_url += (data.img_path + data.gallery_content_img);
            }

            console.log(src_url);
            var html = '' +
                '<div class="show_img">' +
                '   <img src="'+src_url+'">' +
                '</div>' +
                '';
            layer.open({
                type: 1,
                title:'查看图片',
                skin: 'layui-layer-rim', //加上边框
                // area: auto, //宽高
                content: html
            });
        });
        //时间选择器
        var laydate = layui.laydate;

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#create_time' //指定元素id
            ,btns: ['confirm'],
            type: 'datetime'//数据类型(时间)
        });

    });

    //加载form表单
    layui.use('form', function(){
        layFrom = layui.form;
        layFrom.render();
    });
}

// 根据条件模糊查询
function Search() {

    var title         = $('#title').val();
    var store_name         = $('#store_name').val();
    var ad_type  = $('#ad_type').val();
    var receive_type      = $('#receive_type').val();
    var verify_statue = $('#verify_statue').val();
    var activating_statue  = $('#activating_statue').val();
    var create_time      = $('#create_time').val();

    //方法级渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchDoingsSpread'
        ,cols: [[
            {type: 'checkbox'},
            {field: 'id',       title: 'ID',sort:true},
            {field: 'title',     title: '活动标题',sort:true},
            {field: 'store_name',      title: '商家名称',sort:true},
            {field: 'ad_type', title: '活动类型',templet:function (e) {
                    return e.ad_type == '0'?'轮播图推广':'系统消息推广';
                },sort:true},
            {field: 'gallery_img',
                title: '轮播活动轮播图',
                event: 'gallery_img',
                templet:function(e){
                    return '<img src="'+e.img_path+e.gallery_img+'">';
                }},
            {field: 'gallery_content_img',
                title: '轮播活动广告页',
                event: 'gallery_content_img',
                templet:function(e){
                    return '<img src="'+e.img_path+e.gallery_content_img+'">';
                }},
            {field: 'system_msg', title: '系统消息推广内容',sort:true},
            {field: 'receive_type', title: '接收对象',templet:function (e) {
                    return e.receive_type == '0' ? '所有商家和用户':e.receive_type == '1'?'所有商家':e.receive_type == '2'?'所有用户':e.receive_type == '3'?'我的会员':'我的商家';
                },sort:true},
            {field: 'verify_statue', title: '审核状态',templet:function (e) {
                    return e.verify_statue == '0' ? '待审核':e.verify_statue == '1'?'审核通过':'审核失败';
                },sort:true},
            {field: 'activating_statue', title: '上架状态',templet : function (e) {
                    return e.activating_statue == '0' ? '上架':'下架';
                },sort:true},
            {field: 'create_time',       title: '发布时间',sort:true},
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'title'             : title,
            'store_name'        : store_name,
            'ad_type'           : ad_type,
            'receive_type'      : receive_type,
            'verify_statue'     : verify_statue,
            'activating_statue' : activating_statue,
            'create_time'       : create_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}


/**
 * 审核通过并且上架
 */
function verifyOkAndActivating() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table');
    var data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    } else if (data[0].verify_statue == 1){
        layer.msg('活动已经审核!', {icon: 1});
    } else {
        $.ajax({
            url: "/property_system/admin/updateDoingsSpreadStatue",
            type: "POST",
            headers: {'login_session': storage["login_session"]},
            dataType: 'json',
            data: {
                "user_id"          : storage["user_id"],
                "id"               : data[0].id,
                'verify_statue'    : '1',
                'activating_statue': '0'
            },
            success: function (ret) {
                layer.open({
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function(index, layero){
                        location.reload();
                        return true;
                    }
                });
            }
        });
    }
}

/**
 * 审核失败
 */
function verifyError() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table');
    var data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    } else {
        $.ajax({
            url: "/property_system/admin/updateDoingsSpreadStatue",
            type: "POST",
            headers: {'login_session': storage["login_session"]},
            dataType: 'json',
            data: {
                "user_id"          : storage["user_id"],
                "id"               : data[0].id,
                'verify_statue'    : '2',
                'activating_statue': '1'
            },
            success: function (ret) {
                layer.open({
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function(index, layero){
                        location.reload();
                        return true;
                    }
                });
            }
        });
    }
}

/**
 * 上架
 */
function ActivatingOk() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table');
    var data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    } else {
        $.ajax({
            url: "/property_system/admin/updateDoingsSpreadStatue",
            type: "POST",
            headers: {'login_session': storage["login_session"]},
            dataType: 'json',
            data: {
                "user_id"          : storage["user_id"],
                "id"               : data[0].id,
                'verify_statue'    : '1',
                'activating_statue': '0'
            },
            success: function (ret) {
                layer.open({
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function(index, layero){
                        location.reload();
                        return true;
                    }
                });
            }
        });
    }
}

/**
 * 下架
 */
function ActivatingError() {
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table');
    var data = checkStatus.data;
    var length = data.length;
    if (length == 0) {
        layer.msg('请选择一条数据!', {icon: 5});
    }else if (length > 1) {
        layer.msg('只可选择一条数据!', {icon: 5});
    }  else {
        $.ajax({
            url: "/property_system/admin/updateDoingsSpreadStatue",
            type: "POST",
            headers: {'login_session': storage["login_session"]},
            dataType: 'json',
            data: {
                "user_id"          : storage["user_id"],
                "id"               : data[0].id,
                'activating_statue': '1'
            },
            success: function (ret) {
                layer.open({
                    content: ret.msg,
                    btn: ['确定'],
                    yes: function(index, layero){
                        location.reload();
                        return true;
                    }
                });
            }
        });
    }
}

