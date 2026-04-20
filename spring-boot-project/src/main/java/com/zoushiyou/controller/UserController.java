package com.zoushiyou.controller;

import com.github.pagehelper.PageInfo;
import com.zoushiyou.common.Result;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.User;
import com.zoushiyou.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation("根据ID获取用户")
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @ApiOperation("分页查询用户列表")
    @GetMapping
    public Result<PageInfo<User>> getPage(PageQueryDTO queryDTO) {
        PageInfo<User> page = userService.getPage(queryDTO);
        return Result.success(page);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public Result<Void> save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @ApiOperation("更新用户")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.delete(id, 1L);
        return Result.success();
    }
}
