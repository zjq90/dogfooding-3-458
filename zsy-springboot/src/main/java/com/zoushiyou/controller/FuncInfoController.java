package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.common.ResultVo;
import com.zoushiyou.entity.FuncInfo;
import com.zoushiyou.exception.BusinessException;
import com.zoushiyou.service.FuncInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "功能管理")
@RestController
@RequestMapping("/api/v1/functions")
public class FuncInfoController {
    
    @Autowired
    private FuncInfoService funcInfoService;
    
    @ApiOperation("创建功能")
    @PostMapping
    public ResultVo<FuncInfo> create(@RequestBody FuncInfo funcInfo) {
        funcInfo.setCreateTime(new Date());
        funcInfoService.save(funcInfo);
        return ResultVo.success(funcInfo);
    }
    
    @ApiOperation("获取功能详情")
    @GetMapping("/{id}")
    public ResultVo<FuncInfo> getById(@PathVariable Long id) {
        FuncInfo funcInfo = funcInfoService.getById(id);
        if (funcInfo == null) {
            throw new BusinessException("功能不存在");
        }
        return ResultVo.success(funcInfo);
    }
    
    @ApiOperation("根据角色ID获取功能列表")
    @GetMapping("/by-role/{roleId}")
    public ResultVo<List<FuncInfo>> getByRoleId(@PathVariable Long roleId) {
        List<FuncInfo> list = funcInfoService.findByRoleId(roleId);
        return ResultVo.success(list, list.size());
    }
    
    @ApiOperation("分页查询功能列表")
    @GetMapping
    public ResultVo<Page<FuncInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        Page<FuncInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<FuncInfo> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(FuncInfo::getName, name);
        }
        wrapper.orderByAsc(FuncInfo::getSortNum);
        funcInfoService.page(page, wrapper);
        return ResultVo.success(page, (int) page.getTotal());
    }
    
    @ApiOperation("更新功能")
    @PutMapping("/{id}")
    public ResultVo<FuncInfo> update(@PathVariable Long id, @RequestBody FuncInfo funcInfo) {
        FuncInfo existFunc = funcInfoService.getById(id);
        if (existFunc == null) {
            throw new BusinessException("功能不存在");
        }
        funcInfo.setId(id);
        funcInfo.setUpdateTime(new Date());
        funcInfoService.updateById(funcInfo);
        return ResultVo.success(funcInfo);
    }
    
    @ApiOperation("删除功能")
    @DeleteMapping("/{id}")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean result = funcInfoService.removeById(id);
        if (!result) {
            throw new BusinessException("删除功能失败");
        }
        return ResultVo.success(true);
    }
}
