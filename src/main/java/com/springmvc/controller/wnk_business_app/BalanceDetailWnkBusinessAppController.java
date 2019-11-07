package com.springmvc.controller.wnk_business_app;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessWithdrawOrder;
import com.springmvc.entity.WnkCommodityTypes;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class BalanceDetailWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessWithdrawOrderService wnkBusinessWithdrawOrder;
    @Resource
    private WnkBusinessWithdrawOrderService wnkBusinessWithdrawOrderService;

    // 获取商家余额明细(type=0-收入,type=1-支出)
    @RequestMapping(value = "/getBalanceDetail", method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result getBalanceDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer type){
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
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("balance",wnkBusinessAccount.getBalance());
                    map.put("detail",this.wnkBusinessBalanceDetailService.selectRecordByBusinessIdAndType(business_id,type));
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 获取商家提现订单详情
    @RequestMapping(value = "/selectWithdrawInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectWithdrawInfoById(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Integer business_id,
                                         Integer withdraw_id){
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
                    Map<Object,Object> map = this.wnkBusinessWithdrawOrderService.selectWithdrawInfoById(business_id,withdraw_id);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


//    // 生成所有商家二维码
//    @RequestMapping(value = "/businessQrCodeSC", method = RequestMethod.GET)
//    @ResponseBody
//    public Result businessQrCodeSC(HttpServletRequest request, HttpServletResponse response){
//        Result result = new Result();
//        List<Map<Object,Object>> list = this.wnkStoreInformationService.selectAllBusiness();
//        for (Integer index = 0;index<list.size();index++){
//            Map<Object,Object> map = list.get(index);
//            Integer business_id = (Integer) map.get("business_id");
//            //商户推广二维码
//            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
//
//            //商户支付二维码
//            String wnkQrcodeNamePay = UUDUtil.getOrderIdByUUId();
//            Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
//            orderQrcodeMap.put("business_id",business_id);
//            JSONObject json = JSONObject.fromObject(orderQrcodeMap);
//            try {
//                String wnkQrcodeUrl = QRCode.generateQRCode("http://m.chenlankeji.cn/property_system/wnk_business/joinBusinessRecommendRegister?business_id="+business_id,wnkQrcodeName);
//                String wnkQrcodeUrlPay = QRCode.generateQRCode(json.toString(),wnkQrcodeNamePay);
//                String recommend_qr_code = wnkQrcodeName+".png";
//                String pay_qr_code = wnkQrcodeNamePay+".png";
//                this.wnkStoreInformationService.updateBusinessQrCode(recommend_qr_code,pay_qr_code,business_id);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
}
