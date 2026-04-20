package com.zoushiyou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.dto.CommonDto;
import com.zoushiyou.dto.FuncInfoDto;
import com.zoushiyou.dto.UserLoginInfoDto;
import com.zoushiyou.entity.DeptInfo;
import com.zoushiyou.entity.FuncInfo;
import com.zoushiyou.entity.RoleInfo;
import com.zoushiyou.entity.UserInfo;
import com.zoushiyou.mapper.UserInfoMapper;
import com.zoushiyou.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    
    @Autowired
    private RoleInfoService roleInfoService;
    
    @Autowired
    private DeptInfoService deptInfoService;
    
    @Autowired
    private FuncInfoService funcInfoService;
    
    @Override
    public UserInfo findByUserName(String username) {
        return baseMapper.findByUserName(username.trim());
    }
    
    @Override
    public UserLoginInfoDto buildLoginData(UserInfo userInfo, String token) {
        UserLoginInfoDto userLoginInfoDto = new UserLoginInfoDto();
        userLoginInfoDto.setUserInfoDto(new com.zoushiyou.dto.UserInfoDto(userInfo));
        userLoginInfoDto.getUserInfoDto().setToken(token);
        
        RoleInfo roleInfo = roleInfoService.getById(userInfo.getRoleId());
        if (roleInfo != null) {
            userLoginInfoDto.setRoleInfoDto(new CommonDto(roleInfo));
        }
        
        DeptInfo deptInfo = deptInfoService.getById(userInfo.getDeptId());
        if (deptInfo != null) {
            userLoginInfoDto.setDeptInfoDto(new CommonDto(deptInfo));
        }
        
        List<FuncInfo> listFuncInfo = funcInfoService.findByRoleId(userInfo.getRoleId());
        List<FuncInfoDto> lstFunc = new ArrayList<>();
        if (listFuncInfo != null) {
            for (FuncInfo funcInfo : listFuncInfo) {
                if (funcInfo.getParentId() == 0L && funcInfo.getLevelVal() == 1) {
                    FuncInfoDto itemDto = new FuncInfoDto(funcInfo);
                    lstFunc.add(itemDto);
                }
            }
            for (FuncInfoDto itemDto : lstFunc) {
                for (FuncInfo funcInfo : listFuncInfo) {
                    if (itemDto.getId().equals(funcInfo.getParentId()) && funcInfo.getLevelVal() == 2) {
                        if (itemDto.getSubItem() == null) {
                            List<FuncInfoDto> sublstFunc = new ArrayList<>();
                            sublstFunc.add(new FuncInfoDto(funcInfo));
                            itemDto.setSubItem(sublstFunc);
                        } else {
                            itemDto.getSubItem().add(new FuncInfoDto(funcInfo));
                        }
                    }
                }
            }
        }
        userLoginInfoDto.setListfuncInfoDto(lstFunc);
        return userLoginInfoDto;
    }
}
