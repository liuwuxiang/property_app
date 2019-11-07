package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.WnkCommoditiesExpandHotelService;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class CommodityWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkCommodityTypesService wnkCommodityTypesService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkCommoditiesExpandHotelService wnkCommoditiesExpandHotelService;

    // 添加商品
    @RequestMapping(value = "/addCommodity", method = RequestMethod.POST,params = {"business_id","name","price","type_id","describe","commodityPhotoId","state","is_make_wnk"})
    @ResponseBody
    public Result addCommodity(HttpServletRequest request, HttpServletResponse response, Integer business_id, String  name,  Double  price, Integer type_id, String  describe, String  commodityPhotoId, Integer state, Integer is_make_wnk, String  goods_tag){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    WnkCommodities wnkCommodities = new WnkCommodities();
                    wnkCommodities.setName(name);
                    wnkCommodities.setCommodity_describe(describe);
                    wnkCommodities.setPrice(price);
                    wnkCommodities.setState(state);
                    wnkCommodities.setPhoto(commodityPhotoId);
                    wnkCommodities.setBusiness_id(business_id);
                    wnkCommodities.setType_id(type_id);
                    wnkCommodities.setJoin_time(new Date());
                    wnkCommodities.setIs_make_wnk(is_make_wnk);

                    wnkCommodities.setGoods_tag(goods_tag);

                    int addState = this.wnkCommoditiesService.addCommodity(wnkCommodities);
                    if (addState >= 1){
                        Integer commodityId = wnkCommodities.getId();
                        result.setData(commodityId);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("添加成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("添加失败");
                    }

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 更新商户商品标签
    @RequestMapping(value = "/updateBusinessGoodsTagAction", method = RequestMethod.POST,params = {"business_id","goods_id","goods_tag_ids"})
    @ResponseBody
    public Result updateBusinessGoodsTagAction(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer goods_id,String goods_tag_ids){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (goods_tag_ids == null || goods_tag_ids.equals("")){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("你未选择标签");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(goods_id);
                    if (wnkCommodities == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                    }
                    else{
                        wnkCommodities.setGoods_tag(goods_tag_ids);
                        int updateState = this.wnkCommoditiesService.updateCommodityTag(wnkCommodities);
                        if (updateState > 0){
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("更新成功");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("更新失败");
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 获取某个分类下的商品
    @RequestMapping(value = "/getAllCommodityByTypeId", method = RequestMethod.POST,params = {"business_id","type_id"})
    @ResponseBody
    public Result getAllCommodityByTypeId(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer type_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.wnkCommoditiesService.selectAllCommodityByTypeId(type_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> map = list.get(index);
                            map.put("img_path", ImageToolsController.imageShowURL+"?imageid=");
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据商品ID修改商品删除状态
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:55 PM
     */
    @RequestMapping(value = "/updateDeleteStateByCommodityId", method = RequestMethod.POST,params = {"business_id","commodity_id"})
    @ResponseBody
    public Result updateDeleteStateByCommodityId(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer commodity_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                    }
                    else if (wnkCommodities.getBusiness_id() != business_id){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("无权操作此商品");
                    }
                    else{
                        int deleteState = this.wnkCommoditiesService.updateDeleteStateByCommodityId(1,commodity_id);
                        if (deleteState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("删除失败");
                        }
                        else{
                            Map<Object,Object> returnMap = new HashMap<Object,Object>();
                            returnMap.put("type_id",wnkCommodities.getType_id());
                            result.setData(returnMap);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("删除成功");
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 获取商品详情
    @RequestMapping(value = "/getCommodityDetail", method = RequestMethod.POST,params = {"business_id","commodity_id"})
    @ResponseBody
    public Result getCommodityDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer commodity_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商品不存在");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("name",wnkCommodities.getName());
                        map.put("price",wnkCommodities.getPrice());
                        map.put("type_id",wnkCommodities.getType_id());
                        map.put("commodity_describe",wnkCommodities.getCommodity_describe());
                        map.put("state",wnkCommodities.getState());
                        map.put("is_make_wnk",wnkCommodities.getIs_make_wnk());
                        map.put("photo_id",wnkCommodities.getPhoto());
                        map.put("local_photo",wnkCommodities.getPhoto());
                        map.put("goods_tag",wnkCommodities.getGoods_tag());
                        map.put("img_path",ImageToolsController.imageShowURL+"?imageid=");

                        // 获取商品规格
                        List<Map<Object, Object>> maps = wnkCommoditySpecificationsService.selectByCommodityIdAll(wnkCommodities.getId());
                        map.put("goods_guige",maps);
                        // 获取商家是否启用万能卡权益
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                        map.put("make_wnk_state",wnkBusinessType.getMake_wnk_state());

                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 修改商品信息
    @RequestMapping(value = "/setCommodityInformation", method = RequestMethod.POST,params = {"business_id","commodity_id","name","commodity_describe","price","photo","type_id","state","is_make_wnk","goods_tag"})
    @ResponseBody
    public Result setCommodityInformation(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer commodity_id,String name,String commodity_describe,Double price,String photo,Integer type_id,Integer state,Integer is_make_wnk,String goods_tag){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商品不存在");
                    }
                    else{
                        wnkCommodities.setName(name);
                        wnkCommodities.setCommodity_describe(commodity_describe);
                        wnkCommodities.setPrice(price);
                        wnkCommodities.setPhoto(photo);
                        wnkCommodities.setType_id(type_id);
                        wnkCommodities.setState(state);
                        wnkCommodities.setIs_make_wnk(is_make_wnk);
                        wnkCommodities.setGoods_tag(goods_tag);
                        int updateState = this.wnkCommoditiesService.updateCommodity(wnkCommodities);
                        if (updateState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("修改失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("修改成功");
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



    // 修改商品信息
    @RequestMapping(value = "/deleteBusinessCommodityGuiGeById", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteBusinessCommodityGuiGeById(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer guige_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    if ( this.wnkCommoditySpecificationsService.deleteBusinessCommodityGuiGeById(guige_id) > 0){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("删除成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("删除失败");
                    }

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



    /**
     *
     * 功能描述: 新增商品(酒店类商家)
     *
     * @param   wnkCommodities              商家商品实体类
     * @param   wnkCommoditySpecifications  商家商品规格实体类
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:19 2019/1/17
     */
    @RequestMapping(value = "/addCommodityByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result addCommodityByHotel(HttpServletRequest request, HttpServletResponse response,WnkCommodities wnkCommodities,WnkCommoditySpecifications wnkCommoditySpecifications,WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel){
        try {
            if (LoginSessionCheckUtil.checkIsLogin(wnkCommodities.getBusiness_id(),3,request,response,this.sessionIdsService) == null){
                return new Result(Result.NOLOGIN,"此账户暂未登录",null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id());
            if (wnkBusinessAccount == null){
                return new Result(Result.FAIL,"此商家不存在",null);
            }
            // 业务开始

            // 1. 新增商品
            wnkCommodities.setJoin_time(new Date());
            wnkCommodities.setIs_make_wnk(1);
            if (this.wnkCommoditiesService.addCommodity(wnkCommodities) <= 0){
                return new Result(Result.FAIL,"添加失败",null);
            }
            // 2. 新增规格
            wnkCommoditySpecifications.setCommodity_id(wnkCommodities.getId());
            wnkCommoditySpecifications.setIs_wnk(1);
            if (this.wnkCommoditySpecificationsService.insertNewRecord(wnkCommoditySpecifications) <= 0){
                return new Result(Result.FAIL,"商品添加成功,但商品规格和扩展信息添加失败",null);
            }

            wnkCommoditiesExpandHotel.setCommodities_id(wnkCommodities.getId());
            // 3. 新增规格扩展信息
            if (this.wnkCommoditiesExpandHotelService.insertExpandInfo(wnkCommoditiesExpandHotel) <= 0){
                return new Result(Result.FAIL,"商品和商品规格添加成功,但扩展信息添加失败",null);
            }
            return new Result(Result.SUCCESS,"添加成功",null);
        }catch (Exception e){
            return new Result(Result.FAIL,"操作失败",e.getMessage());
        }
    }



    /**
     *
     * 功能描述: 查询商品信息包括扩展信息(酒店类)
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:19 2019/1/17
     */
    @RequestMapping(value = "/selectCommodityInfoAndCommodityExpandHotelInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCommodityInfoAndCommodityExpandHotelInfoById(HttpServletRequest request, HttpServletResponse response,Integer businessId,Integer commodityId){
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId,3,request,response,this.sessionIdsService) == null){
                return new Result(Result.NOLOGIN,"此账户暂未登录",null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            if (wnkBusinessAccount == null){
                return new Result(Result.FAIL,"此商家不存在",null);
            }
            // 业务开始
            List<Object> list = new LinkedList<>();
            // 1.获取商品信息
            WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodityId);
            list.add(wnkCommodities);
            // 2.获取商品规格信息
            List<Map<Object, Object>> maps = this.wnkCommoditySpecificationsService.selectByCommodityId(commodityId);
            list.add(maps.get(0));
            // 3.获取商品扩展信息
            WnkCommoditiesExpandHotel w = this.wnkCommoditiesExpandHotelService.selectCommoditiesExpandHotelByCommoditiesId(commodityId);
            list.add(w);
            list.add(ImageToolsController.imageShowURL+"?imageid=");
            return new Result(Result.SUCCESS,"查询成功",list);
        }catch (Exception e){
            return new Result(Result.FAIL,"操作失败",e.getMessage());
        }
    }


    /**
     *
     * 功能描述: 更新商品(酒店类商家)
     *
     * @param   wnkCommodities              商家商品实体类
     * @param   wnkCommoditySpecifications  商家商品规格实体类
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:19 2019/1/17
     */
    @RequestMapping(value = "/updateCommodityByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCommodityByHotel(HttpServletRequest request, HttpServletResponse response,WnkCommodities wnkCommodities,WnkCommoditySpecifications wnkCommoditySpecifications,WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel,Integer specifications_id){
        try {
            if (LoginSessionCheckUtil.checkIsLogin(wnkCommodities.getBusiness_id(),3,request,response,this.sessionIdsService) == null){
                return new Result(Result.NOLOGIN,"此账户暂未登录",null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id());
            if (wnkBusinessAccount == null){
                return new Result(Result.FAIL,"此商家不存在",null);
            }
            // 业务开始

            // 1. 修改商品
            wnkCommodities.setIs_make_wnk(1);
            if (this.wnkCommoditiesService.updateCommodity(wnkCommodities) <= 0){
                return new Result(Result.FAIL,"修改失败",null);
            }
            // 2. 修改规格
            wnkCommoditySpecifications.setId(specifications_id);
            wnkCommoditySpecifications.setCommodity_id(wnkCommodities.getId());
            wnkCommoditySpecifications.setIs_wnk(1);
            int i = this.wnkCommoditySpecificationsService.updateCommoditySpecificationsInfo(wnkCommoditySpecifications);
            if (i <= 0){
                return new Result(Result.FAIL,"商品修改成功,但商品规格和扩展信息修改失败",null);
            }

            // 先查询扩展信息是否存在
            WnkCommoditiesExpandHotel w = wnkCommoditiesExpandHotelService.selectCommoditiesExpandHotelByCommoditiesId(wnkCommodities.getId());
            if (w == null){
                wnkCommoditiesExpandHotel.setCommodities_id(wnkCommodities.getId());
                if (wnkCommoditiesExpandHotelService.insertExpandInfo(wnkCommoditiesExpandHotel) <= 0){
                    return new Result(Result.FAIL,"商品和商品规格修改成功,但扩展信息修改失败",null);
                }
            } else {
                wnkCommoditiesExpandHotel.setCommodities_id(wnkCommodities.getId());
                // 3. 新增规格信息
                if (this.wnkCommoditiesExpandHotelService.updateExpandInfo(wnkCommoditiesExpandHotel,wnkCommodities.getBusiness_id(),wnkCommodities.getId(),wnkCommoditySpecifications.getInventory()) <= 0){
                    return new Result(Result.FAIL,"商品和商品规格修改成功,但扩展信息修改失败",null);
                }
            }
            return new Result(Result.SUCCESS,"修改成功",null);
        }catch (Exception e){
            return new Result(Result.FAIL,"操作失败",e.getMessage());
        }
    }

}
