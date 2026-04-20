package com.zoushiyou.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "parentId", Long.class, 0L);
        this.strictInsertFill(metaObject, "isEnable", Integer.class, 1);
        this.strictInsertFill(metaObject, "isDelete", Integer.class, 0);
        this.strictInsertFill(metaObject, "ownerId", Long.class, 0L);
        this.strictInsertFill(metaObject, "createId", Long.class, 0L);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateId", Long.class, 0L);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
