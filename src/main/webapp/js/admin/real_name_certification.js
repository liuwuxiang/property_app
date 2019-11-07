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
	      ,{field:'mobile', title: '电话号码',sort:true}
	      ,{field:'real_name', title: '真实姓名',sort:true}
	      ,{field:'id_card_number', title: '身份证号',sort:true}
	      ,{field:'card_effective_deadline', title: '身份证有效截止日期',sort:true}
	      ,{field:'state', title: '状态',sort:true}
	      ,{field:'submit_date_str', title: '提交日期',sort:true}
	      
	    ]]
	    ,page: true
          ,url:'/property_system/admin/getAllIdCardCertification'
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
        //时间选择器
        var laydate = layui.laydate;

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#submit_date_str', //指定元素id
            btns: ['confirm'],
            type: 'datetime'//数据类型(时间)
        });

        //执行一个laydate时间实例  执行更新渲染
        laydate.render({
            elem: '#card_effective_deadline', //指定元素id
            btns: ['confirm'],
            type: 'datetime'//数据类型(时间)
        });
    });
    //加载表单form
    layui.use('form', function(){
        layFrom = layui.form;
    });
}

//审核通过事件
function reviewPass(){
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
    else{
        var model = data[0];
        layer.confirm('确定审核通过？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            setStateNetwork(model.id,1);
        }, function(){

        });
    }
}

//审核不通过事件
function reviewNoPass(){
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
    else{
        var model = data[0];
        layer.confirm('确定审核不通过？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.closeAll('dialog');
            setStateNetwork(model.id,2);
        }, function(){

        });
    }
}

//查看实名认证信息
function lookMessage(){
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
    else{
        var model = data[0];
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title: '实名认证信息',
            content: '/property_system/admin/realNameCertificationDetail?record_id='+model.id,
            area: ['100%', '100%'],
            maxmin: true
        });
    }
}


//修改认证状态事件
function setStateNetwork(record_id,state) {
    var loading_index = layer.load(1);
    $.ajax({
        url:"/property_system/admin/setIdCardCertificationPass",
        type:"POST",
        dataType : 'json',
        data:{"record_id":record_id,"state":state},
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
    var mobile = document.getElementById("mobile").value;
    var real_name = document.getElementById("real_name").value;
    var id_card_number = document.getElementById("id_card_number").value;
    var card_effective_deadline = document.getElementById("card_effective_deadline").value;
    var state = document.getElementById("state").value;
    var submit_date_str = document.getElementById("submit_date_str").value;

    //方法更新渲染
    table.render({
        elem: '#members_manager_table'
        ,url: '/property_system/admin/adminSearchRealNameCertificationByConditions'
        ,cols: [[
            {type:'checkbox'}
            ,{field:'id', title: 'ID',sort:true}
            ,{field:'mobile', title: '电话号码',sort:true}
            ,{field:'real_name', title: '真实姓名',sort:true}
            ,{field:'id_card_number', title: '身份证号',sort:true}
            ,{field:'card_effective_deadline', title: '身份证有效截止日期',sort:true}
            ,{field:'state', title: '状态',sort:true}
            ,{field:'submit_date_str', title: '提交日期',sort:true}

        ]]
        ,id: 'members_manager_table'
        ,page: true
        , method: 'post' //如果无需自定义HTTP类型，可不加该参数
        ,where: {   //传递额外搜索参数
            'mobile'                    : mobile,
            'real_name'                 : real_name,
            'id_card_number'            : id_card_number,
            'card_effective_deadline'   : card_effective_deadline,
            'state'                     : state,
            'submit_date_str'           : submit_date_str,
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            dataName: 'data'
            , msgName: 'msg'
            , statusName: 'status'
            , statusCode: 0
        } //如果无需自定义数据响应名称，可不加该参数
    });
}