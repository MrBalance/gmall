package com.balance.gmall.service.impl.attr;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.attr.PmsBaseAttrInfoDao;
import com.balance.gmall.dictionary.PmsBaseAttrInfoField;
import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.service.attr.PmsBaseAttrInfoService;
import com.balance.gmall.service.attr.PmsBaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 平台属性service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = PmsBaseAttrInfoService.class)
public class PmsBaseAttrInfoServiceImpl implements PmsBaseAttrInfoService {

    @Resource
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Resource
    PmsBaseAttrValueService pmsBaseAttrValueService;

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
                pmsBaseAttrValueService.saveAttrValue(attrValue);
            });
        } else {
            attrInfoResult = pmsBaseAttrInfoDao.updateById(attrInfo);
            List<PmsBaseAttrValue> pmsBaseAttrValues = attrInfo.getAttrValueList();
            pmsBaseAttrValueService.deleteAttrValueByAtrrInfoId(id);
            pmsBaseAttrValues.forEach(attrValue -> {
                attrValue.setAttrId(id);
                pmsBaseAttrValueService.saveAttrValue(attrValue);
            });
        }
        return attrInfoResult;
    }
}
