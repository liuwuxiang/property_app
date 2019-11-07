var table = null ;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
	layui.use(['table', 'laydate'], function(){
	  table = layui.table;
	  
	  table.render({
	    elem: '#members_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id', title: 'ID', sort: true}
	      ,{field:'name', title: '姓名', sort: true}
	      ,{field:'mobile', title: '手机号/账号', sort: true}
	      ,{field:'position', title: '职位', sort: true}
	      ,{field:'admin_types_name', title: '类别', sort: true}
	      ,{field:'join_time', title: '加入时间', sort: true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllAdmins'
          // ,where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
          ,method: 'post' //如果无需自定义HTTP类型，可不加该参数
          //	,request: {} //如果无需自定义请求参数，可不加该参数
          ,response: {
              dataName: 'data'
              ,msgName: 'msg'
              ,statusName: 'status'
              ,statusCode: 0
          } //如果无需自定义数据响应名称，可不加该参数
	  });
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            //指定元素
            elem: '#join_time',
            btns: ['confirm'],
            type: 'datetime'
        });
    });

    layui.use('form', function(){
        layFrom = layui.form;
    });
}

// 根据条件模糊查询
function Search() {
    var name                = $('#name').val();
    var mobile              = $('#mobile').val();
    var position            = $('#position').val();
    var admin_types_name    = $('#admin_types_name').val();
    var join_time           = $('#join_time').val();

    //方法级渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchAdminsByConditions'
        , cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID', sort: true}
            ,{field:'name', title: '姓名', sort: true}
            ,{field:'mobile', title: '手机号/账号', sort: true}
            ,{field:'position', title: '职位', sort: true}
            ,{field:'admin_types_name', title: '类别', sort: true}
            ,{field:'join_time', title: '加入时间', sort: true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {
            'name'            : name,
            'mobile'          : mobile,
            'position'        : position,
            'admin_types_name':admin_types_name,
            'join_time'       : join_time,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });

}

//新增管理员
function addAdmins(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '添加管理员',
  		content: '/property_system/admin/addAdmins',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//修改管理员
function setAdmins(){
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        ,data = checkStatus.data;
    var length = data.length;
    if (length == 0){
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1){
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else {
        var model = data[0];
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '修改管理员',
            content: '/property_system/admin/setAdmins?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//删除管理员
function deleteAdmins(){
    var table = layui.table;
    var checkStatus = table.checkStatus('members_manager_table')
        ,data = checkStatus.data;
    var length = data.length;
    if (length == 0){
        layer.msg('请选择一条数据!', {icon: 5});
    }
    else if (length > 1){
        layer.msg('只可选择一条数据!', {icon: 5});
    }
    else {
        var model = data[0];
        layer.confirm('确定删除此管理员？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            deleteAdminsAction(model.id);
        }, function(){

        });
    }
}

//删除管理员网络事件
function deleteAdminsAction(record_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/deleteAdminInformation",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                parent.layer.alert(data.msg, {icon: 6});
                initTableAndData();
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}