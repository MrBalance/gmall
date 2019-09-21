package com.balance.gmall.service.impl.catalog;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.catalog.PmsBaseCatalog3Dao;
import com.balance.gmall.po.catalog.PmsBaseCatalog3;
import com.balance.gmall.service.catalog.PmsBaseCatalog3Service;
import com.balance.gmall.dictionary.PmsBaseCatalog3Field;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 三级分类service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = PmsBaseCatalog3Service.class)
public class PmsBaseCatalog3ServiceImpl implements PmsBaseCatalog3Service {

    @Resource
    PmsBaseCatalog3Dao pmsBaseCatalog3Dao;

    @Override
    public List<PmsBaseCatalog3> selectListByCatalog2IdId(Long catalog2Id) {
        QueryWrapper<PmsBaseCatalog3> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseCatalog3Field.CATALOG2_ID,catalog2Id);
        return pmsBaseCatalog3Dao.selectList(wrapper);
    }
}
