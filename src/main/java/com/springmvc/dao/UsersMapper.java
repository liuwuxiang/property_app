package com.springmvc.dao;

import com.springmvc.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 会员管理数据持久层
 * @Author: 刘武祥
 * @Date: 2019/2/22 0022 15:45
 */
public interface UsersMapper {

    /**
     *
     * 功能描述: 通过手机号查询用户
     *
     * @param   mobile      手机号
     * @param   login_pwd   密码
     * @return: 返回通过手机号查询出的用户
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:21
     */
    Users selectByMobileAndLoginPWD(String mobile,String login_pwd);

    /**
     *
     * 功能描述: 通过id查询会员
     *
     * @param   user_id  用户id
     * @return: 返回通过id查询出的会员
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:19
     */
    Users selectById(Integer user_id);

    /**
     *
     * 功能描述: 通过wx_unionid查询会员
     *
     * @param   wx_unionid
     * @return: 返回通过wx_unionid查询出的会员
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:16
     */
    Users selectByWxUnionid(String wx_unionid);

    /**
     *
     * 功能描述: 通过手机号查询会员
     *
     * @param   mobile 手机号
     * @return: 返回会员信息
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:15
     */
    Users selectByMobile(String mobile);

    /**
     *
     * 功能描述: 用户手机号注册
     *
     * @param   users 用户实体类
     * @return: 返回用户手机号注册
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:14
     */
    int userMobileRegister(Users users);

    /**
     *
     * 功能描述:通过用户id修改用户昵称
     *
     * @param   users  用户实体类
     * @return: 返回用户昵称
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:13
     */
    int updateUserNickName(Users users);

    /**
     *
     * 功能描述: 通过用户id修改用户手机号
     *
     * @param   users   用户实体类
     * @return: 返回用户手机号
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:12
     */
    int updateUserMobile(Users users);

    /**
     *
     * 功能描述:通过用户id修改用户邮箱
     *
     * @param   users   用户实体类
     * @return: 返回用户邮箱
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:11
     */
    int updateUserEmail(Users users);

    /** 
     *
     * 功能描述: 通过用户id修改用户性别
     *
     * @param   users   用户实体类
     * @return: 返回用户id修改的用户性别
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:10
     */
    int updateUserSex(Users users);

    /**
     *
     * 功能描述: 修改用户头像
     *
     * @param   users   用户实体类
     * @return: 返回修改后的用户头像
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:09
     */
    int updateUserHeader(Users users);

    /**
     *
     * 功能描述: 修改登录密码
     *
     * @param   users   用户实体类
     * @return: 返回修改后的登录密码
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:08
     */
    int updateLoginPwd(Users users);

    /**
     *
     * 功能描述: 修改支付密码
     *
     * @param   users   用户实体类
     * @return: 返回修改后的支付密码
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:08
     */
    int updatePayPwd(Users users);

    /**
     *
     * 功能描述: 修改小额免密支付状态
     *
     * @param   users   用户实体类
     * @return: 返回修改后的小额免密支付状态
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:07
     */
    int updateIsMicrofinance(Users users);
    //
    /**
     *
     * 功能描述: 通过推荐人id查询用户
     *
     * @param   user_id  用户id
     * @return: 返回推荐人id查询出的用户
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:05
     */
    List<Map<Object,Object>> selectUserByRecommendUserId(Integer user_id);

    /**
     *
     * 功能描述: 根据用户id删除记录
     *
     * @param   user_id     用户id
     * @return: 返回删除记录信息
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:04
     */
    int deleteRecordByUserId(Integer user_id);

    /**
     *
     * 功能描述: 用户账号关联修改用户账号信息
     *
     * @param   users 用户实体类
     * @return: 返回用户账号关联修改用户账号信息
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:03
     */
    int relationAccountUpdateInformation(Users users);

    /** 
     *
     * 功能描述: 通过用户id查询并修改推荐人id
     *
     * @param   user_id     用户id
     * @param   isUser_id   推荐人id
     * @return: 返回通过用户id查询并修改推荐人id
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:02
     */
    int selectUserUpdateRecommendUserId(Integer user_id,Integer isUser_id);

    /** 
     *
     * 功能描述: 更新用户通用积分以及消费积分和银币余额
     *
     * @param   users 用户实体
     * @return: 返回更新用户通用积分以及消费积分和银币余额
     * @author: 刘武祥
     * @date: 2019/1/8 0008 14:00
     */
    int updateUserTYAndXFAndSilverCoinBalance(Users users);

    /**
     *
     * 功能描述: 用户微信注册
     *
     * @param   users 用户实体类
     * @return: 返回用户微信注册
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:17
     */
    int userWXRegister(Users users);

    /**
     *
     * 功能描述: 更新用户二维码
     *
     * @param   users 用户实体类
     * @return: 返回更新用户二维码
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:16
     */
    int updateUserQrcode(Users users);

    /**
     *
     * 功能描述: 更新用户星级
     *
     * @param   users  用户实体类
     * @return: 返回更新用户星级
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:15
     */
    int updateUserMemberStar(Users users);

    /**
     *
     * 功能描述: 更新用户等级id
     *
     * @param   users   用户实体类
     * @return: 返回更新用户等级id
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:14
     */
    int updateUserMemberLevelId(Users users);

    /**
     *
     * 功能描述: 查询用户最先推荐的前五位用户当前等级名称
     *
     * @param   recommend_user_id
     * @return: 返回用户最先推荐的前五位用户当前等级名称
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:14
     */
    List<Map<Object,Object>> selectUserRecommendBeforeFiveUsersLevelName(Integer recommend_user_id);

    /** 
     *
     * 功能描述: 后台获取所有用户
     *
     * @return: 返回所有用户信息
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:13
     */
    List<Map<Object,Object>> getAllUsersAdmin();

    /** 
     *
     * 功能描述: 更新用户openid
     *
     * @param   users   用户实体类
     * @return: 更新用户openid
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:12
     */
    int updateUserWXOpenId(Users users);

    /** 
     *
     * 功能描述: 更新用户会员卡等级
     *
     * @param   users 用户实体
     * @return: 返回更新用户会员卡等级
    * @author: 刘武祥
     * @date: 2019/1/8 0008 13:11
     */
    int updateMemberCardLevel(Users users);

    /**
     *
     * 功能描述: 通过推荐人id查询推荐人推荐的各个等级的会员卡
     *
     * @param   recomend_id         推荐人id
     * @param   member_card_level   会员等级id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:09
     */
    List<Map<Object,Object>> selectByMemberCardByRecomendIdAndMemberCardLevel(Integer recomend_id,Integer member_card_level);

    /**
     *
     * 功能描述: 查询用户最先推荐的前五位用户当前会员卡等级是银卡或金卡的用户
     *
     * @param       recomend_id
     * @return:     查询用户最先推荐的前五位用户当前会员卡等级是银卡或金卡的用户
     * @author:     刘武祥
     * @date: 2019/1/8 0008 13:08
     */
    List<Map<Object,Object>> selectUserRecommendBeforeFiveUsersMemberCardLevelYKOrJK(Integer recomend_id);

    /**
     *
     * 功能描述:查询用户最先推荐的前五位用户当前会员卡等级是金卡的用户
     *
     * @param   recomend_id
     * @return: 返回用户最先推荐的前五位用户当前会员卡等级是金卡的用户
    * @author:  刘武祥
     * @date: 2019/1/8 0008 13:07
     */
    List<Map<Object,Object>> selectUserRecommendBeforeFiveUsersMemberCardLevelJK(Integer recomend_id);

    /**
     *
     * 功能描述: 更新用户积分商城积分
     *
     * @param   users 用户实体类
     * @return: 返回更新用户积分商城积分
     * @author: 刘武祥
     * @date: 2019/1/8 0008 13:06
     */
    int updateUserIntegralShoppingIntegral(Users users);

    /**
     *  更新用户积分
     * @param map 用户ID和用户剩余积分
     * @return 返回影响行数
     *
     * @author 杨新杰
     */
    int updateUserIntegralById(@Param("map") Map<String,Object> map);

    /**
     *
     * 功能描述: 通过商家id查询商家推广的用户
     *
     * @param: business_id  商家id
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
     * @return:
     * @author: zhangfan
     * @date: 2018/12/2 12:25 AM
     */
    int updateUserRecommendIdAndRecommendType(Integer recommend_user_id,Integer recommend_type,Integer user_id);

    /**
     *
     * 功能描述: 变更用户推荐人
     *
     * @param recommend_user_id 推荐人ID
     * @param recommend_type 推荐人类型(0-用户,1-商家)
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/16 10:08 PM
     */
    int changeUserRecommend(Integer recommend_user_id,Integer recommend_type,Integer user_id);

    /**
     *
     * 功能描述: 更新用户个推APPID
     *
     * @param:  users  用户实体类
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
     * @return: 返回搜索的会员用户
     * @author: 刘武祥
     * @date: 2019/1/9 0009 16:37
     */
    List<Map<String,Object>> adminSearchMemberByConditions(Map<String,Object> map);
}
