package com.zoushiyou.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoushiyou.common.ConstValue;
import com.zoushiyou.common.ResultVo;
import com.zoushiyou.dto.UserLoginInfoDto;
import com.zoushiyou.entity.UserInfo;
import com.zoushiyou.exception.BusinessException;
import com.zoushiyou.service.UserInfoService;
import com.zoushiyou.util.Helper;
import com.zoushiyou.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/v1/users")
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResultVo<UserLoginInfoDto> login(@RequestBody java.util.Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        UserInfo user = userInfoService.findByUserName(username);
        if (user == null) {
            throw new BusinessException("账号输入错误!");
        }
        String encodedPassword = Helper.getMd5Str(password, username.trim() + user.getSalt());
        if (!user.getPassWord().equals(encodedPassword)) {
            throw new BusinessException("密码输入错误!");
        }
        if (user.getIsEnable() != ConstValue.IS_ENABLE) {
            throw new BusinessException("账号没有激活,请联系管理人员！");
        }
        if (user.getIsDelete() == ConstValue.IS_DELETE) {
            throw new BusinessException("账号已经删除,请联系管理人员！");
        }
        String token = jwtUtil.sign(user.getId(), encodedPassword);
        UserLoginInfoDto userLoginInfoDto = userInfoService.buildLoginData(user, token);
        return ResultVo.success(userLoginInfoDto);
    }
    
    @ApiOperation("检查用户名是否存在")
    @GetMapping("/check-username")
    public ResultVo<Void> checkUsername(@RequestParam String username) {
        UserInfo fInfo = userInfoService.findByUserName(username.trim());
        if (fInfo != null) {
            throw new BusinessException("用户名已添加，请重新输入！");
        }
        return new ResultVo<>();
    }
    
    @ApiOperation("创建用户")
    @PostMapping
    public ResultVo<UserInfo> create(HttpServletRequest request, @RequestBody UserInfo user) {
        UserInfo fInfo = userInfoService.findByUserName(user.getCode());
        if (fInfo != null) {
            throw new BusinessException("用户名已添加，请重新输入！");
        }
        String defaultPwd = "123456";
        user.setSalt(Helper.getUUID());
        user.setPassWord(Helper.getMd5Str(defaultPwd, user.getCode() + user.getSalt()));
        user.setCreateTime(new Date());
        user.setIsEnable(ConstValue.IS_ENABLE);
        user.setIsDelete(ConstValue.NOT_DELETE);
        userInfoService.save(user);
        return ResultVo.success(user);
    }
    
    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public ResultVo<UserInfo> getById(@PathVariable Long id) {
        UserInfo user = userInfoService.getById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return ResultVo.success(user);
    }
    
    @ApiOperation("分页查询用户列表")
    @GetMapping
    public ResultVo<Page<UserInfo>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        Page<UserInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(UserInfo::getName, name);
        }
        wrapper.orderByAsc(UserInfo::getSortNum);
        userInfoService.page(page, wrapper);
        return ResultVo.success(page, (int) page.getTotal());
    }
    
    @ApiOperation("更新用户")
    @PutMapping("/{id}")
    public ResultVo<UserInfo> update(HttpServletRequest request, @PathVariable Long id, @RequestBody UserInfo user) {
        UserInfo existUser = userInfoService.getById(id);
        if (existUser == null) {
            throw new BusinessException("用户不存在");
        }
        user.setId(id);
        user.setUpdateTime(new Date());
        userInfoService.updateById(user);
        return ResultVo.success(user);
    }
    
    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public ResultVo<Boolean> delete(@PathVariable Long id) {
        boolean result = userInfoService.removeById(id);
        if (!result) {
            throw new BusinessException("删除用户失败");
        }
        return ResultVo.success(true);
    }
}
