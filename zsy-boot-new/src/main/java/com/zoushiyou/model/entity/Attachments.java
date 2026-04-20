package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件信息实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_attachments")
public class Attachments extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件类型
     */
    private String fileType;
}
