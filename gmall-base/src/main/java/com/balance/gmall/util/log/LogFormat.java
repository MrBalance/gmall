package com.balance.gmall.util.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yunzhang.du
 * @description: 格式化输出
 * @version v1.0
 * @date: 2019年06月26日
 * @since JDK 1.8
 */
@Slf4j
public class LogFormat {

    private static String getMethodName(){
        StackTraceElement[] temp = Thread.currentThread().getStackTrace();
        if(temp.length > 3){
            StackTraceElement a= temp[3];
            return a.getClassName()+"."+a.getMethodName();
        }else{
            return null;
        }
    }

    public static void info(String logInfo) {
        log.info("|执行日志|-|" + logInfo + "|");
    }
}
