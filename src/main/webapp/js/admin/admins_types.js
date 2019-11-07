var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
	layui.use('table', function(){
	  table = layui.table;
	  
	  table.render({
	    elem: '#members_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id', title: 'ID', sort: true}
	      ,{field:'type_name', title: '类别名称', sort: true}
	      ,{field:'access_rights', title: '类别权限', sort: true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllAdminTypes'
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
	});
	//加载form表单
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//新增管理员类别
function addAdminsType(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '添加管理员类别',
  		content: '/property_system/admin/addAdminsTypes',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//修改管理员类别
function setAdminsType(){
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
            title: '修改管理员类别',
            content: '/property_system/admin/setAdminsTypes?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//模糊查询
function Search() {
    //找到table页面对应id的value值
    var type_name = document.getElementById("type_name").value;

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchAdminsTypeByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID', sort: true}
            ,{field:'type_name', title: '类别名称', sort: true}
            ,{field:'access_rights', title: '类别权限', sort: true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'type_name'     : type_name,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}