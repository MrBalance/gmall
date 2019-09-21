package com.balance.gmall.po.attr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 平台属性值
 * pms_base_attr_value
 *
 */
@Data
public class PmsBaseAttrValue implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 属性值名称
     */
    private String valueName;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 启用：1 停用：0 1
     */
    private String isEnabled;

}
