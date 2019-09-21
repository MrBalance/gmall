package com.balance.gmall.util.id;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 全局id（20位） = 当前时间精确到秒（14位）+ 秒内自增（6位）
 * @author: yunzhang.du
 * @date: 2019年09月18日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Slf4j
public class IdGenerator implements IKeyGenerator {



    @Override
    public String executeSql(String incrementerName) {
        GenerateIdWorker generateIdWorker = new GenerateIdWorker();
        long id = generateIdWorker.idGenerate();
        return "select " + id + " from dual";
    }
}
