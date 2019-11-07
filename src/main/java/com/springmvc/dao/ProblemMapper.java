package com.springmvc.dao;

import com.springmvc.entity.Problem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 常见问题
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 10:19
 */
public interface ProblemMapper {

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
     * 功能描述:  修改常见问题
     *
     * @param problem 常见问题实体类
     * @return 返回修改条目数, 正常返回1
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    int editProblem(Problem problem);

    /**
     * 功能描述:  根据记录ID查询常见问题信息
     *
     * @param problem_id 常见问题记录ID (ID为NULL 则查询所有)
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    Map<String,Object> selectProblemById(@Param("problem_id") String problem_id);

    /**
     * 功能描述:  查询所有记录
     *
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    List<Map<String,Object>> selectProblemAll();

    /**
     * 功能描述:  查询所有已经启用的记录
     *
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date 2018/10/26 10:38
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
     * @param id 常见问题ID
     * @return 返回结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    int deleteProblemById(@Param("id") String id);

    /**
     *
     * 功能描述: 根据条件查询常见问题信息
     *
     * @param   map
     * @return: 返回查询到的常见问题信息
     * @author: 刘武祥
     * @date: 2019/1/16 0016 17:51
     */
    List<Map<Object,Object>> adminSearchIntegralBusinessGoods(Map<String,Object> map);
}
