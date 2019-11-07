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
	      ,{field:'id', title: 'ID',sort: true}
	      ,{field:'clasp_type_name', title: '扣信类型',sort:true}
	      ,{field:'clasp_object', title: '扣信对象',sort:true}
	      ,{field:'clasp_value', title: '扣信额度',sort:true}
	      ,{field:'state', title: '状态',sort:true}
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllCreditRatingStandard'
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
    //加载表单form
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//修改扣信类型
function setCreditRating(){
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
            title: '修改扣信类型',
            content: '/property_system/admin/setCreditRating?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var clasp_type_name = document.getElementById("clasp_type_name").value;
    var clasp_object = document.getElementById("clasp_object").value;
    var clasp_value = document.getElementById("clasp_value").value;
    var state = document.getElementById("state").value;

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchCreditRatingByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort: true}
            ,{field:'clasp_type_name', title: '扣信类型',sort:true}
            ,{field:'clasp_object', title: '扣信对象',sort:true}
            ,{field:'clasp_value', title: '扣信额度',sort:true}
            ,{field:'state', title: '状态',sort:true}
        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'clasp_type_name'  : clasp_type_name,
            'clasp_object'     : clasp_object,
            'clasp_value'      : clasp_value,
            'state'            : state,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}