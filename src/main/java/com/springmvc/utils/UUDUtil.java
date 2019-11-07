package com.springmvc.utils;

import java.util.Random;
import java.util.UUID;

/**
 * UUID工具类
 */
public class UUDUtil {

    /*
    * 通过UUID生成16位唯一订单号
    * */
    public static String getOrderIdByUUId() {
        int first = new Random(10).nextInt(8) + 1;
        System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

//    /*
//    *生成唯一推荐码
//    * */
//    public static String getRecommendCode(AgentService agentService) {
//        String maxCode = agentService.selectCurrntRecommendMaxCode();
//        if (maxCode == null){
//            return "AL000001";
//        }
//        else{
//            int codeValue = Integer.parseInt(maxCode.replaceAll("AL",""))+1;
//            String codeValueStr = String.valueOf(codeValue);
//            if (codeValueStr.length() < 6){
//                for (int index = 0;index<6;index++){
//                    if (codeValueStr.length() < 6){
//                        codeValueStr = "0"+codeValueStr;
//                    }
//                    else{
//                        break;
//                    }
//                }
//                codeValueStr = "AL"+codeValueStr;
//            }
//            else{
//                codeValueStr = "AL"+codeValueStr;
//            }
//            return codeValueStr;
//        }
//
//    }

    /*
    *生成唯一推荐码
    * */
    public static String getRecommendCode2(String maxCode) {
        if (maxCode == null){
            return "AL000001";
        }
        else{
            int codeValue = Integer.parseInt(maxCode.replaceAll("AL",""))+1;
            String codeValueStr = String.valueOf(codeValue);
            if (codeValueStr.length() < 6){
                for (int index = 0;index<6;index++){
                    if (codeValueStr.length() < 6){
                        codeValueStr = "0"+codeValueStr;
                    }
                    else{
                        break;
                    }
                }
                codeValueStr = "AL"+codeValueStr;
            }
            else{
                codeValueStr = "AL"+codeValueStr;
            }
            return codeValueStr;
        }

    }

}
