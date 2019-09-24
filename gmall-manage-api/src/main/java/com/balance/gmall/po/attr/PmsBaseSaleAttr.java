package com.balance.gmall.po.attr;

import lombok.Data;

import java.io.Serializable;

/**
 * pms_base_sale_attr
 *
 * @author
 */
@Data
public class PmsBaseSaleAttr implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 销售属性名称
     */
    private String name;

    /**
     * 三级分类id
     */
    private Long catalog3Id;
}
