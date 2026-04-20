package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_quartzjob")
public class QuartzJob extends BaseEntity {
    private String jobGroup;
    private Integer jobStatus;
    private String jobCron;
}
