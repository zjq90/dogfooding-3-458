package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    @TableField(fill = FieldFill.INSERT)
    private Long parentId;
    
    @Version
    private Integer version;
    
    private String code;
    
    private String name;
    
    private String remarks;
    
    private Integer sortNum;
    
    @TableField(fill = FieldFill.INSERT)
    private Integer isEnable;
    
    @TableLogic
    private Integer isDelete;
    
    @TableField(fill = FieldFill.INSERT)
    private Long ownerId;
    
    @TableField(fill = FieldFill.INSERT)
    private Long createId;
    
    @TableField(fill = FieldFill.UPDATE)
    private Long updateId;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
