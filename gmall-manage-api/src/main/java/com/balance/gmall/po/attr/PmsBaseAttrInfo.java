package com.balance.gmall.po.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 平台属性
 * pms_base_attr_info
 */
@Data
public class PmsBaseAttrInfo implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 属性名称
     */
    private String attrName;

    private Long catalog3Id;

    /**
     * 启用：1 停用：0
     */
    private String isEnabled;

    /**
     * 商品属性值
     */
    @TableField(exist = false)
    private List<PmsBaseAttrValue> attrValueList;

}
