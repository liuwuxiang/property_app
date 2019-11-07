package com.springmvc.controller.wnk_business_app.problem;

import com.springmvc.entity.Problem;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.ProblemService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
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

/**
 * @author 杨新杰
 * @Date 2018/10/25 15:48
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkProblemController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private ProblemService problemService;

    /**
     * 功能描述: 根据记录ID查询常见问题信息
     *
     * @param request    HttpServletRequest 服务类
     * @param problem_id 常见问题记录ID
     * @return 返回查询结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/selectProblemById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectProblemById(HttpServletRequest request,HttpServletResponse response, String problem_id,Integer business_id) {
        Result result = new Result();
        try {
            // 业务逻辑开始
            if (problem_id == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("参数不合法");
            } else {
                Map<String, Object> map = problemService.selectProblemById(problem_id);
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
            // 业务逻辑结束
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;



    }

    /**
     * 功能描述: 查询所有已经启用常见问题信息
     *
     * @param request    HttpServletRequest 服务类
     * @return 返回查询结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/selectProblemAllByTrue", method = RequestMethod.POST)
    @ResponseBody
    public Result selectProblemAllByTrue(HttpServletRequest request , HttpServletResponse response, Integer business_id) {
        Result result = new Result();
        try {
                // 业务逻辑开始
                List<Map<String, Object>> list = problemService.selectProblemAllByTrue();
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

        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 查询所有已经启用常见问题信息-用户查询
     *
     * @param request    HttpServletRequest 服务类
     * @return 返回查询结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/selectProblemAllByTrueUser", method = RequestMethod.POST)
    @ResponseBody
    public Result selectProblemAllByTrueUser(HttpServletRequest request , HttpServletResponse response, Integer business_id) {
        Result result = new Result();
        try {
            // 业务逻辑开始
            List<Map<String, Object>> list = problemService.selectProblemAllByTrueUser();
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

        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
