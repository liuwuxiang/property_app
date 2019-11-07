var table = null;

var layFrom = null;

//初始化table组件以及数据
function initTableAndData(){
	layui.use('table', function(){
	  table = layui.table;
	  
	  table.render({
	    elem: '#member_level_manager_table'
	    ,cellMinWidth: 80
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id', title: 'ID',sort: true}
	      ,{field:'name', title: '银行名称',sort:true}
	      ,{field:'code', title: '银行ID/Code',sort:true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllBank'
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

//新增银行
function addBank(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '新增银行',
  		content: '/property_system/admin/addBank',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//修改银行
function setBank(){
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table')
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
            title: '修改银行',
            content: '/property_system/admin/setBank?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//删除银行
function deleteBank(){
    var table = layui.table;
    var checkStatus = table.checkStatus('member_level_manager_table')
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
        layer.confirm('确定删除此银行？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            deleteBankAction(model.id);
        }, function(){

        });
    }
}

//删除银行网络事件
function deleteBankAction(record_id) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/deleteBankAction",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id},
        success:function(data){
            layer.close(loading_index);
            if (data.status == 0){
                parent.layer.alert(data.msg, {icon: 6});
            }
            else{
                parent.layer.alert(data.msg, {icon: 5});
            }
        },
    });
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var name = document.getElementById("name").value;
    var code = document.getElementById("code").value;

    //方法更新渲染
    table.render({
        elem: '#member_level_manager_table'
        ,url: '/property_system/admin/adminSearchBankByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort: true}
            ,{field:'name', title: '银行名称',sort:true}
            ,{field:'code', title: '银行ID/Code',sort:true}
        ]]
        ,id: 'member_level_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'name'  : name,
            'code'  : code,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}