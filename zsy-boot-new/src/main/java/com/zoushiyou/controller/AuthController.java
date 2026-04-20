package com.zoushiyou.controller;

import com.zoushiyou.model.dto.ResultVo;
import com.zoushiyou.model.dto.UserLoginDto;
import com.zoushiyou.model.entity.UserInfo;
import com.zoushiyou.service.UserInfoService;
import com.zoushiyou.util.Helper;
import com.zoushiyou.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "认证管理", description = "用户登录认证相关接口")
public class AuthController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "使用用户名和密码登录系统")
    public ResultVo<UserLoginDto> login(
            @RequestParam @NotBlank(message = "用户名不能为空") String username,
            @RequestParam @NotBlank(message = "密码不能为空") String password) {

        UserInfo user = userInfoService.findByUserName(username.trim());
        if (user == null) {
            return ResultVo.error("账号输入错误!");
        }

        String encodedPassword = Helper.getMd5Str(password, username.trim() + user.getSalt());
        if (!user.getPassWord().equals(encodedPassword)) {
            return ResultVo.error("密码输入错误!");
        }

        if (user.getIsEnable() != null && user.getIsEnable() != 1) {
            return ResultVo.error("账号没有激活,请联系管理人员！");
        }

        if (user.getIsDelete() != null && user.getIsDelete() == 1) {
            return ResultVo.error("账号已经删除,请联系管理人员！");
        }

        String token = JwtUtil.sign(user.getId(), encodedPassword);
        UserLoginDto loginData = userInfoService.buildLoginData(user, token);

        return ResultVo.success(loginData);
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping("/check-username")
    @Operation(summary = "检查用户名", description = "检查用户名是否已存在")
    public ResultVo<Boolean> checkUserName(@RequestParam String username) {
        UserInfo user = userInfoService.findByUserName(username.trim());
        if (user != null) {
            return ResultVo.error("用户名已存在");
        }
        return ResultVo.success(true);
    }
}
