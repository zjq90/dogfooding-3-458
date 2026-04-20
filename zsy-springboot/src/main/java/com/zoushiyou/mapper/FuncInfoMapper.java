package com.zoushiyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoushiyou.entity.FuncInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FuncInfoMapper extends BaseMapper<FuncInfo> {
    
    @Select("SELECT f.* FROM tb_funcinfo f " +
            "INNER JOIN tb_rolejoinfunc rj ON f.id = rj.func_id " +
            "WHERE rj.role_id = #{roleId} AND f.is_delete = 0 " +
            "ORDER BY f.sort_num")
    List<FuncInfo> findByRoleId(@Param("roleId") Long roleId);
}
