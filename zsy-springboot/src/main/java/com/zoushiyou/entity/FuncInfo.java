package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_funcinfo")
public class FuncInfo extends BaseEntity {
    private String routePath;
    private String styleName;
    private Integer levelVal;
}
