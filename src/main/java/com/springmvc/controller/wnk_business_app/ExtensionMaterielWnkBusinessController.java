package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 02:38
 * @Description:推广物料万能卡商家接口类
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class ExtensionMaterielWnkBusinessController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private MyMaterielService myMaterielService;
    @Resource
    private ExtensionMaterielBuyMealService extensionMaterielBuyMealService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private ExtensionMaterielMealBuyWxOrderService extensionMaterielMealBuyWxOrderService;
    @Resource
    private UsersService usersService;
    @Resource
    private UserCouponsService userCouponsService;
    @Resource
    private UserOpenCardsService userOpenCardsService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private MaterielBuyRecordService materielBuyRecordService;

    /**
     *
     * 功能描述: 获取推广物料
     *
     * @param   request, response, business_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 18:08
     */
    @RequestMapping(value = "/getAllExtensionMateriel",method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getAllExtensionMateriel(HttpServletRequest request, HttpServletResponse response, Integer business_id){

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                   List<Map<Object,Object>> list = this.extensionMaterielService.selectAllRecord();
                   for (Integer index = 0;index < list.size();index++){
                       Map<Object,Object> map = list.get(index);
                       map.put("logo_photo_id", ImageToolsController.imageShowURL+"?imageid="+map.get("logo_photo_id"));
                       map.put("background_photo", ImageToolsController.imageShowURL+"?imageid="+map.get("background_photo"));
                   }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取商家的推广物料
     *
     * @param   request, response, business_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 18:07
     */
    @RequestMapping(value = "/getAllExtensionMaterielByBusinessId",method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getAllExtensionMaterielByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer business_id){

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.myMaterielService.selectAllRecordByUserId(business_id);
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("logo_photo_id", ImageToolsController.imageShowURL+"?imageid="+map.get("logo_photo_id"));
                        map.put("background_photo", ImageToolsController.imageShowURL+"?imageid="+map.get("background_photo"));
                    }
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取某个物料的所有购买套餐
     *
     * @param   request, response, business_id, materiel_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 18:07
     */
    @RequestMapping(value = "/getMaterielBuyMealByMaterielId",method = RequestMethod.POST,params = {"business_id","materiel_id"})
    @ResponseBody
    public Result getMaterielBuyMealByMaterielId(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer materiel_id){

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.extensionMaterielBuyMealService.selectAllRecordByMaterielId(materiel_id);
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无套餐");
                    }
                    else{
                        map.put("list",list);
                        ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectById(materiel_id);
                        if (extensionMateriel == null){
                            //0-物料限制购买,1-物料不限制购买
                            map.put("limit_times",1);
                        }
                        else{
                            //0-物料限制购买,1-物料不限制购买
                            map.put("limit_times",extensionMateriel.getLimit_times());
                            if (extensionMateriel.getLimit_times() == 0){
                                //物料限制购买次数
                                map.put("buy_number",extensionMateriel.getBuy_number());
                                List<Map<Object,Object>> buyList = this.materielBuyRecordService.selectRecordByBusinessIdAndMaterielId(business_id, materiel_id);
                                if (buyList.size() >= extensionMateriel.getBuy_number()){
                                    //是否达到购买次数上限0-已达到,1-未达到
                                    map.put("buy_number_finish",0);
                                }
                                else{
                                    //是否达到购买次数上限0-已达到,1-未达到
                                    map.put("buy_number_finish",1);
                                    //已购买次数
                                    map.put("buy_success_number",buyList.size());
                                }
                            }
                        }
                        map.put("is_wnk",extensionMateriel.getIs_wnk());
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 商家购买物料套餐-余额支付
     *
     * @param   request, response, business_id, materiel_meal_id, pay_text
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 18:07
     */
    @RequestMapping(value = "/wnkBusinessBuyMaterielMealBalancePay",method = RequestMethod.POST,params = {"business_id","materiel_meal_id","pay_text"})
    @ResponseBody
    public Result wnkBusinessBuyMaterielMealBalancePay(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer materiel_meal_id,String pay_text){

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else if(wnkBusinessAccount.getPay_pwd() == null || wnkBusinessAccount.getPay_pwd().equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先设置支付密码");
                }
                else if (!wnkBusinessAccount.getPay_pwd().equals(pay_text)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    ExtensionMaterielBuyMeal extensionMaterielBuyMeal = this.extensionMaterielBuyMealService.selectById(materiel_meal_id);
                    if (extensionMaterielBuyMeal == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此套餐不存在");
                    }
                    else if (extensionMaterielBuyMeal.getPrice() > wnkBusinessAccount.getBalance()){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("账户余额不足");
                    }
                    else{
                        ExtensionMateriel extensionMaterielA = this.extensionMaterielService.selectById(extensionMaterielBuyMeal.getMateriel_id());
                        List<Map<Object,Object>> materielBuyList = this.materielBuyRecordService.selectRecordByBusinessIdAndMaterielId(business_id,extensionMaterielBuyMeal.getMateriel_id());
                        if (extensionMaterielA == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("物料不存在");
                        }
                        else if (extensionMaterielA.getLimit_times() == 0 && materielBuyList.size() >= extensionMaterielA.getBuy_number()){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("购买失败:此物料限制购买"+extensionMaterielA.getBuy_number()+"次,你已用完");
                        }
                        else{
                            wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() - extensionMaterielBuyMeal.getPrice());
                            int updateBalanceState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            if (updateBalanceState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("购买失败");
                            }
                            else{
                                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                                if (wnkStoreInformation != null){
                                    WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                    wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                    wnkBusinessBalanceDetail.setName("购买推广物料");
                                    wnkBusinessBalanceDetail.setTransaction_amount(extensionMaterielBuyMeal.getPrice());
                                    wnkBusinessBalanceDetail.setJoin_time(new Date());
                                    wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                    wnkBusinessBalanceDetail.setType(1);
                                    wnkBusinessBalanceDetail.setState(0);
                                    this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);

                                    int addState = 0;
                                    MyMateriel myMateriel = this.myMaterielService.selectByMaterielIdAndBusinessId(business_id,extensionMaterielBuyMeal.getMateriel_id());
                                    if (myMateriel == null){
                                        myMateriel = new MyMateriel();
                                        myMateriel.setBusiness_id(business_id);
                                        myMateriel.setMateriel_id(extensionMaterielBuyMeal.getMateriel_id());
                                        myMateriel.setSurplus_number(extensionMaterielBuyMeal.getNumber());
                                        addState = this.myMaterielService.insertMaterielBuyRecord(myMateriel);
                                    }
                                    else{
                                        myMateriel.setSurplus_number(myMateriel.getSurplus_number() + extensionMaterielBuyMeal.getNumber());
                                        addState = this.myMaterielService.updateSurplusNumber(myMateriel);
                                    }
                                    if (addState <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("购买失败");
                                    }
                                    else{
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("购买成功");

                                        MaterielBuyRecord materielBuyRecord = new MaterielBuyRecord();
                                        materielBuyRecord.setMateriel_id(extensionMaterielA.getId());
                                        materielBuyRecord.setBusiness_id(business_id);
                                        materielBuyRecord.setBuy_number(extensionMaterielBuyMeal.getNumber());
                                        materielBuyRecord.setBuy_date(new Date());
                                        this.materielBuyRecordService.inserRecord(materielBuyRecord);

                                        //发送短信通知
                                        MobileCodeUtil.sendBusinessBuyRecommendExtensionWnkSms(wnkBusinessAccount.getMobile(),1,extensionMaterielBuyMeal.getPrice(),extensionMaterielA.getName(),extensionMaterielBuyMeal.getNumber());
                                    }
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("账户信息不存在");
                                }
                            }
                        }

                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 商户购买推广物料-微信支付
     *
     * @param: business_id,materiel_meal_id(物料套餐id)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessBuyMaterielMealWXPay", method = RequestMethod.POST,params = {"business_id","materiel_meal_id"})
    @ResponseBody
    public Result wnkBusinessBuyMaterielMealWXPay(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer materiel_meal_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    ExtensionMaterielBuyMeal extensionMaterielBuyMeal = this.extensionMaterielBuyMealService.selectById(materiel_meal_id);
                    if (extensionMaterielBuyMeal == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此套餐不存在");
                    }
                    else{
                        ExtensionMateriel extensionMaterielA = this.extensionMaterielService.selectById(extensionMaterielBuyMeal.getMateriel_id());
                        List<Map<Object,Object>> materielBuyList = this.materielBuyRecordService.selectRecordByBusinessIdAndMaterielId(business_id,extensionMaterielBuyMeal.getMateriel_id());
                        if (extensionMaterielA == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("物料不存在");
                        }
                        else if (extensionMaterielA.getLimit_times() == 0 && materielBuyList.size() >= extensionMaterielA.getBuy_number()){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("购买失败:此物料限制购买"+extensionMaterielA.getBuy_number()+"次,你已用完");
                        }
                        else{
                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                            if (wnkStoreInformation != null){
                                Map<String,Object> messageMap = new HashMap<String,Object>();
                                ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder = new ExtensionMaterielMealBuyWxOrder();
                                extensionMaterielMealBuyWxOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                                extensionMaterielMealBuyWxOrder.setBusiness_id(business_id);
                                extensionMaterielMealBuyWxOrder.setState(0);
                                extensionMaterielMealBuyWxOrder.setCreate_time(new Date());
                                extensionMaterielMealBuyWxOrder.setAmount(extensionMaterielBuyMeal.getPrice());
                                extensionMaterielMealBuyWxOrder.setMateriel_meal_id(extensionMaterielBuyMeal.getId());
                                int state = this.extensionMaterielMealBuyWxOrderService.insertMaterielMealBuyOrder(extensionMaterielMealBuyWxOrder);
                                if (state <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("下单失败");
                                }
                                else{
                                    Double amount = Double.valueOf(extensionMaterielMealBuyWxOrder.getAmount());
                                    Map<String,Object> resutMap = WechatPayLineUtil.wechatPayWnkBusinessBuyMaterielMeal(extensionMaterielMealBuyWxOrder.getOrder_no(), amount, request, response);
                                    if ((Boolean) resutMap.get("status") == true){
                                        SortedMap<String, String> parameterMap = (SortedMap<String, String>)resutMap.get("wx_config");
                                        JSONObject json = JSONObject.fromObject(parameterMap);

                                        Integer timestamp = (Integer) json.get("timestamp");

                                        String payData = "{\"appid\":\""+json.get("appid")+"\",\"noncestr\":\""+json.get("noncestr")+"\",\"package\":\"Sign=WXPay\",\"partnerid\":\""+json.get("partnerid")+"\",\"prepayid\":\""+json.get("prepayid")+"\",\"timestamp\":"+timestamp+",\"sign\":\""+json.get("sign")+"\"}";
                                        result.setData(payData);
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("下单成功");
                                    }
                                    else{
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg((String)resutMap.get("msg"));
                                    }
                                }
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("账户信息不存在");
                            }
                        }

                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;

    }





    // 商家赠送物料给用户
    @RequestMapping(value = "/wnkBusinessSendMaterielToUser",method = RequestMethod.POST,params = {"business_id","user_mobile","materiel_id","pay_pwd"})
    @ResponseBody
    public Result wnkBusinessSendMaterielToUser(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Integer business_id,
                                                String user_mobile,
                                                Integer materiel_id,
                                                String pay_pwd,
                                                Integer gitf_number){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (user_mobile.length() != 11){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("手机号不合法");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else if(wnkBusinessAccount.getPay_pwd() == null || wnkBusinessAccount.getPay_pwd().equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先设置支付密码");
                }
                else if (!wnkBusinessAccount.getPay_pwd().equals(pay_pwd)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else if (wnkStoreInformation == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户信息不存在");
                }
                else{
                    Users users = this.usersService.selectByMobile(user_mobile);
                    if (users == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    }
                    else{
                        MyMateriel myMateriel = this.myMaterielService.selectByMaterielIdAndBusinessId(business_id, materiel_id);
                        if (myMateriel == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("你暂无此物料");
                        }
                        // 如果物料剩余数量小于赠送数量增提示物料剩余不足
                        else if (myMateriel.getSurplus_number() < gitf_number){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此物料剩余不足");
                        }
                        else{
                            ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectById(materiel_id);
                            if (extensionMateriel == null){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此物料不存在");
                            }
                            //此物料为万能银卡
                            else if (extensionMateriel.getIs_wnk() == 1){
                                if (users.getMember_card_level() != -1){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此会员已开卡无需赠送");
                                }
                                //
                                if (gitf_number > 1){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("万能卡一次只能赠送一张");
                                }
                                else{ // 赠送万能卡
                                    // 如果满足以上条件则减去赠送数量
                                    myMateriel.setSurplus_number(myMateriel.getSurplus_number() - gitf_number);
                                    int state = this.myMaterielService.updateSurplusNumber(myMateriel);
                                    if (state <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("赠送失败");
                                    }
                                    else{
                                        users.setMember_card_level(0);
                                        int levelChangeState = this.usersService.updateMemberCardLevel(users);
                                        if (levelChangeState > 0){
                                            int openCardState = this.userOpenCardsService.userOpenCardOrRenew(users.getId(),1,1);
                                            if (openCardState > 0){
                                                result.setData("");
                                                result.setStatus(Result.SUCCESS);
                                                result.setMsg("赠送成功");
                                                //更改用户推荐人及推荐类别
                                                this.usersService.businessChangeUserRecommend(24,1,22);
                                                //更改用户推荐人及推荐类别
//                                                this.usersService.updateUserRecommendIdAndRecommendType(business_id,1,users.getId());
                                                if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                                    String pushTitle = "商家赠送万能卡";
                                                    String pushContent = wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+1+"张万能银卡，请在猛戳-我的优惠券查看";
                                                    GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                                }
                                                if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                                    String pushTitle = "赠送万能卡";
                                                    String pushContent = "您向"+users.getMobile()+"用户赠送了1张万能银卡，请在猛戳-赠送优惠券查看";
                                                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                                }
                                            }
                                            else{
                                                result.setData("");
                                                result.setStatus(Result.FAIL);
                                                result.setMsg("开卡失败");
                                            }
                                        }
                                        else{
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("开卡失败");
                                        }
                                    }
                                }
                            }
                            else{ // 赠送其他物料
                                myMateriel.setSurplus_number(myMateriel.getSurplus_number() - gitf_number);
                                int state = this.myMaterielService.updateSurplusNumber(myMateriel);
                                if (state <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("赠送失败");
                                }
                                else{


                                    UserCoupons userCoupons = this.userCouponsService.selectByMaterielIdAndUserId(users.getId(),materiel_id);

                                    if (userCoupons == null){
                                        userCoupons = new UserCoupons();
                                        userCoupons.setUser_id(users.getId());
                                        userCoupons.setMateriel_id(materiel_id);
                                        userCoupons.setSurplus_number(gitf_number);
                                        this.userCouponsService.insertMaterielBuyRecord(userCoupons);
                                    }
                                    else{
                                        userCoupons.setSurplus_number(userCoupons.getSurplus_number() + gitf_number);
                                        this.userCouponsService.updateSurplusNumber(userCoupons);
                                    }
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("赠送成功");
                                    if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                        String pushTitle = "商家赠送优惠券";
                                        String pushContent = wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+gitf_number+"张"+extensionMateriel.getName()+"，请在猛戳-我的优惠券查看";
                                        GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                    }
                                    if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                        String pushTitle = "赠送优惠券";
                                        String pushContent = "您向"+users.getMobile()+"用户赠送了"+gitf_number+"张"+extensionMateriel.getName()+"，请在猛戳-赠送优惠券查看";
                                        GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }





}
