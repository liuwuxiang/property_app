var storage = window.localStorage;
/*
 *	折线图初始化
 * */
function lineChartInit(login_session){
    storage["login_session"] = login_session;
	var user_id = storage["user_id"];
    var login_session = storage["login_session"];
    //获取用户基础信息
    getUserBaseInformation();
	//获取用户最近收益
	getUserProfit();
}


//获取用户基础信息
function getUserBaseInformation() {
    toast(2,"打开loading");
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserBaseInformation",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"]},
        success:function(data){
            if (data.status == 0){
                setUserInformation(data);
                toast(3,"关闭loading");
                getUserProfit(0);
                //是否实名认证或设置支付密码
                realAuthentication(data.data.id_card_state,data.data.is_pay_state);
            }
            else{
                toast(1,data.msg);
            }
        },
    });
}

//实名认证处理
function realAuthentication(id_card_state,is_pay_state) {
    if (is_pay_state == 0){
        mui.alert('请先设置支付密码', '支付密码', function() {
            self.window.location.href = "/property_system/wx/v1.0.0/securityCenterHome";
        });
    }
}

//获取用户最近收益(type:0-近一周收益，1-近 一月收益)
function getUserProfit(type) {
    toast(2,"打开loading");
    var timeDateArray = new Array();
    var valueArray = new Array();
    $.ajax({
        url:"/property_system/app/v1.0.0/getUserWeekOrMonthIndustryIncome",
        type:"POST",
        headers: {
            'login_session' : storage["login_session"]
        },
        dataType : 'json',
        data:{"user_id":storage["user_id"],"type":type},
        success:function(data){
            if (data.status == 0){
                var list = data.data.list;
                for (var index = 0;index < list.length;index++){
                    var obj = list[index];
                    var time_str = obj.income_date;
                    var value_str = obj.income_amount;
                    time_str = formatDate(time_str);
                    timeDateArray.push(time_str);
                    valueArray.push(value_str);
                }
                initLineChart(timeDateArray,valueArray);
            }
            else{
                toast(3,"关闭loading");
            }
        },
    });
}

//初始化折线图
function initLineChart(timeDateArray,valueArray) {
    var getOption = function(chartType) {
        var chartOption = {
            legend: {
                data: []
            },
            grid: {
                x: 35,
                x2: 10,
                y: 30,
                y2: 25
            },
            toolbox: {
                show: false,
                feature: {
                    mark: {
                        show: true
                    },
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    magicType: {
                        show: true,
                        type: ['line', 'bar']
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            calculable: false,
            xAxis: [{
                type: 'category',
                data: timeDateArray
            }],
            yAxis: [{
                type: 'value',
                splitArea: {
                    show: true
                }
            }],
            series: [{
                name: '蒸发量',
                type: chartType,
                data: valueArray
            }]
        };
        return chartOption;
    };
    var byId = function(id) {
        return document.getElementById(id);
    };

    var lineChart = echarts.init(byId('lineChart'));
    lineChart.setOption(getOption('line'));
    toast(3,"关闭loading");
}

/*
 *	收益时间筛选事件(type=0:近一周,type=1:近一月)
 * */
function profitDateScreen(type){
	var nearly_a_week = document.getElementById("nearly_a_week");
	var nearly_one_month = document.getElementById("nearly_one_month");
	if(type == 0){
		nearly_a_week.className = "profit_click_button active_profit_click_button";
		nearly_one_month.className = "profit_click_button";
        getUserProfit(0);
	}
	else{
		nearly_a_week.className = "profit_click_button";
		nearly_one_month.className = "profit_click_button active_profit_click_button";
        getUserProfit(1);
	}
}

/*
 *	我的个人信息查看事件
 * */
function myInformation(){
	self.window.location.href = "/property_system/wx/v1.0.0/personalInformation";
}

/*
 *	进入通用积分界面
 * */
function joinGeneralIntegral(){
	self.window.location.href = "/property_system/wx/v1.0.0/generalIntegralHome";
}

/*
 *	进入消费积分页面
 * */
function joinConsumptionIntegral(){
	self.window.location.href = "/property_system/wx/v1.0.0/consumptionIntegralHome"
}

/*
 *	进入卡包界面
 * */
function joinCardBag(){
	self.window.location.href = "/property_system/wx/v1.0.0/cardBagHome";
}

/*
 *	进入会员中心
 * */
function joinMemberCenter(){
	self.window.location.href = "/property_system/wx/v1.0.0/memberCenter";
}

/*
 *	进入我的会员卡
 * */
function joinMyMemberCard(){
    self.window.location.href = "/property_system/wx/v1.0.0/joinMyMemberCard";
}

/*
 *	进入我的团队
 * */
function joinMyTeam(){
	self.window.location.href = "/property_system/wx/v1.0.0/myTeamHome";
}

/**
 * 进入我的积分
 */
function joinMyIntegral() {
    self.window.location.href = "/property_system/wx/v1.0.0/myIntegral";
}

/*
 * 进入我的产业
 * */
function joinMyIndustry(){
	self.window.location.href = "/property_system/wx/v1.0.0/myIndustry";
}

/*
 * 进入我的万能卡订单页
 * */
function joinMyWnkOrder(){
    self.window.location.href = "/property_system/wx/v1.0.0/joinWnkMyOrder";
}

/*
 *	进入系统设置
 * */
function joinSystemSetting(){
	self.window.location.href= "/property_system/wx/v1.0.0/systemSettingHome";
}

//进入消息中心
function joinMessageCenter() {
    self.window.location.href= "/property_system/wx/v1.0.0/joinMessageCenter";
}

//进入信用评级
function joinCredit() {
    self.window.location.href= "/property_system/wx/v1.0.0/joinCredit";
}


/*
* 设置用户信息
* */
function setUserInformation(data) {
    var user_id = data.data.user_id;
    // var login_session = data.data.login_session;
    var level_name = data.data.level_name;
    var level_icon = data.data.level_icon;
    var consumption_integral = data.data.consumption_integral;
    var nick_name = data.data.nick_name;
    var sex = data.data.sex;
    var header = data.data.header;
    var recommend_mobile = data.data.recommend_mobile;
    var team_members_number = data.data.team_members_number;
    var general_integral = data.data.general_integral;
    var mobile = data.data.mobile;
    var email = data.data.email;
    var is_microfinance = data.data.is_microfinance;
    var member_card_name = data.data.member_card_name;

    document.getElementById("header_img").src = header;
    document.getElementById("nick_name_tag").innerText = nick_name;
    if (recommend_mobile != "无推荐人" && recommend_mobile != undefined && recommend_mobile != ""){
        document.getElementById("nickname_div").style.marginTop = "35px";
        document.getElementById("recommend_name").style.display = "block";
        document.getElementById("recommend_name").innerText = "推荐人："+recommend_mobile;
    }
    else{
        document.getElementById("recommend_name").style.display = "none";
        document.getElementById("nickname_div").style.marginTop = "48px";
    }

    document.getElementById("member_level_icon").src = level_icon;
    document.getElementById("member_level_name").innerText = level_name;
    if (sex == 0){
        document.getElementById("user_sex_img").src = "/property_system/images/wx/icon/icon_sex_man.png";
    }
    else if(sex == 1){
        document.getElementById("user_sex_img").src = "/property_system/images/wx/icon/icon_sex_woman.png";
    }
    document.getElementById("consumption_balance_tag").innerText = consumption_integral;
    document.getElementById("general_balance_tag").innerText = general_integral;
    document.getElementById("li_member_level_name_tip").innerText = level_name;
    document.getElementById("my_team_number").innerText = "成员"+team_members_number+"位";
    // document.getElementById("li_member_card_name_tip").innerText = member_card_name;

    storage.setItem("user_id",user_id);
    storage.setItem("level_name",level_name);
    storage.setItem("level_icon",level_icon);
    storage.setItem("consumption_integral",consumption_integral);
    storage.setItem("nick_name",nick_name);
    storage.setItem("sex",sex);
    storage.setItem("header",header);
    storage.setItem("recommend_mobile",recommend_mobile);
    storage.setItem("team_members_number",team_members_number);
    storage.setItem("general_integral",general_integral);
    storage.setItem("mobile",mobile);
    storage.setItem("email",email);
    storage.setItem("is_microfinance",is_microfinance);
}

/*
* 格式化时间
* */
function formatDate(time_str) {
    time_str = time_str.replace(/-/g, '.');
    time_str = time_str.slice(5);
    return time_str;
}