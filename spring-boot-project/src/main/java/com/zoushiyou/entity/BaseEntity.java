package com.zoushiyou.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long parentId;
    private Integer version;
    private String code;
    private String name;
    private String remarks;
    private Integer sortNum;
    private Integer isEnable;
    private Integer isDelete;
    private Long ownerId;
    private Long createId;
    private Long updateId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
