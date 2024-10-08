package com.wyl.cosystem.service;

import com.wyl.cosystem.entity.CustTimeInfo;
import com.wyl.cosystem.entity.PushRatioInfo;
import com.wyl.cosystem.entity.RiskRatioInfo;
import com.wyl.cosystem.mapper.QueryMapper;
import com.wyl.cosystem.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ThreeCustService {
    private static final String CUSTTIME_CACHE_KEY_PREFIX = "custTime:" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private static final String PUSHRATIO_CACHE_KEY_PREFIX = "pushRatio";
    private static final String RISKRATIO_CACHE_KEY_PREFIX = "riskRatio";
    private static final String PROVINCE_CACHE_KEY_PREFIX = "province:";

    public static final Map<String, String> provinceMap = new HashMap<>();

    @Resource
    private QueryMapper queryMapper;

    @Autowired
    private RedisUtils redisUtils;

    static {
        provinceMap.put("北京市", "beijing");
        provinceMap.put("天津市", "tianjin");
        provinceMap.put("河北省", "hebei");
        provinceMap.put("山西省", "shanxi");
        provinceMap.put("内蒙古自治区", "neimenggu");
        provinceMap.put("辽宁省", "liaoning");
        provinceMap.put("吉林省", "jilin");
        provinceMap.put("黑龙江省", "heilongjiang");
        provinceMap.put("上海市", "shanghai");
        provinceMap.put("江苏省", "jiangsu");
        provinceMap.put("浙江省", "zhejiang");
        provinceMap.put("安徽省", "anhui");
        provinceMap.put("福建省", "fujian");
        provinceMap.put("江西省", "jiangxi");
        provinceMap.put("山东省", "shandong");
        provinceMap.put("河南省", "henan");
        provinceMap.put("湖北省", "hubei");
        provinceMap.put("湖南省", "hunan");
        provinceMap.put("广东省", "guangdong");
        provinceMap.put("广西壮族自治区", "guangxi");
        provinceMap.put("海南省", "hainan");
        provinceMap.put("重庆市", "chongqin");
        provinceMap.put("四川省", "sichuan");
        provinceMap.put("贵州省", "guizhou");
        provinceMap.put("云南省", "yunnan");
        provinceMap.put("西藏自治区", "xizang");
        provinceMap.put("陕西省", "shanxi1");
        provinceMap.put("甘肃省", "gansu");
        provinceMap.put("青海省", "qinghai");
        provinceMap.put("宁夏回族自治区", "ningxia");
        provinceMap.put("新疆维吾尔自治区", "xinjiang");
    }

    public void setCustTime() {
        boolean b = redisUtils.hasKey(CUSTTIME_CACHE_KEY_PREFIX);
        if (b) {
            return;
        }
        List<CustTimeInfo> timeList = queryMapper.getTimeList();
//        System.out.println(timeList.size());
        Map<String, Object> collect = timeList.stream().collect(Collectors.toMap(CustTimeInfo::getCustId, CustTimeInfo::getTimes));
//        String jsonString = JSON.toJSONString(collect);
//        redisUtils.set(CUSTTIME_CACHE_KEY_PREFIX,jsonString,24*60*60);
        redisUtils.hmset(CUSTTIME_CACHE_KEY_PREFIX, collect, 24 * 60 * 60);

        System.out.println("OK");

    }

    public void set1CustTime(String item, Integer value) {
        boolean b = redisUtils.hasKey(CUSTTIME_CACHE_KEY_PREFIX);
        if (b) {
            return;
        }
        List<CustTimeInfo> timeList = queryMapper.getTimeList();
//        System.out.println(timeList.size());
//        Map<String, Object> collect = timeList.stream().collect(Collectors.toMap(CustTimeInfo::getCustId, CustTimeInfo::getTimes));
//        String jsonString = JSON.toJSONString(collect);
//        redisUtils.set(CUSTTIME_CACHE_KEY_PREFIX,jsonString,24*60*60);
//        redisUtils.hmset(CUSTTIME_CACHE_KEY_PREFIX,collect,24*60*60);
        redisUtils.hset(CUSTTIME_CACHE_KEY_PREFIX, item, value);
        System.out.println("OK");

    }

    public Map<Object, Object> getCustTime() {
        this.setCustTime();
//        String o = (String) redisUtils.get(CUSTTIME_CACHE_KEY_PREFIX);
//        Map<String, Integer> stringObjectMap = JSON.parseObject(o, new TypeReference<Map<String, Integer>>() {
//        });
        Map<Object, Object> stringObjectMap = redisUtils.hmget(CUSTTIME_CACHE_KEY_PREFIX);
        return stringObjectMap;
    }

    public Object get1CustTime(String item) {
        this.setCustTime();
//        String o = (String) redisUtils.get(CUSTTIME_CACHE_KEY_PREFIX);
//        Map<String, Integer> stringObjectMap = JSON.parseObject(o, new TypeReference<Map<String, Integer>>() {
//        });
//        Map<Object, Object> stringObjectMap = redisUtils.hmget(CUSTTIME_CACHE_KEY_PREFIX);
        Object hget = redisUtils.hget(CUSTTIME_CACHE_KEY_PREFIX, item);
        return hget;
    }

    public void setPushRatio() {
        boolean b = redisUtils.hasKey(PUSHRATIO_CACHE_KEY_PREFIX);
        if (b) {
            return;
        }
        List<PushRatioInfo> timeList = queryMapper.getPushRatio();
//        System.out.println(timeList.size());
        Map<String, Object> collect = timeList.stream().collect(Collectors.toMap(PushRatioInfo::getCustId, PushRatioInfo::getPushRatio));
//        String jsonString = JSON.toJSONString(collect);
//        redisUtils.set(PUSHRATIO_CACHE_KEY_PREFIX,jsonString,24*60*60);
        redisUtils.hmset(PUSHRATIO_CACHE_KEY_PREFIX, collect, 24 * 60 * 60);

        System.out.println("OK");

    }

    public Map<Object, Object> getPushRatio() {
        this.setPushRatio();
//        String o = (String) redisUtils.get(PUSHRATIO_CACHE_KEY_PREFIX);
//        Map<String, Double> stringObjectMap = JSON.parseObject(o, new TypeReference<Map<String, Double>>() {
//        });
        Map<Object, Object> stringObjectMap = redisUtils.hmget(PUSHRATIO_CACHE_KEY_PREFIX);
        return stringObjectMap;
    }

    public Object get1PushRatio(String item) {
        this.setPushRatio();
//        String o = (String) redisUtils.get(PUSHRATIO_CACHE_KEY_PREFIX);
//        Map<String, Double> stringObjectMap = JSON.parseObject(o, new TypeReference<Map<String, Double>>() {
//        });
//        Map<Object, Object> stringObjectMap = redisUtils.hmget(PUSHRATIO_CACHE_KEY_PREFIX);
        Object hget = redisUtils.hget(PUSHRATIO_CACHE_KEY_PREFIX, item);
        return hget;
    }

    public void setRiskRatio() {
        boolean b = redisUtils.hasKey(RISKRATIO_CACHE_KEY_PREFIX);
        if (b) {
            return;
        }
        List<RiskRatioInfo> timeList = queryMapper.getRiskRatio();
//        System.out.println(timeList.size());
        Map<String, Object> collect = timeList.stream().collect(Collectors.toMap(RiskRatioInfo::getCustId, RiskRatioInfo::getRiskRatio));
//        String jsonString = JSON.toJSONString(collect);
//        redisUtils.set(RISKRATIO_CACHE_KEY_PREFIX,jsonString,24*60*60);
        redisUtils.hmset(RISKRATIO_CACHE_KEY_PREFIX, collect, 24 * 60 * 60);


        System.out.println("OK");

    }

    public Map<Object, Object> getRiskRatio() {
        this.setRiskRatio();
//        String o = (String) redisUtils.get(RISKRATIO_CACHE_KEY_PREFIX);
//        Map<String, Double> stringObjectMap = JSON.parseObject(o, new TypeReference<Map<String, Double>>() {
//        });
        Map<Object, Object> stringObjectMap = redisUtils.hmget(RISKRATIO_CACHE_KEY_PREFIX);

        return stringObjectMap;
    }

    public Object get1RiskRatio(String item) {
        this.setRiskRatio();
//        String o = (String) redisUtils.get(RISKRATIO_CACHE_KEY_PREFIX);
//        Map<String, Double> stringObjectMap = JSON.parseObject(o, new TypeReference<Map<String, Double>>() {
//        });
//        Map<Object, Object> stringObjectMap = redisUtils.hmget(RISKRATIO_CACHE_KEY_PREFIX);
        Object hget = redisUtils.hget(RISKRATIO_CACHE_KEY_PREFIX, item);

        return hget;
    }

    public void setAllProvince() {
        for (Map.Entry<String, String> entry : provinceMap.entrySet()) {
            setProvince(entry.getKey());
        }
    }

    public void setProvince(String key) {

        boolean b = redisUtils.hasKey(PROVINCE_CACHE_KEY_PREFIX + provinceMap.get(key));
        if (b) {
            return;
        }
        List<String> idList = queryMapper.getProvince(key);
//            String jsonString = JSON.toJSONString(idList);
//            redisUtils.set(PROVINCE_CACHE_KEY_PREFIX + provinceMap.get(key),jsonString,24*60*60);

        redisUtils.lSet(PROVINCE_CACHE_KEY_PREFIX + provinceMap.get(key), idList, 24 * 60 * 60);

        System.out.println("OK");


    }

    public List<Object> getProvince(String key) {
        this.setProvince(key);
        List<Object> objects = redisUtils.lGet(PROVINCE_CACHE_KEY_PREFIX + provinceMap.get(key), 0, -1);
//        String o = (String) redisUtils.get(PROVINCE_CACHE_KEY_PREFIX + provinceMap.get(key));
//        List<String> strings = JSON.parseObject(o, new TypeReference<List<String>>() {
//        });
        return objects;
    }
}

