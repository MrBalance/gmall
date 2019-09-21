package com.balance.gmall.service.attr;

import com.balance.gmall.exception.DescribeException;
import com.balance.gmall.po.attr.PmsBaseAttrInfo;

import java.util.List;

/**
 * @description: 平台属性service层
 * @author: yunzhang.du
 * @date: 2019年09月17日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface PmsBaseAttrInfoService {

    List<PmsBaseAttrInfo> selectListByCatalog3IdId(Long catalog3Id);

    Integer saveAttrInfo(PmsBaseAttrInfo attrInfo) throws DescribeException;
}
