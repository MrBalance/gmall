package com.balance.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

// 启动dubbo注解
@EnableDubboConfiguration
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GamllSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamllSearchServiceApplication.class, args);
    }

}
