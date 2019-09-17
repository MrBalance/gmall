package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.PmsBaseCatalog1Dao;
import com.balance.gmall.po.PmsBaseCatalog1;
import com.balance.gmall.service.PmsBaseCatalog1Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 一级分类service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service
public class PmsBaseCatalog1ServiceImpl implements PmsBaseCatalog1Service {

    @Resource
    PmsBaseCatalog1Dao pmsBaseCatalog1Dao;

    @Override
    public List<PmsBaseCatalog1> selectAllList() {
        return pmsBaseCatalog1Dao.selectList(null);
    }
}
