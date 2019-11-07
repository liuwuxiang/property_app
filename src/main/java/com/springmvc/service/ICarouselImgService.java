package com.springmvc.service;

import com.springmvc.entity.CarouselImg;

import java.util.List;
import java.util.Map;

public interface ICarouselImgService {
    /**
     *
     * 功能描述: 获取所有轮播图信息
     *
     * @return  查询到的轮播图信息
     * @author  杨新杰
     * @date    15:02 2018/12/10
     */
    List<Map<String,Object>> selectCarouselInfoAll();

    /**
     *
     * 功能描述: 根据ID获取轮播图信息
     *
     * @return  查询到的轮播图信息
     * @author  杨新杰
     * @date    15:04 2018/12/10
     */
    Map<String,Object> selectCarouselInfoById(String carousel_id);

    /**
     *
     * 功能描述: 修改轮播图信息
     *
     * @param   carouselImg 轮播图实体类
     * @return  成功返回1 失败返回0, 返回值大于1则是SQL有问题
     * @author  杨新杰
     * @date    15:05 2018/12/10
     */
    int updateCarouselInfoById(CarouselImg carouselImg);

    /**
     *
     * 功能描述: 新增轮播图信息
     *
     * @param   carouselImg 轮播图实体类
     * @return  成功返回1 失败返回0, 返回值大于1则是SQL有问题
     * @author  杨新杰
     * @date    15:05 2018/12/10
     */
    int insertCarouselInfo(CarouselImg carouselImg);

    /**
     *
     * 功能描述: 根据条件查询轮播图管理
     *
     * @param   map 查询条件
     * @return: 返回轮播图管理信息
     * @author: 刘武祥
     * @date: 2019/1/18 0018 17:49
     */
    List<Map<Object,Object>> adminSearchCarouselImg(Map<String,Object> map);
}
