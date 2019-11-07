package com.springmvc.service.impl;

import com.springmvc.dao.CarouselImgMapper;
import com.springmvc.entity.CarouselImg;
import com.springmvc.service.ICarouselImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/10 14:58
 */
@Service("/carouselImgService")
public class CarouselImgService implements ICarouselImgService {

    @Resource
    private CarouselImgMapper carouselImgMapper;

    /**
     * 功能描述: 获取所有轮播图信息
     *
     * @return 查询到的轮播图信息
     * @author 杨新杰
     * @date 15:02 2018/12/10
     */
    @Override
    public List<Map<String, Object>> selectCarouselInfoAll() {
        return this.carouselImgMapper.selectCarouselInfoAll();
    }

    /**
     * 功能描述: 根据ID获取轮播图信息
     *
     * @param carousel_id
     * @return 查询到的轮播图信息
     * @author 杨新杰
     * @date 15:04 2018/12/10
     */
    @Override
    public Map<String, Object> selectCarouselInfoById(String carousel_id) {
        return this.carouselImgMapper.selectCarouselInfoById(carousel_id);
    }

    /**
     * 功能描述: 修改轮播图信息
     *
     * @param carouselImg 轮播图实体类
     * @return 成功返回1 失败返回0, 返回值大于1则是SQL有问题
     * @author 杨新杰
     * @date 15:05 2018/12/10
     */
    @Override
    public int updateCarouselInfoById(CarouselImg carouselImg) {
        return this.carouselImgMapper.updateCarouselInfoById(carouselImg);
    }

    /**
     * 功能描述: 新增轮播图信息
     *
     * @param carouselImg 轮播图实体类
     * @return 成功返回1 失败返回0, 返回值大于1则是SQL有问题
     * @author 杨新杰
     * @date 15:05 2018/12/10
     */
    @Override
    public int insertCarouselInfo(CarouselImg carouselImg) {
        return this.carouselImgMapper.insertCarouselInfo(carouselImg);
    }

    /**
     *
     * 功能描述: 根据条件查询轮播图管理
     *
     * @param   map 查询条件
     * @return: 返回轮播图管理信息
     * @author: 刘武祥
     * @date: 2019/1/18 0018 17:49
     */
    @Override
    public List<Map<Object, Object>> adminSearchCarouselImg(Map<String, Object> map) {
        return this.carouselImgMapper.adminSearchCarouselImg(map);
    }
}
