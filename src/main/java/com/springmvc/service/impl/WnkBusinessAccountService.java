package com.springmvc.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.dao.WnkBusinessAccountMapper;
import com.springmvc.entity.*;
import com.springmvc.service.IWnkBusinessAccountService;
import com.springmvc.utils.MobileCodeUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.springmvc.utils.Qrcode.createQrCodePic;
import static com.springmvc.utils.Qrcode.generateCode;

/**
 * @author 杨新杰
 */
@Service("/wnkBusinessAccountService")
public class WnkBusinessAccountService implements IWnkBusinessAccountService {
    @Resource
    private WnkBusinessAccountMapper wnkBusinessAccountMapper;
    @Resource
    private WnkBusinessLevelService wnkBusinessLevelService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private MyMaterielService myMaterielService;

    @Override
    public WnkBusinessAccount selectByMobileAndLoginPWD(String mobile, String login_pwd) {
        return this.wnkBusinessAccountMapper.selectByMobileAndLoginPWD(mobile, login_pwd);
    }

    @Override
    public WnkBusinessAccount selectByMobile(String mobile) {
        return this.wnkBusinessAccountMapper.selectByMobile(mobile);
    }

    @Override
    public WnkBusinessAccount selectById(Integer business_id) {
        return this.wnkBusinessAccountMapper.selectById(business_id);
    }

    @Override
    public int updateLoginPwd(Integer business_id, String new_login_pwd) {
        return this.wnkBusinessAccountMapper.updateLoginPwd(business_id, new_login_pwd);
    }

    @Override
    public int updatePayPwd(Integer business_id, String new_pay_pwd) {
        return this.wnkBusinessAccountMapper.updatePayPwd(business_id, new_pay_pwd);
    }

    @Override
    public List<Map<Object, Object>> selectAllRecord() {
        return this.wnkBusinessAccountMapper.selectAllRecord();
    }

    @Override
    public int insertBusiness(WnkBusinessAccount wnkBusinessAccount) {
        return this.wnkBusinessAccountMapper.insertBusiness(wnkBusinessAccount);
    }

    @Override
    public int updateAccountInformation(WnkBusinessAccount wnkBusinessAccount) {
        return this.wnkBusinessAccountMapper.updateAccountInformation(wnkBusinessAccount);
    }

    @Override
    public int updateBalance(Integer business_id, Double balance) {
        return this.wnkBusinessAccountMapper.updateBalance(business_id, balance);
    }

    @Override
    public int updateRelevantBalance(WnkBusinessAccount wnkBusinessAccount) {
        return this.wnkBusinessAccountMapper.updateRelevantBalance(wnkBusinessAccount);
    }

    /**
     * 功能描述: 查询所有特别推荐商户信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 9:40 PM
     */
    @Override
    public List<Map<Object, Object>> selectAllEspciallyRecommendRecord() {
        return this.wnkBusinessAccountMapper.selectAllEspciallyRecommendRecord();
    }

    /**
     * 功能描述: 商户注册完成自动添加注册信息
     *
     * @param wnkBusinessRegister 注册信息
     * @param login_pwd           默认登录密码
     * @return int = 1 ：注册成功
     * @author: zhangfan
     * @date: 2018/11/21 5:08 PM
     */
    @Override
    public int addWnkBusinessZDRegister(WnkBusinessRegister wnkBusinessRegister, String login_pwd) {
        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountMapper.selectByMobile(wnkBusinessRegister.getLogin_account());
        if (wnkBusinessAccount != null) {
            return 0;
        } else {
            wnkBusinessAccount = new WnkBusinessAccount();
            wnkBusinessAccount.setMobile(wnkBusinessRegister.getLogin_account());
            wnkBusinessAccount.setLogin_pwd(login_pwd);
            wnkBusinessAccount.setJoin_time(new Date());
            wnkBusinessAccount.setState(0);
            int addBusinessState = this.wnkBusinessAccountMapper.insertBusiness(wnkBusinessAccount);
            if (addBusinessState <= 0) {
                return -1;
            } else {
                WnkStoreInformation wnkStoreInformation = new WnkStoreInformation();
                wnkStoreInformation.setBusiness_id(wnkBusinessAccount.getId());
                wnkStoreInformation.setStore_name(wnkBusinessRegister.getStore_name());
                wnkStoreInformation.setAddress(wnkBusinessRegister.getAddress());
                wnkStoreInformation.setStore_describe(wnkBusinessRegister.getMiaoshu());
                wnkStoreInformation.setContact_mobile(wnkBusinessRegister.getContact_mobile());
                wnkStoreInformation.setBusiness_type_id(wnkBusinessRegister.getType_id());
                wnkStoreInformation.setState(0);
                wnkStoreInformation.setArea(wnkBusinessRegister.getArea());
                wnkStoreInformation.setBanners_photoid("");
                wnkStoreInformation.setRecommend_business_id(wnkBusinessRegister.getRecommend_business_id());
//                String wnkQrcodeUrl = "";
                //商户推广二维码
                String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                try {

                    String text = "http://m.chenlankeji.cn/property_system/wnk_business/joinBusinessRecommendRegister?business_id=" + wnkBusinessAccount.getId();
                    String logoPath = "/usr/local/property_system/images/logo.jpg";
//                    String logoPath = "D:\\property_system\\qrcode\\logo.jpg";
                    StringBuilder sb = new StringBuilder(logoPath);
                    BufferedImage sourceImg1 = ImageIO.read(new File("/usr/local/property_system/images/logo.jpg"));
                    sb.append(120).append("w_").append(120).append("h_1e_1c.src");
                    BufferedImage qrImg3 = generateCode(text, sb.toString(), 250, 250, 250, 250);
                    File file = new File(ImageToolsController.qrcodeURL+"/"+wnkQrcodeName+".png");
                    FileOutputStream out = new FileOutputStream(file);
                    createQrCodePic(250, 250, qrImg3, 250, 250, 0, 0, sourceImg1, 50, 50, 100, 100, out);

                } catch (Exception e) {
//                    wnkQrcodeUrl = "";
                    e.printStackTrace();
                }
                wnkStoreInformation.setRecommend_qr_code(wnkQrcodeName + ".png");

                //商户支付二维码
                String wnkQrcodeNamePay = UUDUtil.getOrderIdByUUId();
                Map<Object, Object> orderQrcodeMap = new HashMap<Object, Object>();
                orderQrcodeMap.put("business_id", wnkBusinessAccount.getId());
                JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                String wnkPayQrcodeName = UUDUtil.getOrderIdByUUId();
                try {
                    String wnkQrcodeUrlPay = QRCode.generateQRCode(json.toString(), wnkPayQrcodeName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                wnkStoreInformation.setPay_qr_code(wnkPayQrcodeName + ".png");

                WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectDefaultLevel();
                if (wnkBusinessLevel != null) {
                    wnkStoreInformation.setBusiness_level_id(wnkBusinessLevel.getId());
                }
                int addStoreState = this.wnkStoreInformationService.insertBusinessInformation(wnkStoreInformation);
                if (addStoreState <= 0) {
                    return -2;
                } else {
                    // 添加未认证信息
                    WnkBusinessLegalize wnkBusinessLegalize = new WnkBusinessLegalize();
                    wnkBusinessLegalize.setBusiness_id(wnkBusinessAccount.getId());
                    // 未上传认证信息 认证状态 -1:未上传认证信息 0 : 未认证 1:认证
                    wnkBusinessLegalize.setLegalize_status(-1);
                    wnkBusinessLegalize.setPhone(wnkBusinessAccount.getMobile());
                    if (this.wnkBusinessLegalizeService.insertLegalizeInfo(wnkBusinessLegalize) > 0) {

                        //赠送1000积分给商户
                        WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                        wnkBusinessLevelIntegralDetail.setBusiness_id(wnkBusinessAccount.getId());
                        wnkBusinessLevelIntegralDetail.setName("系统赠送积分");
                        wnkBusinessLevelIntegralDetail.setIntegral_number(1000.00);
                        wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                        wnkBusinessLevelIntegralDetail.setTransactions_type(0);
                        wnkBusinessAccount.setLevel_integral(wnkBusinessLevelIntegralDetail.getIntegral_number());
                        wnkBusinessAccount.setBalance(0.00);
                        wnkBusinessAccount.setConsumption_integral(0);
                        wnkBusinessAccount.setRose_number(0);
                        int updateBusinessIntegralState = this.wnkBusinessAccountMapper.updateRelevantBalance(wnkBusinessAccount);
                        if (updateBusinessIntegralState > 0) {
                            this.wnkBusinessLevelIntegralDetailService.insertNewRecord(wnkBusinessLevelIntegralDetail);
                        }
                        //赠送以及1张万能卡给商户
                        ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectWnkInformation();
                        if (extensionMateriel != null) {
                            MyMateriel myMateriel = new MyMateriel();
                            myMateriel.setBusiness_id(wnkBusinessAccount.getId());
                            myMateriel.setMateriel_id(extensionMateriel.getId());
                            myMateriel.setSurplus_number(1);
                            this.myMaterielService.insertMaterielBuyRecord(myMateriel);
                        }
                        try {
                            MobileCodeUtil.sendAccountRegisterPassSms(wnkBusinessRegister.getLogin_account(), login_pwd);
                        } catch (ClientException e) {
                            e.printStackTrace();
                        }
                        return 1;
                    } else {
                        return 1;
                    }

                }
            }
        }
    }

    /**
     * 功能描述: 根据条件查询商户信息
     *
     * @param maps
     * @return:
     * @author: 杨新杰
     * @date: 12:25 2018/11/29
     */
    @Override
    public List<Map<Object, Object>> adminSearchBusinessInfoByConditions(Map<String, Object> maps) {
        return this.wnkBusinessAccountMapper.adminSearchBusinessInfoByConditions(maps);
    }

    /**
     * 功能描述: 更新商家等级积分余额
     *
     * @param i
     * @return
     * @author: 杨新杰
     * @date: 11:00 2018/11/30
     */
    @Override
    public int updateBusinessLevelIntegral(Double i, Integer business_id) {
        Map<String,Object> map = new HashMap<>();
        map.put("levelIntegral",i);
        map.put("business_id",business_id);
        return this.wnkBusinessAccountMapper.updateBusinessLevelIntegral(map);
    }

    /**
     * 功能描述: 查询所有已下架的商户信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 9:32 PM
     */
    @Override
    public List<Map<Object, Object>> selectAllLowerRecord() {
        return this.wnkBusinessAccountMapper.selectAllLowerRecord();
    }

    /**
     * 功能描述: 查询所有商家(排除已删除的商家)
     *
     * @return 返回查询结果
     * @author 杨新杰
     * @date 10:20 2019/1/9
     */
    @Override
    public List<Map<String, Object>> selectBusinessAllExcludeDelete(Integer total,Integer limit,String store_name) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("total",total);
        map.put("limit",limit);
        if ("".equals(store_name) || store_name == null){
            map.put("store_name",null);
        } else {
            map.put("store_name","%" + store_name + "%");
        }
        return this.wnkBusinessAccountMapper.selectBusinessAllExcludeDelete(map);
    }

    /**
     *
     * 功能描述: 根据条件查询特别推荐商户信息
     *
     * @param   maps
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/14 0014 15:01
     */
    @Override
    public List<Map<Object, Object>> adminSearchEspeciallyRecommendWnkBusiness(Map<String, Object> maps) {
        return this.wnkBusinessAccountMapper.adminSearchEspeciallyRecommendWnkBusiness(maps);
    }


}
