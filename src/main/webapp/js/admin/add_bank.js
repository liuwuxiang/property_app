//type=0添加，type=1修改
var type;
function initView(current_type) {
    type = current_type;
}

//保存银行信息
function saveBank() {
    if (type == 0){
        addBank();
    }
    else{
        setBank();
    }
}

//添加银行
function addBank() {
    var bank_name = document.getElementById("bank_name").value;
    var bank_code = document.getElementById("bank_code").value;
    if (bank_name == undefined || bank_name == ""){
        layer.msg('请输入银行名称!', {icon: 5});
    }
    else if (bank_code == undefined || bank_code == ""){
        layer.msg('请输入银行Code!', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/addBankAction",
            type:"POST",
            dataType : 'json',
            data:{"bank_name":bank_name,"bank_code":bank_code},
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
}

//修改银行
function setBank() {
    var record_id = document.getElementById("record_id").value;
    var bank_name = document.getElementById("bank_name").value;
    var bank_code = document.getElementById("bank_code").value;
    if (bank_name == undefined || bank_name == ""){
        layer.msg('请输入银行名称!', {icon: 5});
    }
    else if (bank_code == undefined || bank_code == ""){
        layer.msg('请输入银行Code!', {icon: 5});
    }
    else{
        var loading_index = layer.load(1);
        $.ajax({
            url:"/property_system/admin/setBankAction",
            type:"POST",
            dataType : 'json',
            data:{"record_id":record_id,"bank_name":bank_name,"bank_code":bank_code},
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
}