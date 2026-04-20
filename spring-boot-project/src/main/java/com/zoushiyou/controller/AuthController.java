package com.zoushiyou.controller;

import com.zoushiyou.common.Result;
import com.zoushiyou.dto.LoginResultDTO;
import com.zoushiyou.dto.UserLoginDTO;
import com.zoushiyou.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "认证接口")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<LoginResultDTO> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        LoginResultDTO result = userService.login(loginDTO);
        return Result.success(result);
    }
}
