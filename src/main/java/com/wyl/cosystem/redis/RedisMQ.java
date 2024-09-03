package com.wyl.cosystem.redis;

import com.wyl.cosystem.entity.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class RedisMQ {
    public static final String MQ_KEY = "my_queue";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisMQ(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    public void add(Message message){
        System.out.println(MQ_KEY);
        redisTemplate.opsForList().leftPush(MQ_KEY, message);
    }

    public Message poll(){
        Message message = null;
        if(redisTemplate.opsForList().size(MQ_KEY) != null){
            message = (Message) redisTemplate.opsForList().rightPop(MQ_KEY);
        }
        return message;
    }

    public List<Message> pollBatch(long num){
        System.out.println("number of tasks: " + num);
        List<Message> res = new ArrayList<>();
        while(num > 0){
            res.add((Message) redisTemplate.opsForList().rightPop(MQ_KEY));
            num--;
        }
        return res;
    }

    public Long getSize(){
        return redisTemplate.opsForList().size(MQ_KEY);
    }
}
