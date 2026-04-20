package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.common.ResultVo;
import com.zoushiyou.entity.RoleInfo;
import com.zoushiyou.exception.BusinessException;
import com.zoushiyou.service.RoleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/v1/roles")
public class RoleInfoController {
    
    @Autowired
    private RoleInfoService roleInfoService;
    
    @ApiOperation("创建角色")
    @PostMapping
    public ResultVo<RoleInfo> create(@RequestBody RoleInfo roleInfo) {
        roleInfo.setCreateTime(new Date());
        roleInfoService.save(roleInfo);
        return ResultVo.success(roleInfo);
    }
    
    @ApiOperation("获取角色详情")
    @GetMapping("/{id}")
    public ResultVo<RoleInfo> getById(@PathVariable Long id) {
        RoleInfo roleInfo = roleInfoService.getById(id);
        if (roleInfo == null) {
            throw new BusinessException("角色不存在");
        }
        return ResultVo.success(roleInfo);
    }
    
    @ApiOperation("分页查询角色列表")
    @GetMapping
    public ResultVo<Page<RoleInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        Page<RoleInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<RoleInfo> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(RoleInfo::getName, name);
        }
        wrapper.orderByAsc(RoleInfo::getSortNum);
        roleInfoService.page(page, wrapper);
        return ResultVo.success(page, (int) page.getTotal());
    }
    
    @ApiOperation("更新角色")
    @PutMapping("/{id}")
    public ResultVo<RoleInfo> update(@PathVariable Long id, @RequestBody RoleInfo roleInfo) {
        RoleInfo existRole = roleInfoService.getById(id);
        if (existRole == null) {
            throw new BusinessException("角色不存在");
        }
        roleInfo.setId(id);
        roleInfo.setUpdateTime(new Date());
        roleInfoService.updateById(roleInfo);
        return ResultVo.success(roleInfo);
    }
    
    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean result = roleInfoService.removeById(id);
        if (!result) {
            throw new BusinessException("删除角色失败");
        }
        return ResultVo.success(true);
    }
}
