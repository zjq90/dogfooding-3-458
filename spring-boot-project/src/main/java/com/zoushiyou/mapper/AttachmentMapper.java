package com.zoushiyou.mapper;

import com.zoushiyou.entity.Attachment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttachmentMapper {

    @Select("SELECT id, code, name, fk_type, fk_pk_id, suffix, create_time, create_id " +
            "FROM tb_attachments WHERE id = #{id} AND is_delete = 0")
    Attachment selectById(Long id);

    @Select("SELECT id, code, name, fk_type, fk_pk_id, suffix, create_time " +
            "FROM tb_attachments WHERE fk_pk_id = #{fkPkId} AND fk_type = #{fkType} AND is_delete = 0")
    List<Attachment> selectByFk(@Param("fkPkId") Long fkPkId, @Param("fkType") Integer fkType);

    @Select("SELECT stream_data FROM tb_attachments WHERE id = #{id}")
    byte[] selectStreamDataById(Long id);

    @Insert("INSERT INTO tb_attachments (id, code, name, fk_type, fk_pk_id, suffix, stream_data, " +
            "create_id, create_time, is_delete) " +
            "VALUES (#{id}, #{code}, #{name}, #{fkType}, #{fkPkId}, #{suffix}, #{streamData}, " +
            "#{createId}, NOW(), 0)")
    int insert(Attachment attachment);

    @Update("UPDATE tb_attachments SET is_delete = 1, update_id = #{updateId}, update_time = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id, @Param("updateId") Long updateId);
}
