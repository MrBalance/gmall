package com.balance.gmall.service.impl.catalog;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.catalog.PmsBaseCatalog2Dao;
import com.balance.gmall.po.catalog.PmsBaseCatalog2;
import com.balance.gmall.service.catalog.PmsBaseCatalog2Service;
import com.balance.gmall.dictionary.PmsBaseCatalog2Field;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 二级分类service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = PmsBaseCatalog2Service.class)
public class PmsBaseCatalog2ServiceImpl implements PmsBaseCatalog2Service {

    @Resource
    PmsBaseCatalog2Dao pmsBaseCatalog2Dao;

    @Override
    public List<PmsBaseCatalog2> selectListByCatalog1IdId(Long catalog1Id) {
        QueryWrapper<PmsBaseCatalog2> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseCatalog2Field.CATALOG1_ID,catalog1Id);
        return pmsBaseCatalog2Dao.selectList(wrapper);
    }
}
