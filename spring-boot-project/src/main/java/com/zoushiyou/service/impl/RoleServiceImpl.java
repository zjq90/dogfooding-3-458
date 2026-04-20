package com.zoushiyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoushiyou.common.BusinessException;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.Role;
import com.zoushiyou.mapper.RoleMapper;
import com.zoushiyou.service.RoleService;
import com.zoushiyou.util.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final SnowflakeIdGenerator idGenerator;

    @Override
    public Role getById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public PageInfo<Role> getPage(PageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<Role> list = roleMapper.selectList(queryDTO.getKeyword());
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Role role) {
        role.setId(idGenerator.nextId());
        role.setCreateTime(LocalDateTime.now());
        role.setVersion(1);
        role.setIsDelete(0);
        role.setIsEnable(1);
        if (role.getSortNum() == null) {
            role.setSortNum(1);
        }
        return roleMapper.insert(role) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        int result = roleMapper.update(role);
        if (result == 0) {
            throw new BusinessException("数据已被修改，请刷新后重试");
        }
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id, Long operatorId) {
        return roleMapper.deleteById(id, operatorId) > 0;
    }
}
