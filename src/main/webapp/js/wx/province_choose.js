var storage = window.localStorage;
/*
 *城市选择
 */
function cityChooseAction(province_id,name){
 	self.window.location.href = "/property_system//wx/v1.0.0/joinCityChoose?province_id="+province_id+"&province_name="+name;
}

//初始化数据
function initData() {
    toast(2,"打开loading");
    $("#content_ul li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getAllProvince",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
				var list = data.data.list;
				if (list.length <= 0){
                    toast(1,"暂无数据");
				}
				else{
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li onclick=\"cityChooseAction("+obj.province_id+",'"+obj.name+"')\">"+
                            "<a>"+obj.name+"</a>"+
                            "<img src=\"/property_system/images/wx/icon_more.svg\"/>"+
                            "</li>";
                        $("#content_ul").append(html);
                    }
				}
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}