package com.balance.gmall.service;

import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.po.attr.PmsBaseSaleAttr;

import java.util.List;

/**
 * 平台属性api层
 *
 * @author: yunzhang.du
 * @date: 2019年09月21日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface BaseAttrService {


    /**
     * 根据三级列表id，列出平台属性
     *
     * @param: catalog3Id 根据三级列表id
     * @return: List<PmsBaseAttrInfo>
     * @throw:
     * @Date: 2019/9/21 - 17:21
     * @author: yunzhang.du
     */
    List<PmsBaseAttrInfo> selectListByCatalog3IdId(Long catalog3Id);

    /**
     * 根据平台属性id，列出平台属性值
     *
     * @param: attrId 平台属性id
     * @return: List<PmsBaseAttrValue>
     * @throw:
     * @Date: 2019/9/21 - 17:25
     * @author: yunzhang.du
     */
    List<PmsBaseAttrValue> selectListByAttrId(Long attrId);

    /**
     * 根据传入的平台属性对象，保存平台属性和平台值
     *
     * @param: attrInfo 平台属性对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/21 - 17:24
     * @author: yunzhang.du
     */
    Integer saveAttrInfo(PmsBaseAttrInfo attrInfo);

    /**
     * 根据传入平台三级分类，列表展示平台商品属性
     * @param: catalog3Id 根据传入平台三级分类
     * @return: List<PmsBaseSaleAttr>
     * @throw:
     * @Date: 2019/9/23 - 14:50
     * @author: yunzhang.du
     */
    List<PmsBaseSaleAttr> baseSaleAttrListByCatalog3Id(Long catalog3Id);
}
