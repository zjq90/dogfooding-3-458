package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.model.dto.PageDto;
import com.zoushiyou.model.dto.ResultVo;
import com.zoushiyou.model.entity.UserInfo;
import com.zoushiyou.service.UserInfoService;
import com.zoushiyou.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户信息控制器
 */
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "用户管理", description = "用户信息相关接口")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 分页查询用户列表
     */
    @GetMapping
    @Operation(summary = "查询用户列表", description = "分页查询用户信息")
    public ResultVo<PageDto<UserInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {

        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getIsDelete, 0);

        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(UserInfo::getCode, keyword)
                    .or()
                    .like(UserInfo::getName, keyword));
        }

        wrapper.orderByAsc(UserInfo::getSortNum);
        Page<UserInfo> page = userInfoService.page(new Page<>(pageIndex, pageSize), wrapper);

        PageDto<UserInfo> pageDto = new PageDto<>();
        pageDto.setPageIndex((int) page.getCurrent());
        pageDto.setPageSize((int) page.getSize());
        pageDto.setTotal(page.getTotal());
        pageDto.setPages(page.getPages());
        pageDto.setRecords(page.getRecords());

        return ResultVo.success(pageDto, (int) page.getTotal());
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询用户详情", description = "根据ID查询用户详细信息")
    public ResultVo<UserInfo> getById(@PathVariable Long id) {
        UserInfo user = userInfoService.getById(id);
        if (user == null || (user.getIsDelete() != null && user.getIsDelete() == 1)) {
            return ResultVo.error("用户不存在");
        }
        return ResultVo.success(user);
    }

    /**
     * 创建用户
     */
    @PostMapping
    @Operation(summary = "创建用户", description = "创建新用户，默认密码为123456")
    public ResultVo<UserInfo> create(@Valid @RequestBody UserInfo userInfo, HttpServletRequest request) {
        // 检查用户名是否已存在
        UserInfo existUser = userInfoService.findByUserName(userInfo.getCode());
        if (existUser != null) {
            return ResultVo.error("用户名已存在，请重新输入！");
        }

        // 获取当前登录用户ID
        Long userId = getCurrentUserId(request);
        userInfo.setCreateId(userId);
        userInfo.setOwnerId(userId);

        UserInfo createdUser = userInfoService.createUser(userInfo);
        return ResultVo.success(createdUser);
    }

    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新用户", description = "更新用户信息")
    public ResultVo<UserInfo> update(@PathVariable Long id, @Valid @RequestBody UserInfo userInfo, HttpServletRequest request) {
        UserInfo existUser = userInfoService.getById(id);
        if (existUser == null || (existUser.getIsDelete() != null && existUser.getIsDelete() == 1)) {
            return ResultVo.error("用户不存在");
        }

        userInfo.setId(id);
        userInfo.setUpdateId(getCurrentUserId(request));

        boolean success = userInfoService.updateById(userInfo);
        if (!success) {
            return ResultVo.error("更新失败");
        }

        return ResultVo.success(userInfoService.getById(id));
    }

    /**
     * 删除用户（逻辑删除）
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "逻辑删除用户")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean success = userInfoService.removeById(id);
        if (!success) {
            return ResultVo.error("删除失败");
        }
        return ResultVo.success(true);
    }

    /**
     * 从请求中获取当前用户ID
     */
    private Long getCurrentUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            return 0L;
        }
        try {
            return JwtUtil.getUserId(token);
        } catch (Exception e) {
            return 0L;
        }
    }
}
