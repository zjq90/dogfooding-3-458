package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.common.ResultVo;
import com.zoushiyou.entity.DeptInfo;
import com.zoushiyou.exception.BusinessException;
import com.zoushiyou.service.DeptInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "部门管理")
@RestController
@RequestMapping("/api/v1/departments")
public class DeptInfoController {
    
    @Autowired
    private DeptInfoService deptInfoService;
    
    @ApiOperation("创建部门")
    @PostMapping
    public ResultVo<DeptInfo> create(@RequestBody DeptInfo deptInfo) {
        deptInfo.setCreateTime(new Date());
        deptInfoService.save(deptInfo);
        return ResultVo.success(deptInfo);
    }
    
    @ApiOperation("获取部门详情")
    @GetMapping("/{id}")
    public ResultVo<DeptInfo> getById(@PathVariable Long id) {
        DeptInfo deptInfo = deptInfoService.getById(id);
        if (deptInfo == null) {
            throw new BusinessException("部门不存在");
        }
        return ResultVo.success(deptInfo);
    }
    
    @ApiOperation("分页查询部门列表")
    @GetMapping
    public ResultVo<Page<DeptInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        Page<DeptInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<DeptInfo> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(DeptInfo::getName, name);
        }
        wrapper.orderByAsc(DeptInfo::getSortNum);
        deptInfoService.page(page, wrapper);
        return ResultVo.success(page, (int) page.getTotal());
    }
    
    @ApiOperation("更新部门")
    @PutMapping("/{id}")
    public ResultVo<DeptInfo> update(@PathVariable Long id, @RequestBody DeptInfo deptInfo) {
        DeptInfo existDept = deptInfoService.getById(id);
        if (existDept == null) {
            throw new BusinessException("部门不存在");
        }
        deptInfo.setId(id);
        deptInfo.setUpdateTime(new Date());
        deptInfoService.updateById(deptInfo);
        return ResultVo.success(deptInfo);
    }
    
    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean result = deptInfoService.removeById(id);
        if (!result) {
            throw new BusinessException("删除部门失败");
        }
        return ResultVo.success(true);
    }
}
