package com.balance.gmall;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 启动dubbo注解
@EnableDubboConfiguration
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GmallItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallItemServiceApplication.class, args);
    }

}
