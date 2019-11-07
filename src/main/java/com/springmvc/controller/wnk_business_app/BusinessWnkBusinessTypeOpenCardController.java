package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/29 14:49
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class BusinessWnkBusinessTypeOpenCardController {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;

    @Resource
    private WnkBusinessFitnessRecordService wnkBusinessFitnessRecordService;

    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;

    /**
     * 功能描述: 商家会员管理查询
     *
     * @param business_id 商家ID
     * @param sort_type   排序方式
     * @param gender      性别
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @RequestMapping(value = "/selectBusinessVip", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessVip(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer sort_type, Integer gender) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    //
                    List<Map<String, Object>> list = this.wnkBusinessTypeOpenCardService.selectBusinessVip(business_id, sort_type, gender);
                    if (list.size() > 0) {
                        for (Map<String, Object> map : list) {
                            map.put("img_path", ImageToolsController.imageShowURL + "?imageid=");
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData(null);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("暂无数据");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 商家会员管理查询 - 通过手机号码或者用户名
     *
     * @param business_id 商家ID
     * @param content     搜索结果
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @RequestMapping(value = "/selectBusinessVipSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessVipSearch(HttpServletRequest request, HttpServletResponse response, String business_id, String content) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(Integer.valueOf(business_id), 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(Integer.valueOf(business_id));
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    content = "%" + content + "%";
                    List<Map<String, Object>> list = this.wnkBusinessTypeOpenCardService.selectBusinessVipSearch(business_id, content);
                    if (list.size() > 0) {
                        for (Map<String, Object> map : list) {
                            map.put("img_path", ImageToolsController.imageShowURL + "?imageid=");
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("没有查找结果");
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
     * 功能描述: 查看用户健身卡状态
     *
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @RequestMapping(value = "/selectUserGymCardStatusByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserGymCardStatusByUserId(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateGymByUserIdAndBusinessId(user_id, business_id);
                    if (wnkBusinessTypeOpenCard == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    } else {

                        // 查看是否到期
                        if (wnkBusinessTypeOpenCard.getCard_end_time().getTime() < System.currentTimeMillis()) {
                            // 健身卡到期
                            result.setData("2");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
                        } else {
                            // 查询是进门还是出门
                            Map<String, Object> map = this.wnkBusinessFitnessRecordService.selectUserGymCardStatusByUserId(user_id, business_id);
                            if (map == null){
                                result.setData(0);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("查询成功");
                            } else {
                                result.setData(map.get("status"));
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("查询成功");
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


    /**
     * 功能描述: 获取用户信息
     *
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @RequestMapping(value = "/selectUserGymCardInfoByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserGymCardInfoByUserId(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    Map<String, Object> map = this.wnkBusinessTypeOpenCardService.selectUserGymCardInfoByUserId(user_id, business_id);
                    map.put("img_path", ImageToolsController.imageShowURL + "?imageid=");
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
     * 功能描述: 插入用户进/出门记录
     *
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @RequestMapping(value = "/insertUserGymCardStatusByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Result insertUserGymCardStatusByUserId(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id, Integer type) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateGymByUserIdAndBusinessId(user_id, business_id);
                    if (wnkBusinessTypeOpenCard == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    } else {
                        WnkBusinessFitnessRecord w = new WnkBusinessFitnessRecord();
                        w.setBusiness_id(business_id);
                        w.setUse_time(new Date());
                        w.setUse_type(type);
                        w.setUser_id(user_id);
                        if (this.wnkBusinessFitnessRecordService.insertUserGymCardStatusByUserId(w) > 0) {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("操作成功");
                        } else {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("操作失败");
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

    /**
     * 功能描述: 获取商家类型
     *
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @RequestMapping(value = "/selectWnkBusinessTypeNameByBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result selectWnkBusinessTypeNameByBusiness(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id, Integer type) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    result.setData(wnkBusinessType.getName());
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
