package com.balance.gmall.util.id;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月19日
 * @version: v1.0
 * @since: JDK 1.8
 */
// @Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // this.setFieldValByName("operator", "Jerry", metaObject);//版本号3.0.6以及之前的版本
        this.setInsertFieldValByName("operator", "Jerry", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // this.setFieldValByName("operator", "Tom", metaObject);
        this.setUpdateFieldValByName("operator", "Tom", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }
}
