package com.zoushiyou.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_userinfo")
public class UserInfo extends BaseEntity {
    
    @JsonIgnore
    private String passWord;
    
    @JsonIgnore
    private String salt;
    
    @TableField("is_Male")
    private Integer isMale;
    
    private String phoneNum;
    
    private Long roleId;
    
    private Long deptId;
}
