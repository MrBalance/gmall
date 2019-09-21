package com.balance.gmall.service.impl.attr;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.attr.PmsBaseAttrValueDao;
import com.balance.gmall.dictionary.PmsBaseAttrValueField;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.service.attr.PmsBaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

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
@Service(interfaceClass = PmsBaseAttrValueService.class)
public class PmsBaseAttrValueServiceImpl implements PmsBaseAttrValueService {

    @Resource
    PmsBaseAttrValueDao pmsBaseAttrValueDao;

    @Override
    public List<PmsBaseAttrValue> selectListByAttrId(Long attrId) {
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrValueField.ATTR_ID,attrId);
        return pmsBaseAttrValueDao.selectList(wrapper);
    }

    @Override
    public Integer saveAttrValue(PmsBaseAttrValue attrValue) {
        return  pmsBaseAttrValueDao.insert(attrValue);
    }

    @Override
    public Integer deleteAttrValueByAtrrInfoId(Long attrId){
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsBaseAttrValueField.ATTR_ID,attrId);
        return pmsBaseAttrValueDao.delete(wrapper);
    }
}
