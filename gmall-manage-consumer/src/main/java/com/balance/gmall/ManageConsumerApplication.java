package com.balance.gmall;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


// 启动dubbo注解
@EnableDubboConfiguration
//跳过dataSource检查
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ManageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageConsumerApplication.class, args);
    }

}
