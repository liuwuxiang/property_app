package com.springmvc.service.impl;

import com.springmvc.dao.AboutUsMapper;
import com.springmvc.entity.AboutUs;
import com.springmvc.service.IAboutUsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("/aboutUsService")
public class AboutUsService implements IAboutUsService{
    @Resource
    private AboutUsMapper aboutUsMapper;

    @Override
    public AboutUs selectAboutUs() {
        return this.aboutUsMapper.selectAboutUs();
    }

    @Override
    public AboutUs selectIntegralAbout(Integer type) {
        return this.aboutUsMapper.selectIntegralAbout(type);
    }

    @Override
    public int setIntegralAndCoin(String content) {
        return this.aboutUsMapper.setIntegralAndCoin(content);
    }

    @Override
    public int setIntegralAndRMB(String content) {
        return this.aboutUsMapper.setIntegralAndRMB(content);
    }

    /**
     *
     * 功能描述: 新增记录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 2:34 AM
     */
    @Override
    public int addRecord(AboutUs aboutUs) {
        return this.aboutUsMapper.addRecord(aboutUs);
    }

    /**
     *
     * 功能描述: 通过type更新记录信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/22 2:37 AM
     */
    @Override
    public int updateRecordInformation(AboutUs aboutUs) {
        return this.aboutUsMapper.updateRecordInformation(aboutUs);
    }

    /**
     * 根据类型查询 法律声明、价格声明、隐私政策、餐饮安全管理
     *
     * @param type
     * @return
     */
    @Override
    public Map<String, Object> selectAboutByType(String type) {
        return this.aboutUsMapper.selectAboutByType(type);
    }

    /**
     * 功能描述: 更新或者新增 法律声明/价格声明/隐私政策/餐饮安全管理内容
     *
     * @param type
     * @param about_content
     * @return
     * @author 杨新杰
     * @date 16:54 2018/12/11
     */
    @Override
    public int updateAboutInfo(String type, String about_content) {
        Map<String, Object> map = this.selectAboutByType(type);
        if (map == null){
            return this.aboutUsMapper.insertAboutInfo(type,about_content);
        } else {
            return this.aboutUsMapper.updateAboutInfo(type,about_content);
        }
    }

    /**
     * 功能描述: 平台积分兑换信息保存/更新
     *
     * @param exchange_bili       兑换比例
     * @param exchange_start_time 开发时间
     * @param exchange_shop_time  结束时间
     * @param min_exchange_number 最低兑换数量
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 11:34 2018/12/12
     */
    @Override
    public int updateIntegralExchangeInfo(String exchange_bili, String exchange_start_time, String exchange_shop_time, String min_exchange_number) {

        // 兑换比例 type - 23
        // 开放时间 type - 24
        // 结束时间 type - 25
        // 最低兑换数量 type - 26
        int i = -1;
        Map<String, Object> map = this.selectAboutByType("23");
        if (map == null){
           i = this.aboutUsMapper.insertAboutInfo("23",exchange_bili);
        } else {
           i = this.aboutUsMapper.updateAboutInfo("23",exchange_bili);
        }
        map = this.selectAboutByType("24");
        if (map == null){
            i = this.aboutUsMapper.insertAboutInfo("24",exchange_start_time);
        } else {
            i = this.aboutUsMapper.updateAboutInfo("24",exchange_start_time);
        }
        map = this.selectAboutByType("25");
        if (map == null){
            i = this.aboutUsMapper.insertAboutInfo("25",exchange_shop_time);
        } else {
            i = this.aboutUsMapper.updateAboutInfo("25",exchange_shop_time);
        }
        map = this.selectAboutByType("26");
        if (map == null){
            i = this.aboutUsMapper.insertAboutInfo("26",min_exchange_number);
        } else {
            i = this.aboutUsMapper.updateAboutInfo("26",min_exchange_number);
        }
        return i;
    }


}
