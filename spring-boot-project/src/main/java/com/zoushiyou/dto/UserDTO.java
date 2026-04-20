package com.zoushiyou.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String code;
    private String name;
    private String phoneNum;
    private Integer isMale;
    private Long roleId;
    private Long deptId;
    private String remarks;
}
