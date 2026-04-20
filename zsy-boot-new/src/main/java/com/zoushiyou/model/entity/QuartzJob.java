package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 定时任务实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_quartzjob")
public class QuartzJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务状态
     */
    private Integer jobStatus;

    /**
     * 任务表达式
     */
    private String jobCron;
}
