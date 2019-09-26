package com.balance.gmall.po.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
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
