package com.zoushiyou.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuartzJob extends BaseEntity {
    private String jobGroup;
    private Integer jobStatus;
    private String jobCron;
}
