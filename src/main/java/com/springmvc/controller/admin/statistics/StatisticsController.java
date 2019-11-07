package com.springmvc.controller.admin.statistics;

import com.springmvc.service.impl.StatisticsService;
import com.springmvc.service.impl.WnkBusinessLevelService;
import com.springmvc.service.impl.WnkStoreInformationService;
import com.springmvc.utils.CookileUtil;
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
 * 功能描述:
 *
 * @author: 杨新杰
 * @date: 2018/11/30 14:59
 */
@Controller
@RequestMapping("/admin")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private WnkBusinessLevelService wnkBusinessLevelService;



    /**
     * 功能描述:  获取所有建议反馈统计信息
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectSuggestStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectSuggestStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> list = statisticsService.selectSuggestStatisticsInfo();
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  获取用户信息
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectUserStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> list = statisticsService.selectUserStatisticsInfo();
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  获取商家信息信息
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectBusinessStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<String, Object>> lists = new LinkedList<>();

                Map<String, Object> map = statisticsService.selectBusinessStatisticsInfo();

                List<Map<String, Object>> list = this.wnkBusinessLevelService.selectAll();
                if (list.size() > 0) {
                    for (Map<String, Object> maps : list) {
                        Map<String, Object> levelInfoMap = new HashMap<>();
                        levelInfoMap.put("level_name", maps.get("level_name"));
                        levelInfoMap.put("level_number", this.statisticsService.StatisticsLevelNumber((Integer) maps.get("id")).get("Level_umber"));
                        lists.add(levelInfoMap);
                    }
                }
                map.put("level_info", lists);

                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }

        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  玫瑰兑换 - 商家
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectBusinessRoseDetailStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessRoseDetailStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectBusinessRoseDetailStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  玫瑰兑换 - 商家
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectUserRoseDetailStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserRoseDetailStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectUserRoseDetailStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  充值统计 - 商家
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectBusinessBalanceStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessBalanceStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectBusinessBalanceStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  充值统计 - 商家
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectUserBalanceStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserBalanceStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectUserBalanceStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     * 功能描述:  提现统计 - 用户
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectUserWithdrawStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserWithdrawStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectUserWithdrawStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     * 功能描述:  提现统计 - 用户
     *
     * @param request HttpServletRequest
     * @author 杨新杰
     * @date 2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/selectBusinessWithdrawStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessWithdrawStatisticsInfo(HttpServletRequest request) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectBusinessWithdrawStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 订单记录 - 商家
     *
     * @param   request  HttpServletRequest
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @Date: 15:56 2018/12/12
     */
    @RequestMapping(value = "/selectOrdersStatisticsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectOrdersStatisticsInfo(HttpServletRequest request) {
            Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = this.statisticsService.selectOrdersStatisticsInfo();
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
