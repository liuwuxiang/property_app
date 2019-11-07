//数据初始化
function iniData() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/aboutUsData",
        type:"POST",
        dataType : 'json',
        data:{},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭Loading");
                var content = data.data.content;
                $("#contentInsert").html(content);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}