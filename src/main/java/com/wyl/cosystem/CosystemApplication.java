package com.wyl.cosystem;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.wyl.cosystem.mapper") //扫描的mapper
@SpringBootApplication
@EnableCaching
public class CosystemApplication {
    public static void main(String[] args) {

        SpringApplication.run(CosystemApplication.class, args);

        System.out.print("开始运行");
    }
}
