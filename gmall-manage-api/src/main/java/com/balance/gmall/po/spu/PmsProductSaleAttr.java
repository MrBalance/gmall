package com.balance.gmall.po.spu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 商品id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;

    /**
     * 销售属性id
     */
    @JsonSerialize(using= ToStringSerializer.class)
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
