package com.wyl.cosystem.service;

import com.wyl.cosystem.entity.Message;
import com.wyl.cosystem.mapper.QueryMapper;
import com.wyl.cosystem.redis.RedisMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DatabaseService {
    @Autowired
    RedisMQ redisMQ;

    @Autowired
    private QueryMapper queryMapper;

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

//    @PostConstruct
    void init(){
        EXECUTOR.submit(new Handler());
    }

    private class Handler implements Runnable{
        @Override
        public void run(){
            while(true){
                try{
                    Long count = redisMQ.getSize();
                    if(count == null || count == 0) continue;
                    count = Math.min(100, redisMQ.getSize());
                    List<Message> tasks = redisMQ.pollBatch(count);
                    queryMapper.insertBatch(tasks);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

}
