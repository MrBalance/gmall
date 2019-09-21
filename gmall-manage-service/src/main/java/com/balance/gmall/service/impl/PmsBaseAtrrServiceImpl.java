package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.attr.PmsBaseAttrInfoDao;
import com.balance.gmall.dao.attr.PmsBaseAttrValueDao;
import com.balance.gmall.dictionary.PmsBaseAttrInfoField;
import com.balance.gmall.dictionary.PmsBaseAttrValueField;
import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.service.PmsBaseAtrrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 平台属性实现类
 * @author: yunzhang.du
 * @date: 2019年09月21日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = PmsBaseAtrrService.class)
public class PmsBaseAtrrServiceImpl implements PmsBaseAtrrService {
    @Resource
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Resource
    PmsBaseAttrValueDao pmsBaseAttrValueDao;

    @Override
    public List<PmsBaseAttrValue> selectListByAttrId(Long attrId) {
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrValueField.ATTR_ID,attrId);
        return pmsBaseAttrValueDao.selectList(wrapper);
    }

    @Override
    public List<PmsBaseAttrInfo> selectListByCatalog3IdId(Long catalog3Id) {
        QueryWrapper<PmsBaseAttrInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrInfoField.CATALOG3_ID, catalog3Id);
        return pmsBaseAttrInfoDao.selectList(wrapper);
    }

    @Override
    @Transactional
    public Integer saveAttrInfo(PmsBaseAttrInfo attrInfo) {
        int attrInfoResult = 0;
        Long id = attrInfo.getId();
        // 根据id是否位空判断 新增 还是 更新 操作
        if(null == id){
            attrInfoResult = pmsBaseAttrInfoDao.insert(attrInfo);
            Long insertId = attrInfo.getId();
            List<PmsBaseAttrValue> pmsBaseAttrValues = attrInfo.getAttrValueList();
            pmsBaseAttrValues.forEach(attrValue -> {
                attrValue.setAttrId(insertId);
                saveAttrValue(attrValue);
            });
        } else {
            attrInfoResult = pmsBaseAttrInfoDao.updateById(attrInfo);
            List<PmsBaseAttrValue> pmsBaseAttrValues = attrInfo.getAttrValueList();
            deleteAttrValueByAtrrInfoId(id);
            pmsBaseAttrValues.forEach(attrValue -> {
                attrValue.setAttrId(id);
                saveAttrValue(attrValue);
            });
        }
        return attrInfoResult;
    }

    private Integer saveAttrValue(PmsBaseAttrValue attrValue) {
        return  pmsBaseAttrValueDao.insert(attrValue);
    }

    private Integer deleteAttrValueByAtrrInfoId(Long attrId){
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrValueField.ATTR_ID,attrId);
        return pmsBaseAttrValueDao.delete(wrapper);
    }
}
