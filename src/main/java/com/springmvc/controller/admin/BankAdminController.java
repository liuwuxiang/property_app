package com.springmvc.controller.admin;

import com.springmvc.entity.WithdrawSupportBank;
import com.springmvc.service.impl.WithdrawSupportBankService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class BankAdminController {
    @Resource
    private WithdrawSupportBankService withdrawSupportBankService;

    //进入银行管理界面
    @RequestMapping(value = "/bankManager")
    @ResponseBody
    public ModelAndView bankManager(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/bank_manager/bank_manager");
        }
        return modelAndView;
    }

    //进入添加银行界面
    @RequestMapping(value = "/addBank")
    @ResponseBody
    public ModelAndView addBank(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/bank_manager/add_bank");
            modelAndView.addObject("type",0);
        }
        return modelAndView;
    }

    //进入修改银行界面
    @RequestMapping(value = "/setBank",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView setBank(HttpServletRequest request,Integer record_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/bank_manager/add_bank");
            modelAndView.addObject("type",1);
            WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(record_id);
            if (withdrawSupportBank == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("bank_name","");
                modelAndView.addObject("bank_code","");
            }
            else{
                modelAndView.addObject("record_id",withdrawSupportBank.getId());
                modelAndView.addObject("bank_name",withdrawSupportBank.getName());
                modelAndView.addObject("bank_code",withdrawSupportBank.getCode());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有银行
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:25
     */
    @RequestMapping(value = "/getAllBank", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllBank(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.withdrawSupportBankService.selectAllAdmin();
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述:根据条件查询银行信息
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   name        银行名称
     * @param   code        银行id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:30
     */
    @RequestMapping(value = "/adminSearchBankByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchBankByConditions(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String name,
                                                      Integer code
    ){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                map.put("name", "%"+name+"%");
                map.put("code", "%"+code+"%");
                List<Map<String, Object>> List = this.withdrawSupportBankService.adminSearchBankByConditions(map);
                result.setData(List);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 添加银行
    @RequestMapping(value = "/addBankAction", method = RequestMethod.POST,params = {"bank_name","bank_code"})
    @ResponseBody
    public Result addBankAction(HttpServletRequest request, HttpServletResponse response,String bank_name,String bank_code){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (bank_name.equals("") || bank_name == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入银行名称");
                }
                else if (bank_code.equals("") || bank_code == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入银行Code");
                }
                else{
                    WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectByName(bank_name);
                    if (withdrawSupportBank != null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此银行名称已存在");
                    }
                    else{
                        withdrawSupportBank = new WithdrawSupportBank();
                        withdrawSupportBank.setName(bank_name);
                        withdrawSupportBank.setCode(bank_code);
                        int addState = this.withdrawSupportBankService.addBank(withdrawSupportBank);
                        if (addState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("添加失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("添加成功");
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


    // 修改银行
    @RequestMapping(value = "/setBankAction", method = RequestMethod.POST,params = {"record_id","bank_name","bank_code"})
    @ResponseBody
    public Result setBankAction(HttpServletRequest request, HttpServletResponse response,Integer record_id,String bank_name,String bank_code){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (bank_name.equals("") || bank_name == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入银行名称");
                }
                else if (bank_code.equals("") || bank_code == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入银行Code");
                }
                else{
                    WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectByName(bank_name);
                    if (withdrawSupportBank != null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此银行名称已存在");
                    }
                    else{
                        withdrawSupportBank = this.withdrawSupportBankService.selectById(record_id);
                        if (withdrawSupportBank == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此记录不存在");
                        }
                        else{
                            withdrawSupportBank.setName(bank_name);
                            withdrawSupportBank.setCode(bank_code);
                            int addState = this.withdrawSupportBankService.updateBank(withdrawSupportBank);
                            if (addState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("修改失败");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("修改成功");
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

    // 删除银行
    @RequestMapping(value = "/deleteBankAction", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result deleteBankAction(HttpServletRequest request, HttpServletResponse response,Integer record_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(record_id);
                if (withdrawSupportBank == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此银行不存在");
                }
                else{
                    int deleteState = this.withdrawSupportBankService.deleteRecordById(record_id);
                    if (deleteState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("删除失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("删除成功");
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
