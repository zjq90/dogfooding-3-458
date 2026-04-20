package com.zoushiyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoushiyou.model.entity.FuncInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能菜单Mapper
 */
@Mapper
public interface FuncInfoMapper extends BaseMapper<FuncInfo> {

    /**
     * 根据角色ID查询功能列表
     */
    @Select("SELECT f.* FROM tb_funcinfo f " +
            "INNER JOIN tb_rolejoinfunc rf ON f.id = rf.func_id " +
            "WHERE rf.role_id = #{roleId} AND f.is_delete = 0 AND rf.is_delete = 0 " +
            "ORDER BY f.sort_num")
    List<FuncInfo> findByRoleId(@Param("roleId") Long roleId);
}
