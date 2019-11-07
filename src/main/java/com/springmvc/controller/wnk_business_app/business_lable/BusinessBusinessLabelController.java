package com.springmvc.controller.wnk_business_app.business_lable;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkBusinessLabelService;
import com.springmvc.service.impl.WnkStoreInformationService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:商家端 - 服务内容and特色内容
 *
 * @author 杨新杰
 * @date 2019/1/2 11:56
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class BusinessBusinessLabelController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    /**
     *
     * 功能描述: 获取商家服务内容和特色内容
     *
     * @param   business_id  商家ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    11:57 2019/1/2
     */
    @RequestMapping(value = "/selectBusinessTsAndFwContentByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessTsAndFwContentByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    Map<String,Object> retMap = new HashMap<>();
                    StringBuffer labelStr = new StringBuffer();
                    String[] labelArr;

                    // 查询商家信息
                    WnkStoreInformation wsi = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    String fuWuLabel = wsi.getFuwu_label();
                    String teseLabel = wsi.getTese_label();

                    // 通过ID循环查询特色和服务标签内容
                    labelArr = fuWuLabel.split(",");
                    if (labelArr.length > 0) {
                        for (int i = 0; i < labelArr.length; i++) {
                            Map<String, Object> map = this.wnkBusinessLabelService.selectLabel(labelArr[i]);
                            if (map != null) {
                                labelStr.append(map.get("name") + ",");
                            }
                        }
                        retMap.put("fuwuLabel",labelStr.substring(0, labelStr.length() - 1));
                    }

                    labelArr = teseLabel.split(",");
                    if (labelArr.length > 0) {
                        // 清空labelStr的值
                        labelStr.delete(0,labelStr.length());
                        for (int i = 0; i < labelArr.length; i++) {
                            Map<String, Object> map = this.wnkBusinessLabelService.selectLabel(labelArr[i]);
                            if (map != null) {
                                labelStr.append(map.get("name") + ",");
                            }
                        }
                        retMap.put("teseLabel",labelStr.substring(0, labelStr.length() - 1));
                    }

                    result.setData(retMap);
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


}
