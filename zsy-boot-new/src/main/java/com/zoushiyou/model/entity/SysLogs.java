package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统日志实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_syslogs")
public class SysLogs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private Integer opererType;

    /**
     * 操作内容
     */
    private String opererContent;
}
