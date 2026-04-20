package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_attachments")
public class Attachments extends BaseEntity {
    private Integer fkType;
    private Long fkPkId;
    private String suffix;
    private byte[] streamData;
}
