package com.balance.gmall.service.impl;

import com.balance.gmall.dao.catalog.PmsBaseCatalog1Dao;
import com.balance.gmall.dao.catalog.PmsBaseCatalog2Dao;
import com.balance.gmall.dao.catalog.PmsBaseCatalog3Dao;
import com.balance.gmall.dictionary.PmsBaseCatalog2Field;
import com.balance.gmall.dictionary.PmsBaseCatalog3Field;
import com.balance.gmall.po.catalog.PmsBaseCatalog1;
import com.balance.gmall.po.catalog.PmsBaseCatalog2;
import com.balance.gmall.po.catalog.PmsBaseCatalog3;
import com.balance.gmall.service.PmsBaseCatalogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2019年09月21日
 * @version: v1.0
 * @since: JDK 1.8
 */
public class PmsBaseCatalogServiceImpl implements PmsBaseCatalogService {
    @Resource
    PmsBaseCatalog1Dao pmsBaseCatalog1Dao;
    @Resource
    PmsBaseCatalog2Dao pmsBaseCatalog2Dao;
    @Resource
    PmsBaseCatalog3Dao pmsBaseCatalog3Dao;

    @Override
    public List<PmsBaseCatalog1> selectAllList() {
        return pmsBaseCatalog1Dao.selectList(null);
    }

    @Override
    public List<PmsBaseCatalog2> selectListByCatalog1IdId(Long catalog1Id) {
        QueryWrapper<PmsBaseCatalog2> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseCatalog2Field.CATALOG1_ID,catalog1Id);
        return pmsBaseCatalog2Dao.selectList(wrapper);
    }

    @Override
    public List<PmsBaseCatalog3> selectListByCatalog2IdId(Long catalog2Id) {
        QueryWrapper<PmsBaseCatalog3> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseCatalog3Field.CATALOG2_ID,catalog2Id);
        return pmsBaseCatalog3Dao.selectList(wrapper);
    }
}
