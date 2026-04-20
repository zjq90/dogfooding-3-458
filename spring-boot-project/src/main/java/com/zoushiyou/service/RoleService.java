package com.zoushiyou.service;

import com.github.pagehelper.PageInfo;
import com.zoushiyou.dto.PageQueryDTO;
import com.zoushiyou.entity.Role;

import java.util.List;

public interface RoleService {
    Role getById(Long id);

    List<Role> getAll();

    PageInfo<Role> getPage(PageQueryDTO queryDTO);

    boolean save(Role role);

    boolean update(Role role);

    boolean delete(Long id, Long operatorId);
}
