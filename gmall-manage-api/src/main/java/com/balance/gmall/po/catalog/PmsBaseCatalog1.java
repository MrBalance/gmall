package com.balance.gmall.po.catalog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 一级分类
 * pms_base_catalog1
 *
 */
@Data
public class PmsBaseCatalog1 implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

}
