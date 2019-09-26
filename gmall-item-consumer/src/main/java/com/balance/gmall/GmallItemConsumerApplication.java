package com.balance.gmall;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: yunzhang.du
 * @date: 2019年09月26日
 * @version: v1.0
 * @since: JDK 1.8
 */
// 启动dubbo注解
@EnableDubboConfiguration
//跳过dataSource检查
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GmallItemConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallItemConsumerApplication.class, args);
    }

}
