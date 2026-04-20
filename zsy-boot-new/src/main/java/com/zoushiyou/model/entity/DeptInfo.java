package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_deptinfo")
public class DeptInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
}
