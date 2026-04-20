package com.zoushiyou.controller;

import com.github.pagehelper.PageInfo;
import com.zoushiyou.common.Result;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.Role;
import com.zoushiyou.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @ApiOperation("根据ID获取角色")
    @GetMapping("/{id}")
    public Result<Role> getById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.success(role);
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/all")
    public Result<List<Role>> getAll() {
        List<Role> list = roleService.getAll();
        return Result.success(list);
    }

    @ApiOperation("分页查询角色列表")
    @GetMapping
    public Result<PageInfo<Role>> getPage(PageQueryDTO queryDTO) {
        PageInfo<Role> page = roleService.getPage(queryDTO);
        return Result.success(page);
    }

    @ApiOperation("新增角色")
    @PostMapping
    public Result<Void> save(@RequestBody Role role) {
        roleService.save(role);
        return Result.success();
    }

    @ApiOperation("更新角色")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);
        return Result.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        roleService.delete(id, 1L);
        return Result.success();
    }
}
