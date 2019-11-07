package com.springmvc.controller.app;

import com.springmvc.entity.Users;
import com.springmvc.entity.WnkOrdersTwo;
import com.springmvc.service.impl.WnkOrderRefundReasonService;
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
 * @author: zhangfan
 * @Date: 2018/12/10 01:43
 * @Description:
 */
@Controller
@RequestMapping(value = "/app/v2.0.0")
public class WnkOrderRedundReasonAppController {
    @Resource
    private WnkOrderRefundReasonService wnkOrderRefundReasonService;

    /**
     *
     * 功能描述: 查询所有供选择的退款原因
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 1:44 AM
     */
    @RequestMapping(value = "/selectAllOrderRefundReasonRecord", method = RequestMethod.POST)
    @ResponseBody
    public Result selectAllOrderRefundReasonRecord(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            List<Map<Object,Object>> list = this.wnkOrderRefundReasonService.selectQYRecord();
            if (list.size() <= 0){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂无可供选择的退款原因");
            }
            else{
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
