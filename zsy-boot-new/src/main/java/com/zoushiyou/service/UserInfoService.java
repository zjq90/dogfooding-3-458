package com.zoushiyou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoushiyou.mapper.FuncInfoMapper;
import com.zoushiyou.mapper.RoleInfoMapper;
import com.zoushiyou.mapper.DeptInfoMapper;
import com.zoushiyou.mapper.UserInfoMapper;
import com.zoushiyou.model.dto.*;
import com.zoushiyou.model.entity.DeptInfo;
import com.zoushiyou.model.entity.FuncInfo;
import com.zoushiyou.model.entity.RoleInfo;
import com.zoushiyou.model.entity.UserInfo;
import com.zoushiyou.util.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息服务类
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Autowired
    private DeptInfoMapper deptInfoMapper;

    @Autowired
    private FuncInfoMapper funcInfoMapper;

    /**
     * 根据用户名查询用户
     */
    public UserInfo findByUserName(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        return baseMapper.findByUserName(username.trim());
    }

    /**
     * 构建登录数据
     */
    public UserLoginDto buildLoginData(UserInfo userInfo, String token) {
        UserLoginDto loginDto = new UserLoginDto();

        // 设置用户信息
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo, userInfoDto);
        userInfoDto.setToken(token);
        loginDto.setUserInfo(userInfoDto);

        // 设置角色信息
        RoleInfo roleInfo = roleInfoMapper.selectById(userInfo.getRoleId());
        if (roleInfo != null) {
            loginDto.setRoleInfo(new CommonDto(roleInfo.getId(), roleInfo.getCode(), roleInfo.getName()));
        }

        // 设置部门信息
        DeptInfo deptInfo = deptInfoMapper.selectById(userInfo.getDeptId());
        if (deptInfo != null) {
            loginDto.setDeptInfo(new CommonDto(deptInfo.getId(), deptInfo.getCode(), deptInfo.getName()));
        }

        // 设置功能菜单
        List<FuncInfo> funcList = funcInfoMapper.findByRoleId(userInfo.getRoleId());
        List<FuncInfoDto> funcDtoList = buildFuncTree(funcList);
        loginDto.setFuncList(funcDtoList);

        return loginDto;
    }

    /**
     * 构建功能菜单树
     */
    private List<FuncInfoDto> buildFuncTree(List<FuncInfo> funcList) {
        List<FuncInfoDto> result = new ArrayList<>();

        // 先找出一级菜单
        for (FuncInfo func : funcList) {
            if (func.getParentId() == 0L && func.getLevelVal() != null && func.getLevelVal() == 1) {
                FuncInfoDto dto = convertToDto(func);
                result.add(dto);
            }
        }

        // 再找出二级菜单
        for (FuncInfoDto parentDto : result) {
            List<FuncInfoDto> subItems = new ArrayList<>();
            for (FuncInfo func : funcList) {
                if (parentDto.getId().equals(func.getParentId()) && func.getLevelVal() != null && func.getLevelVal() == 2) {
                    subItems.add(convertToDto(func));
                }
            }
            if (!subItems.isEmpty()) {
                parentDto.setSubItems(subItems);
            }
        }

        return result;
    }

    private FuncInfoDto convertToDto(FuncInfo func) {
        FuncInfoDto dto = new FuncInfoDto();
        dto.setId(func.getId());
        dto.setCode(func.getCode());
        dto.setName(func.getName());
        dto.setRoutePath(func.getRoutePath());
        dto.setStyleName(func.getStyleName());
        dto.setLevelVal(func.getLevelVal());
        dto.setSortNum(func.getSortNum());
        return dto;
    }

    /**
     * 创建用户（带默认密码）
     */
    @Transactional(rollbackFor = Exception.class)
    public UserInfo createUser(UserInfo userInfo) {
        String defaultPwd = "123456";
        userInfo.setSalt(Helper.getUUID());
        userInfo.setPassWord(Helper.getMd5Str(defaultPwd, userInfo.getCode() + userInfo.getSalt()));
        save(userInfo);
        return userInfo;
    }
}
