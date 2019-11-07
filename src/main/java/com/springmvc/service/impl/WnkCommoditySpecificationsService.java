package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessTypeManagementHotelMapper;
import com.springmvc.dao.WnkCommoditySpecificationsMapper;
import com.springmvc.entity.WnkBusinessTypeManagementHotel;
import com.springmvc.entity.WnkCommoditySpecifications;
import com.springmvc.service.IWnkCommoditySpecificationsService;
import com.springmvc.utils.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("/wnkCommoditySpecificationsService")
public class WnkCommoditySpecificationsService implements IWnkCommoditySpecificationsService{
    @Resource
    private WnkCommoditySpecificationsMapper wnkCommoditySpecificationsMapper;

    @Resource
    private WnkBusinessTypeManagementHotelMapper wnkBusinessTypeManagementHotelMapper;

    @Override
    public List<Map<Object, Object>> selectByCommodityId(Integer commodity_id) {
        return this.wnkCommoditySpecificationsMapper.selectByCommodityId(commodity_id);
    }

    @Override
    public WnkCommoditySpecifications selectById(Integer record_id) {
        return this.wnkCommoditySpecificationsMapper.selectById(record_id);
    }

    @Override
    public int insertNewRecord(WnkCommoditySpecifications wnkCommoditySpecifications) {
        return this.wnkCommoditySpecificationsMapper.insertNewRecord(wnkCommoditySpecifications);
    }

    @Override
    public List<Map<Object, Object>> selectByCommodityIdAndState(Integer commodity_id, Integer state) {
        return this.wnkCommoditySpecificationsMapper.selectByCommodityIdAndState(commodity_id, state);
    }

    @Override
    public int updateStateByRecordId(Integer state, Integer record_id) {
        return this.wnkCommoditySpecificationsMapper.updateStateByRecordId(state, record_id);
    }

    @Override
    public int updateNameByRecordId(String name, Integer record_id) {
        return this.wnkCommoditySpecificationsMapper.updateNameByRecordId(name, record_id);
    }

    /**
     * 功能描述: 更新商品规格信息
     *
     * @param wnkCommoditySpecifications@return
     * @author 杨新杰
     * @date 2018/11/17 16:17
     */
    @Override
    public int updateCommoditySpecificationsInfo(WnkCommoditySpecifications wnkCommoditySpecifications) {
        return this.wnkCommoditySpecificationsMapper.updateCommoditySpecificationsInfo(wnkCommoditySpecifications);
    }

    /**
     * 查询商品所有规格 包括禁用的
     *
     * @param id 商品ID
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectByCommodityIdAll(Integer id) {
        return this.wnkCommoditySpecificationsMapper.selectByCommodityIdByAll(id);
    }

    /**
     *
     * 功能描述: 更新规格库存
     *
     * @param wnkCommoditySpecifications 规格信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/27 4:50 PM
     */
    @Override
    public int updateSpecificationStock(WnkCommoditySpecifications wnkCommoditySpecifications) {
        return this.wnkCommoditySpecificationsMapper.updateSpecificationStock(wnkCommoditySpecifications);
    }

    /**
     *
     * 功能描述: 查询某个商家下所有执行万能卡权益的规格数据
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/27 5:53 PM
     */
    @Override
    public List<Map<Object, Object>> selectWnkGuiGeByBusinessId(Integer business_id) {
        return this.wnkCommoditySpecificationsMapper.selectWnkGuiGeByBusinessId(business_id);
    }

    /**
     *
     * 功能描述:查询某个商品下所有执行万能卡权益的规格数据
     *
     * @param commodity_id 商品id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/27 6:32 PM
     */
    @Override
    public List<Map<Object, Object>> selectWnkGuiGeByCommodity(Integer commodity_id) {
        return this.wnkCommoditySpecificationsMapper.selectWnkGuiGeByCommodity(commodity_id);
    }

    /**
     *
     * 功能描述: 查询某个商家下已开启万能卡权益的规格最低价
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/28 3:32 PM
     */
    @Override
    public Map<Object, Object> selectWnkGuiGeMinPriceByBusinessId(Integer business_id) {
        return this.wnkCommoditySpecificationsMapper.selectWnkGuiGeMinPriceByBusinessId(business_id);
    }

    /**
     *
     * 功能描述:查询某个商家下已启用的的规格最低价
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/28 3:42 PM
     */
    @Override
    public Map<Object,Object> selectWnkGuiGeStartByBusinessId(Integer business_id) {
        return this.wnkCommoditySpecificationsMapper.selectWnkGuiGeStartByBusinessId(business_id);
    }

    /**
     * 功能描述: 删除规格ID
     *
     * @param guige_id
     * @return
     * @author 杨新杰
     * @date 14:11 2018/12/30
     */
    @Override
    public int deleteBusinessCommodityGuiGeById(Integer guige_id) {
        return this.wnkCommoditySpecificationsMapper.deleteBusinessCommodityGuiGeById(guige_id);
    }

    /**
     * 方法说明:根据时间戳和商品ID获取对应日期的商品库存
     *
     * @param timeInMillis  时间戳
     * @param commoditiesId 商品ID
     * @return 查询到的结果
     * @author 杨新杰
     **/
    @Override
    public Map<Object, Object> selectCommoditiesInventoryByDateAndCommoditiesId(Long timeInMillis, Integer commoditiesId,Integer business_id) {
        List<Map<Object, Object>> maps;
        /*
         * 先查询对于时间戳有没有进行过房态管理重新制定了库存，如果没有则查询规格中的库存并返回
         */

        WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel = new WnkBusinessTypeManagementHotel();

        // 先把时间戳去除时分秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        date.setTime(timeInMillis);
        String format = sdf.format(date);

        wnkBusinessTypeManagementHotel.setTime_stamp(new Date(format).getTime());
        wnkBusinessTypeManagementHotel.setCommodities_id(commoditiesId);
        wnkBusinessTypeManagementHotel.setBusiness_id(business_id);
        WnkBusinessTypeManagementHotel w = this.wnkBusinessTypeManagementHotelMapper.selectHotelManagement(wnkBusinessTypeManagementHotel);
        if (w != null){
            Map<Object, Object> map = new HashMap<>(16);
            map.put("inventory",w.getInventory_num());
            return map;
        } else {
            maps = this.wnkCommoditySpecificationsMapper.selectByCommodityId(commoditiesId);
            return maps.get(0);
        }
    }

    /**
     * 方法说明:查询指定时间段之内指定商品的库存数量
     *
     * @param objectMap map包含的字段:
     *                  <p>joinTime      : 入住时间</p>
     *                  <p>outTime       : 退房时间</p>
     *                  <p>commoditiesId : 商品ID</p>
     *                  <p>businessId    : 商家ID</p>
     * @return 返回指定时间段内最小的库存数量
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    @Override
    public Integer selectDayInventoryNumberByCommoditiesId(Map<String, Object> objectMap) {
        // 获取参数
        Long joinTime = TimeUtil.delHMS((Long)objectMap.get("joinTime"));
        Long outTime  = TimeUtil.delHMS((Long)objectMap.get("outTime" ));
        Integer commoditiesId = (Integer)objectMap.get("commoditiesId");
        Integer businessID = (Integer)objectMap.get("businessId");
        // 两个时间段相隔几天
        Long dayNumber = (outTime-joinTime) / (1000 * 3600 * 24);

        WnkBusinessTypeManagementHotel w = new WnkBusinessTypeManagementHotel();
        w.setBusiness_id(businessID);
        w.setCommodities_id(commoditiesId);
        w.setTime_stamp(joinTime);

        List<Integer> sortingList = new LinkedList<>();

        for (int i = 0 ;i < dayNumber ; i++){
            WnkBusinessTypeManagementHotel objW = this.wnkBusinessTypeManagementHotelMapper.selectHotelManagement(w);
            if (objW == null){
                Map<Object, Object> objMap = this.wnkCommoditySpecificationsMapper.selectByCommodityId(commoditiesId).get(0);
                sortingList.add((Integer) objMap.get("inventory"));
            } else {
                sortingList.add(objW.getInventory_num());
            }

            // 加一天
            w.setTime_stamp(w.getTime_stamp() + (1000 * 3600 * 24));
        }

        // list排序
        // 默认排序(从小到大)
        Collections.sort(sortingList);
        return sortingList.get(0);
    }

    /**
     * 方法说明:更新指定时间段之内指定商品的库存数量
     *
     * @param objectMap map包含的字段:
     *                  <p>joinTime      : 入住时间</p>
     *                  <p>outTime       : 退房时间</p>
     *                  <p>commoditiesId : 商品ID</p>
     *                  <p>businessId    : 商家ID</p>
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    @Override
    public void updateDayInventoryNumberByCommoditiesIdAndJoinAndOutTimeAndBusinessId(Map<String, Object> objectMap){
        // 获取参数
        Long joinTime = TimeUtil.delHMS((Long)objectMap.get("joinTime"));
        Long outTime  = TimeUtil.delHMS((Long)objectMap.get("outTime" ));
        Integer commoditiesId = (Integer)objectMap.get("commoditiesId");
        Integer businessID = (Integer)objectMap.get("businessId");
        // 两个时间段相隔几天
        Long dayNumber = (outTime-joinTime) / (1000 * 3600 * 24);
        WnkBusinessTypeManagementHotel w = new WnkBusinessTypeManagementHotel();
        w.setBusiness_id(businessID);
        w.setCommodities_id(commoditiesId);
        w.setTime_stamp(joinTime);

        for (int i = 0 ;i < dayNumber ; i++){
            WnkBusinessTypeManagementHotel objW = this.wnkBusinessTypeManagementHotelMapper.selectHotelManagement(w);
            if (objW == null){
               // 查询规格信息
                List<Map<Object, Object>> maps = this.wnkCommoditySpecificationsMapper.selectByCommodityId(commoditiesId);
                if (maps.size() > 0){
                    Map<Object, Object> m = maps.get(0);
                    if ((Integer)m.get("inventory") != -1 ){
                        w.setInventory_num((Integer)m.get("inventory") - 1);
                        this.wnkBusinessTypeManagementHotelMapper.insertHotelManagement(w);
                    }
                } else {
                    objW .setInventory_num(objW.getInventory_num() - 1);
                    this.wnkBusinessTypeManagementHotelMapper.updateHotelManagement(w);
                }

            } else {
                objW.setInventory_num(objW.getInventory_num() - 1);
                this.wnkBusinessTypeManagementHotelMapper.updateHotelManagement(objW);
            }
            // 加一天
            w.setTime_stamp(w.getTime_stamp() + (1000 * 3600 * 24));
        }

    }

    /**
     * 方法说明:更新指定时间段之内指定商品的库存数量
     *
     * @param objectMap map包含的字段:
     *                  <p>joinTime      : 入住时间</p>
     *                  <p>outTime       : 退房时间</p>
     *                  <p>commoditiesId : 商品ID</p>
     *                  <p>businessId    : 商家ID</p>
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    @Override
    public void updateDayInventoryNumberByCommoditiesIdAndJoinAndOutTimeAndBusinessIdAndUp(Map<String, Object> objectMap){
        // 获取参数
        Long joinTime = TimeUtil.delHMS((Long)objectMap.get("joinTime"));
        Long outTime  = TimeUtil.delHMS((Long)objectMap.get("outTime" ));
        Integer commoditiesId = (Integer)objectMap.get("commoditiesId");
        Integer businessID = (Integer)objectMap.get("businessId");
        // 两个时间段相隔几天
        Long dayNumber = (outTime-joinTime) / (1000 * 3600 * 24);
        WnkBusinessTypeManagementHotel w = new WnkBusinessTypeManagementHotel();
        w.setBusiness_id(businessID);
        w.setCommodities_id(commoditiesId);
        w.setTime_stamp(joinTime);

        for (int i = 0 ;i < dayNumber ; i++){
            WnkBusinessTypeManagementHotel objW = this.wnkBusinessTypeManagementHotelMapper.selectHotelManagement(w);
            if (objW == null){
                // 查询规格信息
                List<Map<Object, Object>> maps = this.wnkCommoditySpecificationsMapper.selectByCommodityId(commoditiesId);
                if (maps.size() > 0){
                    Map<Object, Object> m = maps.get(0);
                    if ((Integer)m.get("inventory") != -1 ){
                        w.setInventory_num((Integer)m.get("inventory") + 1);
                        this.wnkBusinessTypeManagementHotelMapper.insertHotelManagement(w);
                    }
                } else {
                    objW .setInventory_num(objW.getInventory_num() + 1);
                    this.wnkBusinessTypeManagementHotelMapper.updateHotelManagement(w);
                }

            } else {
                objW.setInventory_num(objW.getInventory_num() + 1);
                this.wnkBusinessTypeManagementHotelMapper.updateHotelManagement(objW);
            }
            // 加一天
            w.setTime_stamp(w.getTime_stamp() + (1000 * 3600 * 24));
        }

    }


}
