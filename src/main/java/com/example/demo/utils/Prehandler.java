package com.example.demo.utils;

import com.example.demo.redis.RedisUtils;
import com.example.demo.service.ThreeCustService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 吴远龙
 * @Date: 2024-09-02 23:52
 */
@Component
public class Prehandler implements CommandLineRunner {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ThreeCustService threeCustService;

    //获取当前日期对应的营销次数
    public void getCustTimes(){
        threeCustService.setCustTime();
    }
    //获取回捞系数
    public void getPushRatio(){
        threeCustService.setPushRatio();
    }
    //获取风险
    public void getRiskRatio(){
        threeCustService.setRiskRatio();
    }
    //获取各省的人
    public void getProvince(){
        threeCustService.setAllProvince();
    }

    @Override
    public void run(String... args) throws Exception {
        this.getCustTimes();
        this.getPushRatio();
        this.getRiskRatio();
        this.getProvince();
    }
}
