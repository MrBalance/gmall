package com.balance.gmall.po.spu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
     * 销售属性值名称
     */
    private String saleAttrValueName;

    /** 是否被选中*/
    @TableField(exist = false)
    private boolean Checked;

}
