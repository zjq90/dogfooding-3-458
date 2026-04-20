package com.zoushiyou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Attachment extends BaseEntity {
    private Integer fkType;
    private Long fkPkId;
    private String suffix;
    private byte[] streamData;
}
