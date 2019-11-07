package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.springmvc.utils.Qrcode.createQrCodePic;
import static com.springmvc.utils.Qrcode.generateCode;

/**
 * @author: zhangfan
 * @Date: 2018/11/5 02:12
 * @Description:用户面向商家开会员卡接口类
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkBusinessTypeOpenCardAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private ConsumptionIntegralExpenditureService consumptionIntegralExpenditureService;
    @Resource
    private UserOpenBusinessMemberCardWXOrdersService userOpenBusinessMemberCardWXOrdersService;

    @Resource
    private WnkBusinessFitnessRecordService wnkBusinessFitnessRecordService;


    /**
     * 功能描述: 用户开商家会员卡支付-通用积分/消费积分支付
     *
     * @param user_id     用户ID
     * @param business_id 商家ID
     * @param pay_way     支付方式(0-通用积分支付,1-消费积分支付)
     * @param pay_pwd     支付密码
     * @return:
     * @author: zhangfan
     * @date: 2018/11/5 2:13 AM
     */
    @RequestMapping(value = "/userOpenBusinessMemberCardIntegralPay", method = RequestMethod.POST, params = {"user_id", "business_id", "pay_way", "pay_pwd"})
    @ResponseBody
    public Result userOpenBusinessMemberCardIntegralPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id, Integer pay_way, String pay_pwd,Double pay_price) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else if (pay_way != 0 && pay_way != 1) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            } else {
                Users users = this.usersService.selectById(user_id);
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("用户不存在");
                } else if (!users.getPay_pwd().equals(pay_pwd)) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                } else if (wnkStoreInformation == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商户不存在");
                } else {
                    WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    if (wnkBusinessType == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商户分类不存在");
                    } else if (wnkBusinessType.getCommdity_charge_way() == 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商户不支持开卡");
                    } else {
                        Double price = null;
                        if (pay_price == null){
                            wnkBusinessType.getCommodifty_price();
                        } else {
                            price = pay_price;
                        }
                        if (pay_way == 0 && users.getGeneral_integral() < price) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("通用积分余额不足");
                        } else if (pay_way == 1 && users.getConsumption_integral() < price) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("消费积分余额不足");
                        } else {
                            if (pay_way == 0) {
                                users.setGeneral_integral(users.getGeneral_integral() - price);
                            } else {
                                users.setConsumption_integral(users.getConsumption_integral() - price);
                            }
                            //进行用户积分余额扣减
                            int updateBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                            if (updateBalanceState <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("支付失败");
                            } else {
                                //进行开卡操作
                                WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, business_id);
                                if (wnkBusinessTypeOpenCard == null) {
                                    wnkBusinessTypeOpenCard = new WnkBusinessTypeOpenCard();
                                    wnkBusinessTypeOpenCard.setUser_id(user_id);
                                    wnkBusinessTypeOpenCard.setBusiness_type_id(wnkBusinessType.getId());
                                    wnkBusinessTypeOpenCard.setOpen_card_time(new Date());
                                    wnkBusinessTypeOpenCard.setBusiness_id(business_id);


                                    if (wnkBusinessType.getName().equals("健身")) {
                                        //生成健身卡二维码
                                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                        String text = "{\"type\":\"4\",\"user_id\":\"" + user_id + "\"}";
                                        String logoPath = ImageToolsController.qrcodeURL +  "/logo.jpg";
                                        StringBuilder sb = new StringBuilder(logoPath);
                                        BufferedImage sourceImg1 = ImageIO.read(new File(logoPath));
                                        sb.append(120).append("w_").append(120).append("h_1e_1c.src");
                                        BufferedImage qrImg3 = generateCode(text, sb.toString(), 250, 250, 250, 250);
                                        File file = new File(ImageToolsController.qrcodeURL + "/" + wnkQrcodeName + ".png");
                                        FileOutputStream out = new FileOutputStream(file);
                                        createQrCodePic(250, 250, qrImg3, 250, 250, 0, 0, sourceImg1, 50, 50, 100, 100, out);
                                        wnkBusinessTypeOpenCard.setQrcode(wnkQrcodeName + ".png");
                                        wnkBusinessTypeOpenCard.setType(1); // 类型:健身卡
                                    } else {
                                        wnkBusinessTypeOpenCard.setType(0); // 普通商家会员卡
                                    }


                                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd,hh:mm:ss");
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(new Date());
                                    calendar.add(Calendar.MONTH, 12);
                                    wnkBusinessTypeOpenCard.setCard_end_time(calendar.getTime());
                                    this.wnkBusinessTypeOpenCardService.insertOpenCardRecord(wnkBusinessTypeOpenCard);

                                } else {



                                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd,hh:mm:ss");
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(wnkBusinessTypeOpenCard.getCard_end_time());
                                    calendar.add(Calendar.MONTH, 12);
                                    wnkBusinessTypeOpenCard.setCard_end_time(calendar.getTime());

                                    if (wnkBusinessType.getName().equals("健身")) {
                                        //生成健身卡二维码
                                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                        String text = "{\"type\":\"4\",\"user_id\":\"" + user_id + "\"}";
                                        String logoPath = ImageToolsController.qrcodeURL +  "/logo.jpg";
                                        StringBuilder sb = new StringBuilder(logoPath);
                                        BufferedImage sourceImg1 = ImageIO.read(new File(logoPath));
                                        sb.append(120).append("w_").append(120).append("h_1e_1c.src");
                                        BufferedImage qrImg3 = generateCode(text, sb.toString(), 250, 250, 250, 250);
                                        File file = new File(ImageToolsController.qrcodeURL + "/" + wnkQrcodeName + ".png");
                                        FileOutputStream out = new FileOutputStream(file);
                                        createQrCodePic(250, 250, qrImg3, 250, 250, 0, 0, sourceImg1, 50, 50, 100, 100, out);
                                        wnkBusinessTypeOpenCard.setQrcode(wnkQrcodeName + ".png");
                                    }

                                    this.wnkBusinessTypeOpenCardService.updateCardEndTime(wnkBusinessTypeOpenCard);
                                }

                                //进行用户积分明细记录
                                // 0 通用积分 1 消费积分
                                if (pay_way == 0) {
                                    GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                    generalIntegralExpenditure.setName("办理商家会员卡");
                                    generalIntegralExpenditure.setExpenditure_date(new Date());
                                    generalIntegralExpenditure.setExpenditure_amount(price);
                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                    generalIntegralExpenditure.setUser_id(user_id);
                                    generalIntegralExpenditure.setExpenditure_type(5);
                                    this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);

                                } else {

                                    ConsumptionIntegralExpenditure consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                    consumptionIntegralExpenditure.setName("办理商家会员卡");
                                    consumptionIntegralExpenditure.setExpenditure_date(new Date());
                                    consumptionIntegralExpenditure.setExpenditure_amount(price);
                                    consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral());
                                    consumptionIntegralExpenditure.setUser_id(user_id);
                                    consumptionIntegralExpenditure.setExpenditure_type(4);
                                    this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
                                }
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("支付成功");
                            }
                        }

                    }
                }

            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 用户办理商家会员卡微信支付
    @RequestMapping(value = "/userBuyBusinessMemberCardWXPay", method = RequestMethod.POST)
    @ResponseBody
    public Result userBuyBusinessMemberCardWXPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id,Double pay_price) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else if (wnkStoreInformation == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商户不存在");
                } else {
                    WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    if (wnkBusinessType == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商户分类不存在");
                    } else if (wnkBusinessType.getCommdity_charge_way() == 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商户不支持开卡");
                    } else {
                        WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, business_id);
                        if (wnkBusinessTypeOpenCard == null) {
                            UserOpenBusinessMemberCardWXOrders userOpenBusinessMemberCardWXOrders = new UserOpenBusinessMemberCardWXOrders();
                            userOpenBusinessMemberCardWXOrders.setUser_id(user_id);
                            userOpenBusinessMemberCardWXOrders.setBusiness_id(business_id);
                            userOpenBusinessMemberCardWXOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                            userOpenBusinessMemberCardWXOrders.setState(0);
                            userOpenBusinessMemberCardWXOrders.setCreate_time(new Date());
                            userOpenBusinessMemberCardWXOrders.setBusiness_type_id(wnkBusinessType.getId());
                            int insertState = this.userOpenBusinessMemberCardWXOrdersService.insertNewOrder(userOpenBusinessMemberCardWXOrders);
                            if (insertState <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            } else {
                                Map<String, Object> resutMap = new HashMap<>();
                                if (pay_price == null){
                                    resutMap = WechatPayLineUtil.wechatPayBuyBusinessMemberCardWXAppPay(userOpenBusinessMemberCardWXOrders.getOrder_no(), wnkBusinessType.getCommodifty_price(), request, response);
                                } else {
                                    resutMap = WechatPayLineUtil.wechatPayBuyBusinessMemberCardWXAppPay(userOpenBusinessMemberCardWXOrders.getOrder_no(), pay_price, request, response);
                                }

                                if ((Boolean) resutMap.get("status") == true) {
                                    SortedMap<String, String> parameterMap = (SortedMap<String, String>) resutMap.get("wx_config");
                                    JSONObject json = JSONObject.fromObject(parameterMap);

                                    Integer timestamp = (Integer) json.get("timestamp");

                                    String payData = "{\"appid\":\"" + json.get("appid") + "\",\"noncestr\":\"" + json.get("noncestr") + "\",\"package\":\"Sign=WXPay\",\"partnerid\":\"" + json.get("partnerid") + "\",\"prepayid\":\"" + json.get("prepayid") + "\",\"timestamp\":" + timestamp + ",\"sign\":\"" + json.get("sign") + "\"}";
                                    result.setData(payData);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("下单成功");
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    String msg = (String) resutMap.get("msg");
                                    if (msg == null || msg.equals("")) {
                                        result.setMsg("未知错误");
                                    } else {
                                        result.setMsg((String) resutMap.get("msg"));
                                    }

                                }
                            }
                        } else {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("你已在此商户处开卡");
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("下单失败");
        }
        return result;
    }

    /**
     * 功能描述: 获取用户健身卡信息
     *
     * @param user_id     用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/11/5 2:13 AM
     */
    @RequestMapping(value = "/selectFitnessCard", method = RequestMethod.POST)
    @ResponseBody
    public Result selectFitnessCard(HttpServletRequest request, HttpServletResponse response, Integer user_id,Double lat,Double longt) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("用户不存在");
                } else {
                    Map<String,Object> maps = new HashMap<>();
                    maps.put("user_id",user_id);
                    maps.put("lat",lat);
                    maps.put("longt",longt);

                    List<Map<String,Object>> list =  this.wnkBusinessTypeOpenCardService.selectOpenCardFitnessByUserId(maps);
                    if (list.size() > 0){
                        for (Map<String,Object> map : list){
                            map.put("qrcode_path",ImageToolsController.qrcodeShowURL+ "?imageid=");
                            if (map.get("removing") == null){
                                map.put("removing","不详");
                            }
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData(null);
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     * 功能描述: 通过ID获取健身卡信息
     *
     * @param user_id     用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/11/5 2:13 AM
     */
    @RequestMapping(value = "/selectFitnessCardInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectFitnessCardInfoById(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer card_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("用户不存在");
                } else {
                    Map<String,Object> map = this.wnkBusinessTypeOpenCardService.selectFitnessCardInfoById(user_id,card_id);
                    if (map != null){
                        map.put("qrcode_path",ImageToolsController.qrcodeShowURL+"?imageid=");
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData(null);
                        result.setStatus(Result.FAIL);
                        result.setMsg("查询失败");
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



    /**
     * 功能描述: 获取健身卡使用记录
     *
     * @param user_id     用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/11/5 2:13 AM
     */
    @RequestMapping(value = "/selectFitnessCardDetailById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectFitnessCardDetailById(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("用户不存在");
                } else {
                    List<Map<String,Object>> list = this.wnkBusinessFitnessRecordService.selectFitnessCardDetailById(user_id,business_id);
                    if (list.size() > 0){
//                        map.put("qrcode_path",ImageToolsController.qrcodeShowURL+"?imageid=");
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData(null);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("暂无记录");
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
