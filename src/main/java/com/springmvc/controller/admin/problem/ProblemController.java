package com.springmvc.controller.admin.problem;

import com.springmvc.entity.Problem;
import com.springmvc.service.impl.ProblemService;
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

/**
 *
 * 功能描述: 常见问题
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 10:00
 */
@Controller
@RequestMapping("/admin")
public class ProblemController {


    @Resource
    private ProblemService problemService;

    /**
     * 功能描述:  进入常见问题列表页面
     *
     * @param request HttpServletRequest 服务类
     * @return 返回常见问题页面
     * @author 杨新杰
     * @date 2018/10/26 10:25
     */
    @RequestMapping(value = "/joinProblem", method = RequestMethod.GET)
    public ModelAndView joinProblem(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/problem/problem");
        }
        return modelAndView;
    }

    /**
     * 功能描述:  进入新增页面
     *
     * @param request HttpServletRequest 服务类
     * @return 返回新增页面
     * @author 杨新杰
     * @date 2018/10/26 10:25
     */
    @RequestMapping(value = "/joinAddProblem", method = RequestMethod.GET)
    public ModelAndView joinAddProblem(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/problem/problem_edit");
        }
        return modelAndView;
    }

    /**
     * 功能描述:  进入修改页面
     *
     * @param request    HttpServletRequest 服务类
     * @param problem_id 常见问题记录ID
     * @return 返回修改页面
     * @author 杨新杰
     * @date 2018/10/26 10:25
     */
    @RequestMapping(value = "/joinEditProblem", method = RequestMethod.GET)
    public ModelAndView joinEditProblem(HttpServletRequest request, String problem_id) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/problem/problem_edit");
            modelAndView.addObject("problem_id", problem_id);
        }
        return modelAndView;
    }

    /**
     * 功能描述: 常见问题新增
     *
     * @param request HttpServletRequest 服务类
     * @return 返回新增结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/addProblem", method = RequestMethod.POST, params = {"title", "content", "check", "type", "open_way"})
    @ResponseBody
    public Result addProblem(HttpServletRequest request, String title, String content, Integer check, Integer type, Integer open_way) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("暂未登录");
            } else {
                // 参数检查
                if (title.equals("") || check == null || content.equals("")) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("参数不合法");
                } else {
                    Problem problem = new Problem();
                    problem.setTitle(title);
                    problem.setContent(content);
                    problem.setCheck(check);
                    problem.setType(type);
                    problem.setOpen_way(open_way);
                    if (problemService.addProblem(problem) <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("新增失败");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("新增成功");
                    }
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("新增失败");
        }
        return result;
    }


    /**
     * 功能描述: 常见问题内容 标题修改
     *
     * @param request HttpServletRequest 服务类
     * @param problem 常见问题实体类
     * @return 返回修改结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/editProblem", method = RequestMethod.POST)
    @ResponseBody
    public Result editProblem(HttpServletRequest request, Problem problem) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务逻辑开始
                if (problem.getId() == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("参数不合法");
                } else {
                    if (problemService.editProblem(problem) <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }


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
    public Result selectProblemById(HttpServletRequest request, String problem_id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
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
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述: 查询所有常见问题信息
     *
     * @param request HttpServletRequest 服务类
     * @return 返回查询结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/selectProblemAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectProblemAll(HttpServletRequest request) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务逻辑开始
                List<Map<String, Object>> list = problemService.selectProblemAll();
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述:根据条件查询常见问题信息
     *
     * @param   request        请求
     * @param   response       响应
     * @param   limit          分页条目数
     * @param   page           分页数
     * @param   title          问题标题
     * @param   open_way_str   打开方式
     * @param   content        问题内容
     * @param   type_str       类别
     * @param   creation_time  创建时间
     * @return: Result         返回根据条件查询常见问题信息
     * @author: 刘武祥
     * @date: 2019/1/16 0016 17:43
     */
    @RequestMapping(value = "/adminSearchProblem", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchProblem(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   Integer limit,
                                                   Integer page,
                                                   String title,
                                                   String open_way_str,
                                                   String content,
                                                   Integer type_str,
                                                   String creation_time){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("start_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (title != null && !"".equals(title)) {
                    map.put("title", "%" + title + "%");
                }
                map.put("open_way_str", ("".equals(open_way_str) ? null : open_way_str) );
                if (content != null && !"".equals(content)){
                    map.put("content","%"+content+"%");
                }
                map.put("type_str", ("".equals(type_str) ? null : type_str) );
                map.put("creation_time",creation_time);
                List<Map<Object,Object>> list = this.problemService.adminSearchIntegralBusinessGoods(map);

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
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     * 功能描述: 删除常见问题 (逻辑删除)
     *
     * @param request HttpServletRequest 服务类
     * @return 返回查询结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @RequestMapping(value = "/deleteProblemById", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteProblemById(HttpServletRequest request, String id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务逻辑开始
                if (this.problemService.deleteProblemById(id) > 0) {
                    result.setData(null);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("删除成功");
                } else {
                    result.setData(null);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("删除失败");
                }
//                List<Map<String, Object>> list = problemService.selectProblemAll();
//                result.setData(list);
//                result.setStatus(Result.SUCCESS);
////                result.setMsg("查询成功");
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

}
