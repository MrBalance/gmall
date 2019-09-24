package com.balance.gmall.po.spu;

import lombok.Data;

import java.io.Serializable;

/**
 * pms_product_sale_attr_value
 * @author
 */
@Data
public class PmsProductSaleAttrValue implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 销售属性id
     */
    private Long saleAttrId;

    /**
     * 销售属性值名称
     */
    private String saleAttrValueName;

}
