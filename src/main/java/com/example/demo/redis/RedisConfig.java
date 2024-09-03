package com.example.demo.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;



@Configuration
public class RedisConfig<V> {//redis配置

    @Value("${spring.redis.host:}") // 提供了默认值 localhost
    private String redisHost;

    @Value("${spring.redis.port:}") // 提供了默认值 6379
    private Integer redisPort;

    @Bean("redisTemplate")
    public RedisTemplate<String,V> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,V> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(RedisSerializer.string());//设置key序列化方式
        template.setValueSerializer(RedisSerializer.json());//设置value序列化方式
        template.setHashKeySerializer(RedisSerializer.string());//设置hashkey序列化方式
        template.setHashValueSerializer(RedisSerializer.json());//设置hashvalue序列化方式
        template.afterPropertiesSet();
        return template;
    }
}
