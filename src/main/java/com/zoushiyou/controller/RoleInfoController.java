package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.model.dto.PageDto;
import com.zoushiyou.model.dto.ResultVo;
import com.zoushiyou.model.entity.RoleInfo;
import com.zoushiyou.service.RoleInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色信息控制器
 */
@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "角色管理", description = "角色信息相关接口")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    /**
     * 分页查询角色列表
     */
    @GetMapping
    @Operation(summary = "查询角色列表", description = "分页查询角色信息")
    public ResultVo<PageDto<RoleInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {

        LambdaQueryWrapper<RoleInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleInfo::getIsDelete, 0);

        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(RoleInfo::getCode, keyword)
                    .or()
                    .like(RoleInfo::getName, keyword));
        }

        wrapper.orderByAsc(RoleInfo::getSortNum);
        Page<RoleInfo> page = roleInfoService.page(new Page<>(pageIndex, pageSize), wrapper);

        PageDto<RoleInfo> pageDto = new PageDto<>();
        pageDto.setPageIndex((int) page.getCurrent());
        pageDto.setPageSize((int) page.getSize());
        pageDto.setTotal(page.getTotal());
        pageDto.setRecords(page.getRecords());

        return ResultVo.success(pageDto, (int) page.getTotal());
    }

    /**
     * 查询所有角色（不分页）
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有角色", description = "查询所有角色信息")
    public ResultVo<List<RoleInfo>> all() {
        LambdaQueryWrapper<RoleInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleInfo::getIsDelete, 0);
        wrapper.orderByAsc(RoleInfo::getSortNum);
        return ResultVo.success(roleInfoService.list(wrapper));
    }

    /**
     * 根据ID查询角色
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询角色详情", description = "根据ID查询角色详细信息")
    public ResultVo<RoleInfo> getById(@PathVariable Long id) {
        RoleInfo role = roleInfoService.getById(id);
        if (role == null || (role.getIsDelete() != null && role.getIsDelete() == 1)) {
            return ResultVo.error("角色不存在");
        }
        return ResultVo.success(role);
    }

    /**
     * 创建角色
     */
    @PostMapping
    @Operation(summary = "创建角色", description = "创建新角色")
    public ResultVo<RoleInfo> create(@Valid @RequestBody RoleInfo roleInfo) {
        roleInfoService.save(roleInfo);
        return ResultVo.success(roleInfo);
    }

    /**
     * 更新角色
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新角色", description = "更新角色信息")
    public ResultVo<RoleInfo> update(@PathVariable Long id, @Valid @RequestBody RoleInfo roleInfo) {
        RoleInfo existRole = roleInfoService.getById(id);
        if (existRole == null || (existRole.getIsDelete() != null && existRole.getIsDelete() == 1)) {
            return ResultVo.error("角色不存在");
        }

        roleInfo.setId(id);
        boolean success = roleInfoService.updateById(roleInfo);
        if (!success) {
            return ResultVo.error("更新失败");
        }

        return ResultVo.success(roleInfoService.getById(id));
    }

    /**
     * 删除角色（逻辑删除）
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色", description = "逻辑删除角色")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean success = roleInfoService.removeById(id);
        if (!success) {
            return ResultVo.error("删除失败");
        }
        return ResultVo.success(true);
    }
}
