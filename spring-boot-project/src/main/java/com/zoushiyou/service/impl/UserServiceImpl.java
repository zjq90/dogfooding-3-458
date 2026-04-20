package com.zoushiyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoushiyou.common.BusinessException;
import com.zoushiyou.dto.LoginResultDTO;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.dto.UserLoginDTO;
import com.zoushiyou.entity.User;
import com.zoushiyou.mapper.UserMapper;
import com.zoushiyou.service.UserService;
import com.zoushiyou.util.JwtUtil;
import com.zoushiyou.util.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final SnowflakeIdGenerator idGenerator;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResultDTO login(UserLoginDTO loginDTO) {
        User user = userMapper.selectByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex((loginDTO.getPassword() + user.getSalt()).getBytes());
        if (!encryptedPassword.equals(user.getPassWord())) {
            throw new BusinessException("密码错误");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getCode());
        return new LoginResultDTO(user.getId(), user.getName(), token);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageInfo<User> getPage(PageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<User> list = userMapper.selectList(queryDTO.getKeyword());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(User user) {
        user.setId(idGenerator.nextId());
        user.setCreateTime(LocalDateTime.now());
        user.setVersion(1);
        user.setIsDelete(0);
        user.setIsEnable(1);
        if (user.getSortNum() == null) {
            user.setSortNum(1);
        }

        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassWord(DigestUtils.md5DigestAsHex((user.getPassWord() + salt).getBytes()));

        return userMapper.insert(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        int result = userMapper.update(user);
        if (result == 0) {
            throw new BusinessException("数据已被修改，请刷新后重试");
        }
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id, Long operatorId) {
        return userMapper.deleteById(id, operatorId) > 0;
    }
}
