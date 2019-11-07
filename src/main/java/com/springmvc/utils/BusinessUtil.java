package com.springmvc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商家工具类
 */
public class BusinessUtil {
    /**
     * 通过商家id查询商家信息
     * @param business_id 商家ID
     * @return 成功返回商家信息map,失败返回空的map
     */
    public static Map<Object,Object> getBusiness(Integer business_id){
        Map<Object,Object> map = null;
        Map<Object,Object> businessMap = BusinessUtil.getAllBusiness();
        List<Map<Object,Object>> list1 = (List)businessMap.get("list1");
        List<Map<Object,Object>> list2 = (List)businessMap.get("list2");
        List<Map<Object,Object>> list3 = (List)businessMap.get("list3");
        List<Map<Object,Object>> list4 = (List)businessMap.get("list4");
        List<Map<Object,Object>> list5 = (List)businessMap.get("list5");
        for (Integer index = 0;index < list1.size();index++){
            Map<Object,Object> map0 = list1.get(index);
            if (map0.get("id") == business_id){
                return map0;
            }
        }

        for (Integer index = 0;index < list2.size();index++){
            Map<Object,Object> map0 = list2.get(index);
            if (map0.get("id") == business_id){
                return map0;
            }
        }

        for (Integer index = 0;index < list3.size();index++){
            Map<Object,Object> map0 = list3.get(index);
            if (map0.get("id") == business_id){
                return map0;
            }
        }

        for (Integer index = 0;index < list4.size();index++){
            Map<Object,Object> map0 = list4.get(index);
            if (map0.get("id") == business_id){
                return map0;
            }
        }

        for (Integer index = 0;index < list5.size();index++){
            Map<Object,Object> map0 = list5.get(index);
            if (map0.get("id") == business_id){
                return map0;
            }
        }
        return map;
    }

    /**
     * 得到商家信息
     * @return
     */
    public static Map<Object,Object> getAllBusiness(){
        final String image_url = "/property_system/images/wx/business_banner/";
        Map<Object,Object> returnMap = new HashMap<Object,Object>();
        //电影演  0
        List<Map<Object,Object>> list1 = new ArrayList<Map<Object,Object>>();
        //健身  1
        List<Map<Object,Object>> list2 = new ArrayList<Map<Object,Object>>();
        //免费洗车  2
        List<Map<Object,Object>> list3 = new ArrayList<Map<Object,Object>>();
        //vip商家  3
        List<Map<Object,Object>> list4 = new ArrayList<Map<Object,Object>>();
        //美发 5
        List<Map<Object,Object>> list5 = new ArrayList<Map<Object,Object>>();

        returnMap.put("list1",list1);
        returnMap.put("list2",list2);
        returnMap.put("list3",list3);
        returnMap.put("list4",list4);
        returnMap.put("list5",list5);

        list1.add(BusinessUtil.scBusiness(0,"芒果影城",0,"曲靖芒果影城，作为曲靖市文化服务第一单位，国际五星级标准建设，采用世界顶尖3D放映技术和设备，多维立体音效，最佳观影视觉，全国同步上映，配备宽敞时尚的休息大厅和高档舒适的影厅坐骑，带给您无穷的电影乐趣和精神享受。",new ArrayList<String>(){{add(image_url+"mangguo1.jpg"); add(image_url+"mangguo2.jpg");}},"云南曲靖麒麟区建设路芒果商业街书苑彼岸A栋3楼（曲靖一中东门斜对面）","城中"));
        list1.add(BusinessUtil.scBusiness(15,"曲靖中影珠江源影城",0,"位于珠江源广场其占地10638平方米，工程预算投资1800万元。工程于2003年9月18日正式开工建设，整个影视中心由地下停车场、音乐广场、多功能电影厅等几部分组成。作为麒麟区城市建设的重点项目、麒麟区“十大民心工程”的珠江源影视中心，它的建成不仅进一步繁荣了麒麟区文化事业，极大的丰富了全区乃至全市人民的文化生活，同时也标志着全区文化产业改革与发展迈出了重大步伐。内设4个影厅共440个座位。",new ArrayList<String>(){{add(image_url+"zhujiangyuan_background1.jpg"); add(image_url+"zhujiangyuan_background2.jpg");add(image_url+"zhujiangyuan_background3.jpg");}},"云南省曲靖市麒麟南路218号","城中"));
        list1.add(BusinessUtil.scBusiness(16,"曲靖华夏4D国际影城",0,"顶级3D设备,让你身临其境!炫酷4D带你体验室内雨雪花香，4D电影能让你上天！入海！穿越丛林！与怪兽面对面！从平面时间进入绚丽斑，配有265平米的滇东第一巨幕,13.1声道立体环绕,堪比现场演奏的听觉冲击双机放映",new ArrayList<String>(){{add(image_url+"huaxiayingyuan_background1.jpg"); add(image_url+"huaxiayingyuan_background2.jpg");add(image_url+"huaxiayingyuan_background3.jpg");}},"曲靖市经济开发区人民工社剑桥中心15栋","西区"));
//        list1.add(BusinessUtil.scBusiness(1,"万达影城",0,"曲靖万达影城位于云南省曲靖市三江大道万达广场4F万达影城，是万达院线旗下的一家全新影城，也是曲靖第1家万达影城，总座位1387座，拥有408座IMAX影厅，五星级影院标准、现代化风格、时尚大气，影厅全部采用数字Reald3D放映机， 特色厅有IMAX、MX4D厅、儿童厅。位置：曲靖市三江大道万达广场四楼",new ArrayList<String>(){{add(image_url+"wanda1.jpg"); add(image_url+"wanda2.jpg");add(image_url+"wanda3.jpg");}}));

        list2.add(BusinessUtil.scBusiness(2,"博克•搏击主体健身会所",1,"博克搏击主题健身会所完善的设施，宽敞宜人的环境，有国内知名专业的新时尚。博克现已签约昆仑决联盟（中国原创性的功夫搏击国际赛事）；年底完成分会所的建设，完成曲靖连锁链。分会所占地5000余平方米，建设有专业的游泳馆、搏击训练基地、跆拳道训练基地、羽毛球馆、各式舞蹈房、 健身、休闲、娱乐、美容为体的vip制场所， 将配备国内顶级教练团队。",new ArrayList<String>(){{add(image_url+"boke1.jpg"); add(image_url+"boke2.jpg");add(image_url+"boke3.jpg");}},"曲靖市麒麟区翰林国际北50米（健军一号捷克健身）","西区"));
        list2.add(BusinessUtil.scBusiness(3,"健军一号国际健身会所-将军店",1,"健军一号是集运动、休闲娱乐于一身的多功能健身场所。会所由专业的搏击房、擂台区、跆拳道室、瑜伽房、动感操房、动感单车房、私教房、自由力量区、体测区、男女浴室、健身区构成，总营业面积逾3000平方米，设有100多个停车位，耗资500多万元。",new ArrayList<String>(){{add(image_url+"jianjun1.jpg"); add(image_url+"jianjun2.png");add(image_url+"jianjun3.jpg");}},"曲靖市麒麟区将军镇红绿灯路口（健军一号将军镇店）","东区"));
        list2.add(BusinessUtil.scBusiness(4,"拳行天下",1,"拳行天下是健军一号分会所，内设专业的搏击房、擂台区、跆拳道室、瑜伽房、动感操房、动感单车房、私教房、自由力量区、体测区、男女浴室、健身区。",new ArrayList<String>(){{add(image_url+"quanxing1.png"); add(image_url+"quanxing2.png");}},"曲靖市麒麟区麒麟西路25号速8酒店院内（拳行天下）","西区"));
        list2.add(BusinessUtil.scBusiness(5,"52健身",1,"专注专业健身，打造精英私教团队，坚持提供五星级的质量与服务，坚持以客为尊的服务态度与质量，坚持提供最贴近客人需要的健身理念；引导时代消费新潮流，带动健身消费新趋势；品牌大众化、品质专业化、管理标准化、服务个性化。",new ArrayList<String>(){{add(image_url+"52_01.jpg"); add(image_url+"52_02.jpg");add(image_url+"52_03.png");}},"曲靖市麒麟区麒麟南路144号新世纪商务酒店三楼（52运动健身）","城中"));

        list3.add(BusinessUtil.scBusiness(6,"新东恒一站式汽车服务中心",2,"曲靖首家养护式洗车，专业汽车美容方案提供服务商，我们会运用专业的技能与服务秉承专业化、规范化、流程化、科学化的运营方式以环保持续的美车方式呵护您的爱车，感受精护洗车以洗代养，专业美车方案为您及您的爱车带来洁净的空间与愉悦的驾乘乐趣。",new ArrayList<String>(){{add(image_url+"xindongheng.jpg"); }},"曲靖市麒麟区教场西路257号","西区"));
        list3.add(BusinessUtil.scBusiness(7,"博特洁汽车服务会所",2,"公司主要经营汽车精洗、美容、装饰、贴膜、钣金喷漆、快修快保、轮胎、保险等一站式服务。秉承“诚信、专业”的经营理念,坚持用户至上、质量第一。我们将以精湛的技术，一流的服务为您的爱车保驾护航，希望广大企业、消费者与我们联系、洽谈，我们会用优秀的产品和服务让您满意！",new ArrayList<String>(){{add(image_url+"boteji1.png"); add(image_url+"boteji2.png");}},"曲靖开发区翠峰西路锦福花园10幢3号（小区侧门）","西区"));

        list4.add(BusinessUtil.scBusiness(8,"绝对牛•风尚烤鱼馆",3,"绝对牛风尚烤鱼馆，餐饮新模式，魔术师般的手法，烧烤与火锅的结合，成就市场王者。绝对牛风尚烤鱼馆，口味特有秘方，菜品玩转时尚，多种经营方式，聚敛更多收益，高校、环保、节能的餐饮新时代。绝对牛风尚烤鱼馆无需经验和技术，拥有标准化的操作流程，经营更简单，有人气、有口碑、有生意的绝对牛！",new ArrayList<String>(){{add(image_url+"juduiniu1.jpg"); add(image_url+"juduiniu2.png");add(image_url+"juduiniu3.jpg");}},"曲靖开发区学府路金域蓝苑80栋-5号","西区"));

        list5.add(BusinessUtil.scBusiness(9,"漂亮百分百烫染吧(玄坛店)",5,"漂亮百分百烫染吧，始创于2008年。经过7年发展，励精图志，到今天已拥有烫发，染发，美发等业务，是个人时尚造型及经典创意发型的综合性服务机构。拥有实力的设计团队，设计师对百分百造型的设计理念均能让您感受到经典与时尚的结合之美，艺术性的思维讲空间中先进的、潮流元素完美融入东方人的特色发型中，展现在烫、染、造型中。",new ArrayList<String>(){{add(image_url+"piaoliangbaifenbai_xuantan1.jpg"); add(image_url+"piaoliangbaifenbai_xuantan2.jpg");add(image_url+"piaoliangbaifenbai_xuantan3.jpg");}},"曲靖市麒麟区玄坛路和麒麟南路交叉口玄坛商场2楼","城中"));
        list5.add(BusinessUtil.scBusiness(10,"漂亮百分百(交通路沃尔玛店)",5,"漂亮百分百创建于2008年7月1日,是个人时尚造型及经典创意发型的综合性服务机构。拥有实力的设计团队,设计师对百分百造型的设计理念均能让您感受到经典",new ArrayList<String>(){{add(image_url+"piaoliangbaifenbai_woerma1.jpg"); add(image_url+"piaoliangbaifenbai_woerma2.jpg");}},"交通路沃尔玛旁","城中"));
        list5.add(BusinessUtil.scBusiness(11,"漂亮百分百(麒麟巷店)酷嘛萌造型",5,"漂亮百分百创建于2008年7月1日,是个人时尚造型及经典创意发型的综合性服务机构。拥有实力的设计团队,设计师对百分百造型的设计理念均能让您感受到经典",new ArrayList<String>(){{add(image_url+"piaoliangbaifenbai_linqigang1.jpg"); add(image_url+"piaoliangbaifenbai_linqigang2.jpg");add(image_url+"piaoliangbaifenbai_linqigang3.jpg");}},"麒麟巷中段A-7号(工商技术学校斜对面)","城中"));
        list5.add(BusinessUtil.scBusiness(12,"漂亮百分百烫染吧(东星店)",5,"曲靖市麒麟区漂亮百分百烫染吧东星店办公室地址位于素有“滇黔锁钥”、“云南咽喉”之称的曲靖，云南省曲靖市麒麟区白石江街道东星小区1-2号商铺，于2017年09月18日在麒麟区市场监督管理局注册成立，在店铺发展壮大的1年里，我们始终为客户提供好的产品和技术支持、健全的售后服务，店铺主要经营美发服务，有好的产品和专业的销售和技术团队。",new ArrayList<String>(){{add(image_url+"piaoliangbaifenbai_dongxing1.jpg"); add(image_url+"piaoliangbaifenbai_dongxing1.jpg");add(image_url+"piaoliangbaifenbai_dongxing1.jpg");}},"东星小区路口1-2号商铺","东区"));
        list5.add(BusinessUtil.scBusiness(13,"漂亮百分百(万达店)",5,"漂亮百分百创建于2008年7月1日,是个人时尚造型及经典创意发型的综合性服务机构。拥有实力的设计团队,设计师对百分百造型的设计理念均能让您感受到经典",new ArrayList<String>(){{add(image_url+"piaoliangbaifenbai_wanda1.jpg"); add(image_url+"piaoliangbaifenbai_wanda2.jpg");add(image_url+"piaoliangbaifenbai_wanda3.jpg");}},"万达广场一号门四楼","西区"));
        list5.add(BusinessUtil.scBusiness(14,"漂亮百分百(沃尔玛停车场)",5,"漂亮百分百创建于2008年7月1日,是个人时尚造型及经典创意发型的综合性服务机构。拥有实力的设计团队,设计师对百分百造型的设计理念均能让您感受到经典",new ArrayList<String>(){{add(image_url+"piaoliangbaifenbai_woerma1.jpg"); add(image_url+"piaoliangbaifenbai_woerma2.jpg");}},"南片区沃尔玛停车场旁","南区"));


        return returnMap;
    }

    /**
     * 生成商家信息Map
     * @param id   商家ID
     * @param name 商家名称
     * @param type 商家类型(0-电影院,1-健身,2-免费洗车,3-vip商家)
     * @param brief
     * @param banners
     * @param address
     * @param area
     * @return
     */
    public static Map<Object,Object> scBusiness(Integer id,String name,Integer type,String brief,List<String> banners,String address,String area){
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put("id",id);
        map.put("name",name);
        //0-电影院,1-健身,2-免费洗车,3-vip商家
        map.put("type",type);
        map.put("brief",brief);
        map.put("banners",banners);
        map.put("address",address);
        map.put("area",area);

        return map;
    }
}
