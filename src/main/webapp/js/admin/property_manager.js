var table = null;

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
	      ,{field:'id', title: 'ID',sort:true}
	      ,{field:'account', title: '主账号',sort:true}
	      ,{field:'city', title: '城市',sort:true}
	      ,{field:'residential_name', title: '物业名称',sort:true}
	      ,{field:'address', title: '详细地址',sort:true}
	      ,{field:'contact_name', title: '联系人',sort:true}
	      ,{field:'contact_mobile', title: '联系电话',sort:true}
	      ,{field:'create_time', title: '入驻时间',sort:true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllProperty'
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
            elem: '#create_time', //指定元素id
            btns: ['confirm'],
            type: 'datetime'
        });
	});

    //加载表单form
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//新增物业
function addProperty(){
	//弹出即全屏
	var index = layer.open({
  		type: 2,
  		title: '添加物业',
  		content: '/property_system/admin/addProperty',
  		area: ['100%', '100%'],
  		maxmin: true
	});
}

//修改物业
function setProperty(){
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
            title: '修改物业',
            content: '/property_system/admin/setProperty?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}

//搜索
function Search() {
    //找到table页面对应id的value值
    var account = document.getElementById("account").value;
    var city = document.getElementById("city").value;
    var residential_name = document.getElementById("residential_name").value;
    var address = document.getElementById("address").value;
    var contact_name = document.getElementById("contact_name").value;
    var contact_mobile = document.getElementById("contact_mobile").value;
    var create_time = document.getElementById("create_time").value;

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchPropertyByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort:true}
            ,{field:'account', title: '主账号',sort:true}
            ,{field:'city', title: '城市',sort:true}
            ,{field:'residential_name', title: '物业名称',sort:true}
            ,{field:'address', title: '详细地址',sort:true}
            ,{field:'contact_name', title: '联系人',sort:true}
            ,{field:'contact_mobile', title: '联系电话',sort:true}
            ,{field:'create_time', title: '入驻时间',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'account'           : account,
            'city'              : city,
            'residential_name'  : residential_name,
            'address'           : address,
            'contact_name'      : contact_name,
            'contact_mobile'    : contact_mobile,
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