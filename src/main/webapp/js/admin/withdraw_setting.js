
var lay_from = null;

//视图初始化
function initView(){


    layui.use('form', function () {
        lay_from = layui.form;
        lay_from.on('checkbox(is_any_time)', function (data) {
            var check_box = document.getElementById("is_any_time");
            if (check_box.checked) {
                document.getElementById("allow_withdraw_time_li_start").style.display = "none";
                document.getElementById("allow_withdraw_time_li_end").style.display = "none";
            }
            else {
                document.getElementById("allow_withdraw_time_li_start").style.display = "block";
                document.getElementById("allow_withdraw_time_li_end").style.display = "block";
            }
        });

        selectWithdrawSetting();

        if (parseInt(document.getElementById('withdraw_type').value) === 0) {
            document.getElementById('min_number_li').style.display = 'inline';
            document.getElementById('get_time_li').style.display = 'inline';
        }

    });
}

/**
 * 查询已经设置好的提现条件
 */
function selectWithdrawSetting() {
    // 发送请求
    $.ajax({
        url: "/property_system/admin/adminSelectWithdrawSetting",
        type: "POST",
        dataType: 'json',
        data: {
            withdraw_type : document.getElementById("withdraw_type").value
        },
        success: function (data) {
            console.log(data);
            if (parseInt(data.status) === 0 && data.data != null || data.data !== undefined){
                document.getElementById("service_charge_proportion").value = data.data.service_charge_proportion;
                document.getElementById("withdraw_proportion").value = data.data.withdraw_proportion;
                document.getElementById("min_number").value = data.data.min_number;
                document.getElementById("get_time").value = parseInt(data.data.get_time) === -1 ? '' : data.data.get_time;
                if (parseInt(data.data.is_any_time) === 1){
                    document.getElementById("is_any_time").checked = true;
                    document.getElementById("allow_withdraw_time_li_start").style.display = "none";
				}
                // 开始时间
                document.getElementById("withdraw_start_time").value = data.data.withdraw_start_time;
                document.getElementById("withdraw_end_time").value   = data.data.withdraw_end_time;
			}
			lay_from.render();
        }
    });
}

/**
 * 保存或新增提现条件
 * @constructor
 */
function UpdateWithdraw(){
	// 获取参数
	// 1.设置类型
	var withdraw_type = document.getElementById("withdraw_type").value;
	// 2.提现手续费
	var service_charge_proportion = document.getElementById("service_charge_proportion").value;
	// 3.是否限制时间
	var is_any_time = document.getElementById("is_any_time").checked ? 1 : 0;
	// 开始时间
	var withdraw_start_time =  document.getElementById("withdraw_start_time").value;
    var withdraw_end_time =  document.getElementById("withdraw_end_time").value;
    // 兑换比例
    var withdraw_proportion = document.getElementById("withdraw_proportion").value;
    // 最低提现数量
    var min_number = document.getElementById('min_number').value;
    // 到账时间
    var get_time = document.getElementById('get_time').value;


    // 参数检查
	if (service_charge_proportion === undefined || String(service_charge_proportion) === ''){
        layer.msg('请输入提现手续费比例!', {icon: 5});
        return;
	}
    if (parseInt(is_any_time) === 0){
        if (withdraw_start_time === undefined || String(withdraw_start_time) === ''){
            layer.msg('请输入开始提现时间段的日期!', {icon: 5});
            return;
        }
        if (withdraw_end_time === undefined || String(withdraw_end_time) === ''){
            layer.msg('请输入结束提现时间段的日期!', {icon: 5});
            return;
        }

        // 比较  结束日期不能大于开始日期
        if (parseInt(withdraw_end_time) <= parseInt(withdraw_start_time)){
            layer.msg('结束日期不能小于或等于开始日期!', {icon: 5});
            return;
		}

    } else {
        withdraw_start_time = 1;
        withdraw_end_time   = 1;
	}

	// 如果是商家则要检测是否输入最小限制和提现提现到账时间
	if (parseInt(withdraw_type) === 0){
        if (min_number === undefined || String(min_number) === ''){
            layer.msg('请输入最低提现数量!', {icon: 5});
            return;
        }
        if (get_time === undefined || String(get_time) === ''){
            layer.msg('请输入到账时间!', {icon: 5});
            return;
        }
    } else {
	    // 用户则置空
        min_number = null;
        get_time   = -1;
    }
    // 发送请求
    $.ajax({
        url: "/property_system/admin/adminUpdateWithdrawSetting",
        type: "POST",
        dataType: 'json',
        data: {
            withdraw_type            : withdraw_type,
            service_charge_proportion: service_charge_proportion,
            is_any_time              : is_any_time,
            withdraw_start_time      : withdraw_start_time,
            withdraw_end_time        : withdraw_end_time,
            withdraw_proportion      : withdraw_proportion,
            min_number               : min_number,
            get_time                 : get_time
		},
        success: function (data) {
            layer.open({
                content: data.msg,
                // btn: ['确定'],
                // yes: function (index, layero) {
                //     location.reload();
                //     return true;
                // }
            });
        }
    });
}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
