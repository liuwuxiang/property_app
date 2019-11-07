//审核通过事件
function reviewPass(){
	layer.confirm('确定审核通过？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
	  layer.closeAll('dialog');
        setStateNetwork(1);
	}, function(){
	  
	});
}

//审核不通过事件
function reviewNoPass(){
	layer.confirm('确定审核不通过？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
	  layer.closeAll('dialog');
        setStateNetwork(2);
	}, function(){
	  
	});
}

//修改认证状态事件
function setStateNetwork(state) {
	var record_id = document.getElementById("record_id").value;
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