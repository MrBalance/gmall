package com.balance.gmall.po.spu;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * pms_product_sale_attr
 *
 * @author
 */
@Data
public class PmsProductSaleAttr implements Serializable {
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
     * 销售属性名称(冗余)
     */
    private String saleAttrName;

    /**
     * 商品属性值列表
     */
    @TableField(exist = false)
    private List<PmsProductSaleAttrValue> spuSaleAttrValueList;

}
