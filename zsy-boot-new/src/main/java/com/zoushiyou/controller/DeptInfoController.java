package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.model.dto.PageDto;
import com.zoushiyou.model.dto.ResultVo;
import com.zoushiyou.model.entity.DeptInfo;
import com.zoushiyou.service.DeptInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门信息控制器
 */
@RestController
@RequestMapping("/api/v1/depts")
@Tag(name = "部门管理", description = "部门信息相关接口")
public class DeptInfoController {

    @Autowired
    private DeptInfoService deptInfoService;

    /**
     * 分页查询部门列表
     */
    @GetMapping
    @Operation(summary = "查询部门列表", description = "分页查询部门信息")
    public ResultVo<PageDto<DeptInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {

        LambdaQueryWrapper<DeptInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeptInfo::getIsDelete, 0);

        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(DeptInfo::getCode, keyword)
                    .or()
                    .like(DeptInfo::getName, keyword));
        }

        wrapper.orderByAsc(DeptInfo::getSortNum);
        Page<DeptInfo> page = deptInfoService.page(new Page<>(pageIndex, pageSize), wrapper);

        PageDto<DeptInfo> pageDto = new PageDto<>();
        pageDto.setPageIndex((int) page.getCurrent());
        pageDto.setPageSize((int) page.getSize());
        pageDto.setTotal(page.getTotal());
        pageDto.setRecords(page.getRecords());

        return ResultVo.success(pageDto, (int) page.getTotal());
    }

    /**
     * 查询所有部门（不分页）
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有部门", description = "查询所有部门信息")
    public ResultVo<List<DeptInfo>> all() {
        LambdaQueryWrapper<DeptInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeptInfo::getIsDelete, 0);
        wrapper.orderByAsc(DeptInfo::getSortNum);
        return ResultVo.success(deptInfoService.list(wrapper));
    }

    /**
     * 根据ID查询部门
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询部门详情", description = "根据ID查询部门详细信息")
    public ResultVo<DeptInfo> getById(@PathVariable Long id) {
        DeptInfo dept = deptInfoService.getById(id);
        if (dept == null || (dept.getIsDelete() != null && dept.getIsDelete() == 1)) {
            return ResultVo.error("部门不存在");
        }
        return ResultVo.success(dept);
    }

    /**
     * 创建部门
     */
    @PostMapping
    @Operation(summary = "创建部门", description = "创建新部门")
    public ResultVo<DeptInfo> create(@Valid @RequestBody DeptInfo deptInfo) {
        deptInfoService.save(deptInfo);
        return ResultVo.success(deptInfo);
    }

    /**
     * 更新部门
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新部门", description = "更新部门信息")
    public ResultVo<DeptInfo> update(@PathVariable Long id, @Valid @RequestBody DeptInfo deptInfo) {
        DeptInfo existDept = deptInfoService.getById(id);
        if (existDept == null || (existDept.getIsDelete() != null && existDept.getIsDelete() == 1)) {
            return ResultVo.error("部门不存在");
        }

        deptInfo.setId(id);
        boolean success = deptInfoService.updateById(deptInfo);
        if (!success) {
            return ResultVo.error("更新失败");
        }

        return ResultVo.success(deptInfoService.getById(id));
    }

    /**
     * 删除部门（逻辑删除）
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除部门", description = "逻辑删除部门")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean success = deptInfoService.removeById(id);
        if (!success) {
            return ResultVo.error("删除失败");
        }
        return ResultVo.success(true);
    }
}
