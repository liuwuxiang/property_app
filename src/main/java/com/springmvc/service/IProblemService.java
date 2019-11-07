package com.springmvc.service;

import com.springmvc.entity.Problem;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/26 10:34
 */
public interface IProblemService {
    /**
     *
     * 功能描述:  新增常见问题
     *
     * @param  problem 常见问题实体类
     * @return 返回新增条目数, 正常返回1
     * @author 杨新杰
     * @date   2018/10/26 10:38
     */
    int addProblem(Problem problem);

    /**
     *
     * 功能描述:  修改常见问题
     *
     * @param  problem 常见问题实体类
     * @return 返回修改条目数, 正常返回1
     * @author 杨新杰
     * @date   2018/10/26 10:38
     */
    int editProblem(Problem problem);

    /**
     *
     * 功能描述:  根据记录ID查询常见问题信息
     *
     * @param  problem_id 常见问题记录ID
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date   2018/10/26 10:38
     */
    Map<String,Object> selectProblemById(String problem_id);

    /**
     *
     * 功能描述:  查询所有记录
     *
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date   2018/10/26 10:38
     */
    List<Map<String,Object>> selectProblemAll();

    /**
     *
     * 功能描述:  查询所有已经启用的记录
     *
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date   2018/10/26 10:38
     */
    List<Map<String,Object>> selectProblemAllByTrue();

    /**
     *
     * 功能描述: 查询所有已经启用的记录-用户查询
     *
     * @param:
     * @return: 返回查询到的信息
     * @author: zhangfan
     * @date: 2018/11/6 12:07 AM
     */
    List<Map<String,Object>> selectProblemAllByTrueUser();

    /**
     * 功能描述: 删除常见问题 (逻辑删除)
     *
     * @param  id 常见问题ID
     * @return 返回结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    int deleteProblemById(String id);

    /**
     *
     * 功能描述: 根据条件查询常见问题信息
     *
     * @param   map  查询条件
     * @return: 返回查询到的常见问题信息
     * @author: 刘武祥
     * @date: 2019/1/16 0016 17:48
     */
    List<Map<Object,Object>> adminSearchIntegralBusinessGoods(Map<String,Object> map);
}
