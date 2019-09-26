package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.attr.PmsBaseAttrInfoDao;
import com.balance.gmall.dao.attr.PmsBaseAttrValueDao;
import com.balance.gmall.dao.attr.PmsBaseSaleAttrDao;
import com.balance.gmall.dictionary.PmsBaseAttrInfoField;
import com.balance.gmall.dictionary.PmsBaseAttrValueField;
import com.balance.gmall.dictionary.PmsBaseSaleAttrField;
import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.po.attr.PmsBaseSaleAttr;
import com.balance.gmall.service.BaseAttrService;
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
@Service(interfaceClass = BaseAttrService.class)
public class BaseAttrServiceImpl implements BaseAttrService {
    @Resource
    private PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Resource
    private PmsBaseAttrValueDao pmsBaseAttrValueDao;
    @Resource
    private PmsBaseSaleAttrDao pmsBaseSaleAttrDao;

    @Override
    public List<PmsBaseAttrValue> selectBaseAttrValueListByAttrId(Long attrId) {
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrValueField.ATTR_ID,attrId);
        return pmsBaseAttrValueDao.selectList(wrapper);
    }

    @Override
    public List<PmsBaseAttrInfo> selectBaseAttrInfoListByCatalog3IdId(Long catalog3Id) {
        QueryWrapper<PmsBaseAttrInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrInfoField.CATALOG3_ID, catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoDao.selectList(wrapper);
        pmsBaseAttrInfos.forEach(pmsBaseAttrInfo -> {
            Long id = pmsBaseAttrInfo.getId();
            List<PmsBaseAttrValue> pmsBaseAttrValues = selectBaseAttrValueListByAttrId(id);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        });
        return pmsBaseAttrInfos;
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

    @Override
    public List<PmsBaseSaleAttr> selectBaseSaleAttrListByCatalog3Id(Long catalog3Id) {
        QueryWrapper<PmsBaseSaleAttr> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseSaleAttrField.CATALOG3_ID,catalog3Id);
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = pmsBaseSaleAttrDao.selectList(wrapper);
        return pmsBaseSaleAttrs;
    }

    /**
     * 保存平台属性值
     * @param: attrValue 平台属性值
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:36
     * @author: yunzhang.du
     */
    private Integer saveAttrValue(PmsBaseAttrValue attrValue) {
        return  pmsBaseAttrValueDao.insert(attrValue);
    }

    /**
     * 通过平台属性id删除平台属性值
     * @param: attrId 平台属性id
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:36
     * @author: yunzhang.du
     */
    private Integer deleteAttrValueByAtrrInfoId(Long attrId){
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrValueField.ATTR_ID,attrId);
        return pmsBaseAttrValueDao.delete(wrapper);
    }
}
