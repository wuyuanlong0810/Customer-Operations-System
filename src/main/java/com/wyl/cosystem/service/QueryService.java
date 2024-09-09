package com.wyl.cosystem.service;

import com.wyl.cosystem.entity.BatchQueryInfo;
import com.wyl.cosystem.entity.Message;
import com.wyl.cosystem.entity.QueryInfo;
import com.wyl.cosystem.entity.User;
import com.wyl.cosystem.mapper.QueryMapper;
import com.wyl.cosystem.redis.RedisMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class QueryService {
    private static final String USER_CACHE_KEY_PREFIX = "user:";
    private static final String MOBILE_CACHE_KEY_PREFIX = "mobile:";

    @Autowired
    private QueryMapper queryMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ThreeCustService threeCustService;

    @Autowired
    private RedisMQ redisMQ;


    public User getUser(QueryInfo queryInfo) {
        String cache_key = "";
        if (queryInfo.getCustId() != null) cache_key = USER_CACHE_KEY_PREFIX + queryInfo.getCustId();
        else if (queryInfo.getMobile() != null) cache_key = MOBILE_CACHE_KEY_PREFIX + queryInfo.getMobile();
        else {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get(cache_key);
        try {
            if (user != null) {
                System.out.println("cache hit");
            } else {
                System.out.println("cache miss");
                user = queryMapper.query(queryInfo);
//                Map<Object, Object> custTime = threeCustService.getCustTime();
//                Map<Object, Object> pushRatio = threeCustService.getPushRatio();
//                Map<Object, Object> riskRatio = threeCustService.getRiskRatio();
//                user.setSendNum((Integer) custTime.get(queryInfo.getCustId()));
//                user.setPushRatio((Double) pushRatio.get(queryInfo.getCustId()));
//                user.setRiskRatio((Double) riskRatio.get(queryInfo.getCustId()));

                user.setSendNum((Integer) threeCustService.get1CustTime(queryInfo.getCustId()));
                user.setPushRatio((Double) threeCustService.get1PushRatio(queryInfo.getCustId()));
                user.setRiskRatio((Double) threeCustService.get1RiskRatio(queryInfo.getCustId()));
                if (queryInfo.getSendLimit() != null && user.getSendNum() >= queryInfo.getSendLimit()) {
                    return null;
                }
                if (user.getPushRatio() != null) {
                    if (queryInfo.getPushRatio() != null && user.getPushRatio() < queryInfo.getPushRatio()) {
                        return null;
                    }
                }
                if (user.getRiskRatio() != null) {
                    if (queryInfo.getRiskRatio() != null && user.getRiskRatio() > queryInfo.getRiskRatio()) {
                        return null;
                    }
                }

            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (user != null) {
                Message message = new Message(new Date(), user.getCustId());

//                redisMQ.add(message);

                user.setSendNum(user.getSendNum() + 1); //自增
                String cache_key1 = USER_CACHE_KEY_PREFIX + user.getCustId();
                String cache_key2 = MOBILE_CACHE_KEY_PREFIX + user.getMobile();
                redisTemplate.opsForValue().set(cache_key1, user, 10, TimeUnit.MINUTES);
                redisTemplate.opsForValue().set(cache_key2, user, 10, TimeUnit.MINUTES);

            }
        }

    }

    //用多个线程 测试过后发现慢多了
//    public List<String> getCustIdBatch(BatchQueryInfo batchQueryInfo) {
////        List<String> result = queryMapper.batch_query(batchQueryInfo);
//        long l = System.currentTimeMillis();
//        String[] provinces = batchQueryInfo.getProvinces();
//        List<String> result = new ArrayList<>();
//        for (String province : provinces) {
//            result.addAll(threeCustService.getProvince(province));
//        }
//        int num = 0;
//        if (batchQueryInfo.getSendLimit() != null) num++;
//        if (batchQueryInfo.getRiskRatio() != null) num++;
//        if (batchQueryInfo.getPushRatio() != null) num++;
//
//        AtomicReference<List<String>> result1 = new AtomicReference<>(new ArrayList<>());
//        AtomicReference<List<String>> result2 = new AtomicReference<>(new ArrayList<>());
//        AtomicReference<List<String>> result3 = new AtomicReference<>(new ArrayList<>());
//        try {
//            ExecutorService executor = Executors.newFixedThreadPool(num);
//            final CountDownLatch countDownLatch = new CountDownLatch(num);
//            if (batchQueryInfo.getSendLimit() != null) {
//                executor.execute(() -> {
//                    Map<String, Integer> custTime = threeCustService.getCustTime();
//                    result1.set(result.stream().filter(a ->
//                            custTime.get(a) == null || custTime.get(a) < batchQueryInfo.getSendLimit()
//                    ).collect(Collectors.toList()));
//                    countDownLatch.countDown();
//                });
//            }
//            if (batchQueryInfo.getPushRatio() != null) {
//                executor.execute(() -> {
//                    Map<String, Double> pushRatio = threeCustService.getPushRatio();
//                    result2.set(result.stream().filter(a ->
//                            pushRatio.get(a) == null || pushRatio.get(a) * 100 >= batchQueryInfo.getPushRatio()
//                    ).collect(Collectors.toList()));
//                    countDownLatch.countDown();
//                });
//            }
//
//
//            if (batchQueryInfo.getRiskRatio() != null) {
//                executor.execute(() -> {
//                    Map<String, Double> riskRatio = threeCustService.getRiskRatio();
//                    result3.set(result.stream().filter(a ->
//                            riskRatio.get(a) == null || riskRatio.get(a) * 100 <= batchQueryInfo.getRiskRatio()
//                    ).collect(Collectors.toList()));
//                    countDownLatch.countDown();
//                });
//            }
//
//
//            countDownLatch.await();
//            executor.shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<String> list= (List<String>) CollectionUtils.intersection(result1.get(), result2.get()).stream().collect(Collectors.toList());
//        List<String> list2= (List<String>) CollectionUtils.intersection(list, result3.get()).stream().collect(Collectors.toList());
//
//        System.out.println("result:" + list2.size());
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1 - l);
//
////        Collection disjunction = CollectionUtils.disjunction(result, xxx);
////        Collection subtract = CollectionUtils.subtract(xxx, result);
//
//
//        return list2;
//    }


    //按顺序过滤
//    public List<String> getCustIdBatch(BatchQueryInfo batchQueryInfo) {
////        List<String> result = queryMapper.batch_query(batchQueryInfo);
//        long l = System.currentTimeMillis();
//        String[] provinces = batchQueryInfo.getProvinces();
//        List<String> result = new ArrayList<>();
//        for (String province : provinces) {
//            result.addAll(threeCustService.getProvince(province));
//        }
////        List<String> xxx = queryMapper.xxx();
////        System.out.println("xxx:"+xxx.size());
//        Map<String, Integer> custTime = threeCustService.getCustTime();
//        Map<String, Double> pushRatio = threeCustService.getPushRatio();
//        Map<String, Double> riskRatio = threeCustService.getRiskRatio();
////        System.out.println(batchQueryInfo.getSendLimit());
//
//        if (batchQueryInfo.getSendLimit() != null) {
//            result = result.stream().filter(a ->
//                    custTime.get(a) == null || custTime.get(a) < batchQueryInfo.getSendLimit()
//            ).collect(Collectors.toList());
//        }
//        if (batchQueryInfo.getPushRatio() != null) {
//            result = result.stream().filter(a ->
//                    pushRatio.get(a) == null || pushRatio.get(a) * 100 >= batchQueryInfo.getPushRatio()
//            ).collect(Collectors.toList());
//        }
//        if (batchQueryInfo.getRiskRatio() != null) {
//            result = result.stream().filter(a ->
//                    riskRatio.get(a) == null || riskRatio.get(a) * 100 <= batchQueryInfo.getRiskRatio()
//            ).collect(Collectors.toList());
//        }
//        System.out.println("result:" + result.size());
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1-l);
//
////        Collection disjunction = CollectionUtils.disjunction(result, xxx);
////        Collection subtract = CollectionUtils.subtract(xxx, result);
//
//
//        return result;
//    }

    //一起过滤
    public List<Object> getCustIdBatch(BatchQueryInfo batchQueryInfo) {
//        List<String> result = queryMapper.batch_query(batchQueryInfo);
        String[] provinces = batchQueryInfo.getProvinces();
        List<Object> result = new ArrayList<>();
        for (String province : provinces) {
            result.addAll(threeCustService.getProvince(province));
        }
        Map<Object, Object> custTime;
        if (batchQueryInfo.getSendLimit() != null)
            custTime = threeCustService.getCustTime();
        else {
            custTime = new HashMap<>();
        }
        Map<Object, Object> pushRatio;
        if (batchQueryInfo.getPushRatio() != null)
            pushRatio = threeCustService.getPushRatio();
        else {
            pushRatio = new HashMap<>();
        }
        Map<Object, Object> riskRatio;
        if (batchQueryInfo.getRiskRatio() != null)
            riskRatio = threeCustService.getRiskRatio();
        else {
            riskRatio = new HashMap<>();
        }
        result = result.stream().filter(a -> {
                    boolean b1, b2, b3;
                    b1 = custTime.get(a) == null || (Integer) custTime.get(a) < batchQueryInfo.getSendLimit();
                    b2 = pushRatio.get(a) == null || (Double) pushRatio.get(a) * 100 >= batchQueryInfo.getPushRatio();
                    b3 = riskRatio.get(a) == null || (Double) riskRatio.get(a) * 100 <= batchQueryInfo.getRiskRatio();
                    return b1 && b2 && b3;
                }

        ).collect(Collectors.toList());

        System.out.println("result:" + result.size());

//        Collection disjunction = CollectionUtils.disjunction(result, xxx);
//        Collection subtract = CollectionUtils.subtract(xxx, result);


        return result;
    }
}
