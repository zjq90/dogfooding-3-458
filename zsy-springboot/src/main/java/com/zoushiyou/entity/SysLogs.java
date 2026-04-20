package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_syslogs")
public class SysLogs extends BaseEntity {
    private Integer opererType;
    private String opererContent;
}
