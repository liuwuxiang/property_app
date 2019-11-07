package com.springmvc.controller.wnk_business_app.business_type;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkCommodities;
import com.springmvc.entity.WnkCommoditySpecifications;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.IWnkBusinessTypeQuickInputService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
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
import java.util.List;
import java.util.Map;

/**
 * 功能描述:商家端规格快捷输入控制器
 *
 * @author  杨新杰
 * @date 2019/1/7 14:27
 */

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class BusinessTypeQuickInputAppController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private IWnkBusinessTypeQuickInputService wnkBusinessTypeQuickInputService;

    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    /**
     *
     * 功能描述: 根据商家ID获取对应分类的规格快捷输入
     *
     * @param businessId  商家ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:30 2019/1/7
     */
    @RequestMapping(value = "/selectBusinessTypeQuickInputByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessTypeQuickInputByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer businessId){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                    return result;
                }

                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(businessId);
                List<Map<String, Object>> list = this.wnkBusinessTypeQuickInputService.selectBusinessTypeQuickInputByBusinessTypeId(wnkStoreInformation.getBusiness_type_id());
                if (list.size() > 0 ){
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData(list);
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
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
