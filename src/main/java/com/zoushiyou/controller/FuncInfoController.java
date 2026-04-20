package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.model.dto.PageDto;
import com.zoushiyou.model.dto.ResultVo;
import com.zoushiyou.model.entity.FuncInfo;
import com.zoushiyou.service.FuncInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能菜单控制器
 */
@RestController
@RequestMapping("/api/v1/funcs")
@Tag(name = "菜单管理", description = "功能菜单相关接口")
public class FuncInfoController {

    @Autowired
    private FuncInfoService funcInfoService;

    /**
     * 分页查询菜单列表
     */
    @GetMapping
    @Operation(summary = "查询菜单列表", description = "分页查询功能菜单信息")
    public ResultVo<PageDto<FuncInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {

        LambdaQueryWrapper<FuncInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FuncInfo::getIsDelete, 0);

        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(FuncInfo::getCode, keyword)
                    .or()
                    .like(FuncInfo::getName, keyword));
        }

        wrapper.orderByAsc(FuncInfo::getSortNum);
        Page<FuncInfo> page = funcInfoService.page(new Page<>(pageIndex, pageSize), wrapper);

        PageDto<FuncInfo> pageDto = new PageDto<>();
        pageDto.setPageIndex((int) page.getCurrent());
        pageDto.setPageSize((int) page.getSize());
        pageDto.setTotal(page.getTotal());
        pageDto.setRecords(page.getRecords());

        return ResultVo.success(pageDto, (int) page.getTotal());
    }

    /**
     * 查询所有菜单（不分页）
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有菜单", description = "查询所有功能菜单信息")
    public ResultVo<List<FuncInfo>> all() {
        LambdaQueryWrapper<FuncInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FuncInfo::getIsDelete, 0);
        wrapper.orderByAsc(FuncInfo::getSortNum);
        return ResultVo.success(funcInfoService.list(wrapper));
    }

    /**
     * 根据ID查询菜单
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询菜单详情", description = "根据ID查询菜单详细信息")
    public ResultVo<FuncInfo> getById(@PathVariable Long id) {
        FuncInfo func = funcInfoService.getById(id);
        if (func == null || (func.getIsDelete() != null && func.getIsDelete() == 1)) {
            return ResultVo.error("菜单不存在");
        }
        return ResultVo.success(func);
    }

    /**
     * 创建菜单
     */
    @PostMapping
    @Operation(summary = "创建菜单", description = "创建新功能菜单")
    public ResultVo<FuncInfo> create(@Valid @RequestBody FuncInfo funcInfo) {
        funcInfoService.save(funcInfo);
        return ResultVo.success(funcInfo);
    }

    /**
     * 更新菜单
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新菜单", description = "更新功能菜单信息")
    public ResultVo<FuncInfo> update(@PathVariable Long id, @Valid @RequestBody FuncInfo funcInfo) {
        FuncInfo existFunc = funcInfoService.getById(id);
        if (existFunc == null || (existFunc.getIsDelete() != null && existFunc.getIsDelete() == 1)) {
            return ResultVo.error("菜单不存在");
        }

        funcInfo.setId(id);
        boolean success = funcInfoService.updateById(funcInfo);
        if (!success) {
            return ResultVo.error("更新失败");
        }

        return ResultVo.success(funcInfoService.getById(id));
    }

    /**
     * 删除菜单（逻辑删除）
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜单", description = "逻辑删除功能菜单")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean success = funcInfoService.removeById(id);
        if (!success) {
            return ResultVo.error("删除失败");
        }
        return ResultVo.success(true);
    }
}
