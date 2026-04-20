package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能菜单信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_funcinfo")
public class FuncInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 路由地址
     */
    private String routePath;

    /**
     * 样式名称
     */
    private String styleName;

    /**
     * 级别（层次）
     */
    private Integer levelVal;
}
