var storage = window.localStorage;
//商品图片id
var commodityPhotoId = "";
//0-上架,1-下架
var commodityState = 0;
//是否有商品分类(-1-没有,0-有)
var isHaveCommodityType = -1;
//0-不允许万能卡消费,1-允许万能卡消费
var is_make_wnk = 1;

//初始化数据
function initData() {
    //获取分类
    getAllType();
}

//判断字符串是否为数字
function checkRate(nubmer) {
    //判断正整数
    var re = /^\d+$/;
    if (re.test(nubmer)) {
        return true;
    }else{
        return false;
    }
}

//获取分类
function getAllType() {
    toast(2,"打开loading");
    $("#type_ul li").remove();
    $.ajax({
        url:"/property_system/wnk_business_app/v1.0.0/getAllCommodityType",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"business_id":storage["business_id"]},
        success:function(data){
            if (data.status == 0){
                toast(3,"关闭loading");
                var list = data.data;
                if (list.length <= 0){
                    toast(1,"暂无数据");
                }
                else{
                    var typeSelect = document.getElementById("type_select");
                    for (var index = 0;index < list.length; index++){
                        isHaveCommodityType = 0;
                        var obj = list[index];
                        typeSelect.options.add(new Option(obj.type_name,obj.id)); //这个兼容IE与firefox
                    }
                }
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//图片选择
function choosePhoto() {
    document.getElementById("header_file").click();
}

function chooseHeaderChangeFile() {
    toast(2,"开启loading");
    $.ajaxFileUpload({
        url : '/property_system/images/savaimageMobile.do', // 用于文件上传的服务器端请求地址
        secureuri : false, // 是否需要安全协议，一般设置为false
        fileElementId : 'header_file', // 文件上传域的ID
        dataType : 'json', // 返回值类型 一般设置为json
        type : "post",
        data:{"fileNameStr":"ajaxFile","fileId":"header_file"},
        success : function(data, status) // 服务器成功响应处理函数
        {
            if (data.error == 0){
                toast(3,"关闭loading");
                commodityPhotoId = data.url;
                document.getElementById("commodity_photo").src = data.url_location;
            }
            else{
                toast(1,data.message);
            }
        },
        error : function(data, status, e)// 服务器响应失败处理函数
        {
            toast(3,"关闭loading");
            alert(e);
        }
    });
}


//添加商品
function addCommodity() {
    if (isHaveCommodityType == -1){
        toast(0,"请先创建商品分类");
    }
    else{
        var name = document.getElementById("name").value;
        var price = document.getElementById("price").value;
        var type_id = document.getElementById("type_select").options[document.getElementById("type_select").selectedIndex].value;
        var describe = document.getElementById("describe").value;
        if (name == "" || name == undefined){
            toast(0,"请输入商品名称");
        }
        else if (price == "" || price == undefined){
            toast(0,"请输入商品价格");
        }
        else if (price <= 0){
            toast(0,"商品价格不可小于等于0");
        }
        else if (type_id == "" || type_id == undefined){
            toast(0,"请先创建商品分类");
        }
        else if (describe == "" || describe == undefined){
            toast(0,"请输入商品描述");
        }
        else if(commodityPhotoId == "" || commodityPhotoId == undefined){
            toast(0,"请上传商品图片");
        }
        else if (checkRate(price) == false){
            toast(0,"商品价格需为整数");
        }
        else{
            toast(2,"打开loading");
            $.ajax({
                url:"/property_system/wnk_business_app/v1.0.0/addCommodity",
                type:"POST",
                headers: {
                    'login_session' : storage["login_session"]
                },
                dataType : 'json',
                data:{"business_id":storage["business_id"],"name":name,"price":price,"type_id":type_id,"describe":describe,"commodityPhotoId":commodityPhotoId,"state":commodityState,"is_make_wnk":is_make_wnk},
                success:function(data){
                    toast(1,data.msg);
                    if (data.status == 0){
                        self.window.history.go(-1);
                    }
                },
            });
        }
    }
}