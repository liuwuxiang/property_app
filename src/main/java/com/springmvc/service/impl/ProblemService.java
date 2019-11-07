package com.springmvc.service.impl;

import com.springmvc.dao.ProblemMapper;
import com.springmvc.entity.Problem;
import com.springmvc.service.IProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 常见问题
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 10:14
 */
@Service("/problemService")
public class ProblemService implements IProblemService {


    @Resource
    private ProblemMapper problemMapper;

    /**
     *
     * 功能描述:  新增常见问题
     *
     * @param  problem 常见问题实体类
     * @return 返回新增条目数, 正常返回1
     * @author 杨新杰
     * @date   2018/10/26 10:38
     */
    @Override
    public int addProblem(Problem problem) {
        return problemMapper.addProblem(problem);
    }

    /**
     * 功能描述:  修改常见问题
     *
     * @param problem 常见问题实体类
     * @return 返回修改条目数, 正常返回1
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    @Override
    public int editProblem(Problem problem) {
        return problemMapper.editProblem(problem);
    }

    /**
     * 功能描述:  根据记录ID查询常见问题信息
     *
     * @param problem_id 常见问题记录ID
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    @Override
    public Map<String, Object> selectProblemById(String problem_id) {
        return problemMapper.selectProblemById(problem_id);
    }

    /**
     * 功能描述:  查询所有记录
     *
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    @Override
    public List<Map<String, Object>> selectProblemAll() {
        return problemMapper.selectProblemAll();
    }

    /**
     * 功能描述:  查询所有已经启用的记录
     *
     * @return 返回查询到的信息
     * @author 杨新杰
     * @date 2018/10/26 10:38
     */
    @Override
    public List<Map<String, Object>> selectProblemAllByTrue() {
        return problemMapper.selectProblemAllByTrue();
    }

    /**
     *
     * 功能描述: 查询所有已经启用的记录-用户查询
     *
     * @param:
     * @return: 返回查询到的信息
     * @author: zhangfan
     * @date: 2018/11/6 12:07 AM
     */
    @Override
    public List<Map<String, Object>> selectProblemAllByTrueUser() {
        return this.problemMapper.selectProblemAllByTrueUser();
    }

    /**
     * 功能描述: 删除常见问题 (逻辑删除)
     *
     * @param id 常见问题ID
     * @return 返回结果
     * @author 杨新杰
     * @date 2018/10/26 10:31
     */
    @Override
    public int deleteProblemById(String id) {
        return this.problemMapper.deleteProblemById(id);
    }

    /**
     *
     * 功能描述: 根据条件查询常见问题信息
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/16 0016 17:50
     */
    @Override
    public List<Map<Object, Object>> adminSearchIntegralBusinessGoods(Map<String, Object> map) {
        return this.problemMapper.adminSearchIntegralBusinessGoods(map);
    }
}
