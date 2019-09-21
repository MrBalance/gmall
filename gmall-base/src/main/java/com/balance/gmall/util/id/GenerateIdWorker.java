package com.balance.gmall.util.id;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月19日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Slf4j
public class GenerateIdWorker {
    /**
     * 获取上一次的时间
     */
    private static long lastTime = -1L;

    /**
     * 秒内自增值
     */
    private static long sequence = 0L;

    /**
     * 自增最大值
     */
    private final static long SEQUENCE_MAX_DIGIT = (long) Math.pow(10, 5);



    public static long nextId(){
        GenerateIdWorker generateIdWorker = new GenerateIdWorker();
        return generateIdWorker.idGenerate();
    }

    public synchronized long idGenerate() {

        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // new Date()为获取当前系统时间
        long time = Long.valueOf(df.format(new Date())+"00000");
        // 如果两次获取id在同一秒内 自增
        if (lastTime == time) {
            // 如果超过最大子增值 秒数加1
            if (sequence < SEQUENCE_MAX_DIGIT) {
                sequence = sequence + 1;
            } else {
                time += 1;
                sequence = 0;
            }
        } else {
            sequence = 0;
        }
        lastTime = time;
        return time + sequence;
    }
}
