package com.springmvc.service;

import com.springmvc.entity.Users;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 会员管理服务层
 * @Author: 刘武祥
 * @Date: 2019/2/22 0022 15:44
 */
public interface IUsersService {
    /**
     * 通过手机号查询用户
     * @param mobile
     * @param login_pwd
     * @return
     */
    Users selectByMobileAndLoginPWD(String mobile, String login_pwd);

    /**
     * 通过id查询会员
     * @param user_id
     * @return
     */
    Users selectById(Integer user_id);

    /**
     * 通过wx_unionid查询会员
     * @param wx_unionid
     * @return
     */
    Users selectByWxUnionid(String wx_unionid);

    /**
     * 通过手机号查询会员
     * @param mobile
     * @return
     */
    Users selectByMobile(String mobile);

    /**
     * 用户手机号注册
     * @param users
     * @return
     */
    int userMobileRegister(Users users);

    /**
     * 通过用户id修改用户昵称
     * @param users
     * @return
     */
    int updateUserNickName(Users users);

    /**
     * 通过用户id修改用户手机号
     * @param users
     * @return
     */
    int updateUserMobile(Users users);

    /**
     * 通过用户id修改用户邮箱
     * @param users
     * @return
     */
    int updateUserEmail(Users users);

    /**
     * 通过用户id修改用户性别
     * @param users
     * @return
     */
    int updateUserSex(Users users);

    /**
     * 修改用户头像
     * @param users
     * @return
     */
    int updateUserHeader(Users users);

    /**
     * 修改登录密码
     * @param users
     * @return
     */
    int updateLoginPwd(Users users);

    /**
     * 修改支付密码
     * @param users
     * @return
     */
    int updatePayPwd(Users users);

    /**
     * 修改小额免密支付状态
     * @param users
     * @return
     */
    int updateIsMicrofinance(Users users);

    /**
     * 通过推荐人id查询用户
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectUserByRecommendUserId(Integer user_id);

    /**
     * 用户账号关联操作(relation_user_id-关联账号id，isrelation_user_id-被关联账号id,被关联账号为即将被删除的账号)
     * @param relation_user_id    关联账号id
     * @param isrelation_user_id  被关联账号id,被关联账号为即将被删除的账号
     * @return
     */
    int relationAccount(Integer relation_user_id,Integer isrelation_user_id);

    /**
     * 用户账号关联修改用户账号信息
     * @param users
     * @return
     */
    int relationAccountUpdateInformation(Users users);

    /**
     * 更新用户通用积分以及消费积分和银币余额
     * @param users
     * @return
     */
    int updateUserTYAndXFAndSilverCoinBalance(Users users);

    /**
     * 用户微信注册
     * @param users
     * @return
     */
    int userWXRegister(Users users);

    /**
     * 更新用户二维码
     * @param users
     * @return
     */
    int updateUserQrcode(Users users);

    /**
     * 更新用户等级id
     * @param users
     * @return
     */
    int updateUserMemberLevelId(Users users);

    /**
     * 用户兑换银币更新用户等级id
     * @param integralNumber
     * @param users
     * @return
     */
    int userExchangeSlivreCoinUpdateUserLevle(Integer integralNumber,Users users);

    /**
     * 查询用户最先推荐的前五位用户当前等级名称
     * @param recommend_user_id
     * @return
     */
    List<Map<Object,Object>> selectUserRecommendBeforeFiveUsersLevelName(Integer recommend_user_id);

    /**
     * 后台获取所有用户
     * @return
     */
    List<Map<Object,Object>> getAllUsersAdmin();

    /**
     * 更新用户openid
     * @param users
     * @return
     */
    int updateUserWXOpenId(Users users);

    /**
     * 更新用户会员卡等级
     * @param users
     * @return
     */
    int updateMemberCardLevel(Users users);

    /**
     * 通过推荐人id查询推荐人推荐的各个等级的会员卡
     * @param recomend_id
     * @param member_card_level
     * @return
     */
    List<Map<Object,Object>> selectByMemberCardByRecomendIdAndMemberCardLevel(Integer recomend_id,Integer member_card_level);

    /**
     * 查询用户最先推荐的前五位用户当前会员卡等级是银卡或金卡的用户
     * @param recomend_id
     * @return
     */
    List<Map<Object,Object>> selectUserRecommendBeforeFiveUsersMemberCardLevelYKOrJK(Integer recomend_id);

    /**
     * 查询用户最先推荐的前五位用户当前会员卡等级是金卡的用户
     * @param recomend_id
     * @return
     */
    List<Map<Object,Object>> selectUserRecommendBeforeFiveUsersMemberCardLevelJK(Integer recomend_id);

    /**
     * 更新用户积分商城积分
     * @param users
     * @return
     */
    int updateUserIntegralShoppingIntegral(Users users);

    /**
     *  更新用户平台积分
     * @param map <br>用户ID和用户剩余积分<br>
     *            integral - 剩余积分<br>
     *            user_id  - 用户ID<br>
     * @return 返回影响行数
     * @author 杨新杰
     */
    int updateUserIntegralById(Map<String,Object> map);

    /**
     *
     * 功能描述: 通过商家id查询商家推广的用户
     *
     * @param: business_id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/27 6:23 PM
     */
    List<Map<Object,Object>> selectUserByBusinessId(Integer business_id);

    /**
     *
     * 功能描述: 更新用户信息 (后台管理员更新用户信息专用)
     *
     * @param   users 用户信息实体类
     * @return  成功返回 > 0 失败返回 < 0
     * @author: 杨新杰
     * @date: 16:50 2018/11/28
     */
    int adminUpdateUserInfoById(Users users);

    /**
     *
     * 功能描述:更新用户推荐人id及推荐类别
     *
     * @param:  recommend_user_id
     * @param:  recommend_type
     * @param:  user_id
     * @return: 更新用户推荐人id及推荐类别
     * @author: zhangfan
     * @date: 2018/12/2 12:25 AM
     */
    int updateUserRecommendIdAndRecommendType(Integer recommend_user_id,Integer recommend_type,Integer user_id);

    /**
     *
     * 功能描述: 商家变更用户推荐人
     *
     * @param recommend_user_id 推荐人ID
     * @param recommend_type 推荐人类型(0-用户,1-商家)
     * @param user_id 用户ID
     * @return: 商家变更用户推荐人
     * @author: zhangfan
     * @date: 2018/12/16 10:08 PM
     */
    int businessChangeUserRecommend(Integer recommend_user_id,Integer recommend_type,Integer user_id);

    /**
     *
     * 功能描述: 更新用户个推APPID
     *
     * @param:  users 用户实体类
     * @return: 更新用户个推APPID
     * @author: zhangfan
     * @date: 2018/12/19 12:42 AM
     */
    int updateUserGeTuiAppId(Users users);

    /**
     *
     * 功能描述: 根据条件查询搜索会员用户
     *
     * @param   map 查询条件
     * @return: 会员用户
     * @author: 刘武祥
     * @date: 2019/1/9 0009 16:34
     */
    List<Map<String,Object>> adminSearchMemberByConditions(Map<String,Object> map);
}
