package com.springmvc.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaBen<br>
 * 用于返回数据
 *
 * @author 杨新杰
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    /**状态:成功*/
    public static final Integer SUCCESS = 0;
    /**状态:失败*/
    public static final Integer FAIL = 1;
    /**状态:未登录*/
    public static final Integer NOLOGIN = 2;

    private Integer status = SUCCESS;

    private String msg = "";

    private Object data;

    /**用于分页查询时给出总条目数*/
    private Object count;

    /**
     * 实例化
     * 默认status为成功
     */
    public Result(){}

    /**
     * 实例化时直接赋值
     * @param status 状态
     * @param msg    消息内容
     * @param data   附带消息
     */
    public Result(Integer status,String msg,Object data){
        this.status = status;
        this.msg    = msg;
        this.setData(data);
        this.count  = 0;
    }

    /**
     * 实例化时直接赋值
     * @param status 状态
     * @param msg    消息内容
     * @param data   附带消息
     * @param count  分页查询时候返回总条目数
     */
    public Result (Integer status,String msg,Object data,Object count){
        this.status = status;
        this.msg    = msg;
        this.setData(data);
        this.count  = count;
    }

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        // 判断是否为int
        if (data instanceof String){
            if (data.equals("")){
                Map<Object,Object> map = new HashMap<Object,Object>();
                this.data = map;
            }
            else{
                this.data = data;
            }
        }
        else{
            this.data = data;
        }
    }
}

