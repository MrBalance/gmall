package com.balance.gmall;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 启动dubbo注解
@EnableDubboConfiguration
@SpringBootApplication
public class ManageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageConsumerApplication.class, args);
    }

}
