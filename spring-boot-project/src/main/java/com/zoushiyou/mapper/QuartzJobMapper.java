package com.zoushiyou.mapper;

import com.zoushiyou.entity.QuartzJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuartzJobMapper {

    @Select("SELECT * FROM tb_quartzjob WHERE id = #{id} AND is_delete = 0")
    QuartzJob selectById(Long id);

    @Select("<script>" +
            "SELECT * FROM tb_quartzjob WHERE is_delete = 0 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND (name LIKE CONCAT('%', #{keyword}, '%') OR job_group LIKE CONCAT('%', #{keyword}, '%'))" +
            "</if>" +
            "ORDER BY sort_num ASC, create_time DESC" +
            "</script>")
    List<QuartzJob> selectList(String keyword);

    @Insert("INSERT INTO tb_quartzjob (id, parent_id, version, code, name, remarks, sort_num, " +
            "is_enable, is_delete, owner_id, create_id, update_id, create_time, update_time, " +
            "job_group, job_status, job_cron) " +
            "VALUES (#{id}, #{parentId}, #{version}, #{code}, #{name}, #{remarks}, #{sortNum}, " +
            "#{isEnable}, #{isDelete}, #{ownerId}, #{createId}, #{updateId}, #{createTime}, #{updateTime}, " +
            "#{jobGroup}, #{jobStatus}, #{jobCron})")
    int insert(QuartzJob quartzJob);

    @Update("UPDATE tb_quartzjob SET parent_id = #{parentId}, version = version + 1, code = #{code}, " +
            "name = #{name}, remarks = #{remarks}, sort_num = #{sortNum}, is_enable = #{isEnable}, " +
            "owner_id = #{ownerId}, update_id = #{updateId}, update_time = #{updateTime}, " +
            "job_group = #{jobGroup}, job_status = #{jobStatus}, job_cron = #{jobCron} " +
            "WHERE id = #{id} AND version = #{version}")
    int update(QuartzJob quartzJob);

    @Update("UPDATE tb_quartzjob SET is_delete = 1, update_id = #{updateId}, update_time = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id, @Param("updateId") Long updateId);
}
