package com.balance.gmall.po.catalog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 三级分类
 * pms_base_catalog3
 *
 */
@Data
public class PmsBaseCatalog3 implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 三级分类名称
     */
    private String name;

    /**
     * 二级分类编号
     */
    private Long catalog2Id;

}
