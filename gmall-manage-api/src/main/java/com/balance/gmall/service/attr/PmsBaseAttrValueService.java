package com.balance.gmall.service.attr;

import com.balance.gmall.po.attr.PmsBaseAttrValue;

import java.util.List;

/**
 * @description: 平台属性值service层
 * @author: yunzhang.du
 * @date: 2019年09月17日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface PmsBaseAttrValueService {

    List<PmsBaseAttrValue> selectListByAttrId(Long attrId);

    public Integer saveAttrValue(PmsBaseAttrValue attrValue);

    public Integer deleteAttrValueByAtrrInfoId(Long attrId);
}
