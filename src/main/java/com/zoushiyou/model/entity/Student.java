package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;
}
