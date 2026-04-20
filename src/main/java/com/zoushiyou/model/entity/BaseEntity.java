package com.zoushiyou.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键标识
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父级主键标识
     */
    private Long parentId;

    /**
     * 版本号（乐观锁）
     */
    @Version
    private Integer version;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 排序数字
     */
    private Integer sortNum;

    /**
     * 是否可用 1:可用，0:禁用
     */
    private Integer isEnable;

    /**
     * 是否删除 1：删除，0：正常
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 拥有人Id
     */
    private Long ownerId;

    /**
     * 创建者Id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 修改者Id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
