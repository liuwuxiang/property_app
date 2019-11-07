var storage = window.localStorage;

//初始化数据
function initData() {
    getSliverCoinDetail();
}

//获取银币明细
function getSliverCoinDetail() {
    toast(2,"打开loading");
    publicnull_tip("暂无数据",1);
    $("#list li").remove();
    $.ajax({
        url:"/property_system/app/v1.0.0/getSilverCoinDetail",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                var list = data.data.list;
                if (list.length <= 0){
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无数据",1);
                }
                else{
                    toast(3,"关闭Loading");
                    publicnull_tip("暂无数据",0);
                    for (var index = 0;index < list.length; index++){
                        var obj = list[index];
                        var html = "<li class=\"item\">"+
                            "<div class=\"left\">"+
                            "<span class=\"name\">"+obj.name+"</span>"+
                            "<span class=\"time\">余额 "+obj.balance+"银币</span>"+
                        "</div>"+
                        "<div class=\"right\">";
                        if (obj.income_expenditure_type == 0){
                            html = html + "<span class=\"num down\">-"+obj.transaction_amount+"</span>";
                        }
                        else{
                            html = html + "<span class=\"num up\">+"+obj.transaction_amount+"</span>";
                        }
                            html = html + "<p class=\"time\">"+obj.record_date+"</p>"+
                                "</div>"+
                                "</li>";

                        $("#list").append(html);
                    }
                }
            }
            else{
                toast(3,"关闭Loading");
                publicnull_tip(data.msg,1);
            }
        },
    });
}

/*
* 提示修改
* */
function publicnull_tip(content,state) {
    var publicnull_tip = document.getElementById("publicnull_tip");
    if (state == 0){
        publicnull_tip.style.display = "none";
    }
    else{
        publicnull_tip.style.display = "block";
    }
}