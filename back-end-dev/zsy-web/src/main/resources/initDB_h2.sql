-- H2 Database Init Script
-- Compatible with H2 Database

DROP TABLE IF EXISTS tb_attachments;
DROP TABLE IF EXISTS tb_quartzjob;
DROP TABLE IF EXISTS tb_syslogs;
DROP TABLE IF EXISTS tb_student;
DROP TABLE IF EXISTS tb_rolejoinfunc;
DROP TABLE IF EXISTS tb_funcinfo;
DROP TABLE IF EXISTS tb_userinfo;
DROP TABLE IF EXISTS tb_roleinfo;
DROP TABLE IF EXISTS tb_deptinfo;

CREATE TABLE tb_deptinfo (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL
);

CREATE TABLE tb_funcinfo (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL,
    routePath VARCHAR(150) DEFAULT NULL,
    styleName VARCHAR(150) DEFAULT NULL,
    levelVal INT DEFAULT NULL
);

CREATE TABLE tb_roleinfo (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL
);

CREATE TABLE tb_rolejoinfunc (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL,
    roleId BIGINT DEFAULT NULL,
    funcId BIGINT DEFAULT NULL,
    levelVal INT DEFAULT NULL
);

CREATE TABLE tb_student (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL
);

CREATE TABLE tb_syslogs (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL,
    opererType INT DEFAULT NULL,
    opererContent VARCHAR(450) DEFAULT NULL
);

CREATE TABLE tb_quartzjob (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL,
    jobGroup VARCHAR(50) DEFAULT NULL,
    jobStatus INT DEFAULT NULL,
    jobCron VARCHAR(50) DEFAULT NULL
);

CREATE TABLE tb_userinfo (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) UNIQUE DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL,
    passWord VARCHAR(150) DEFAULT NULL,
    salt VARCHAR(36) DEFAULT NULL,
    is_Male INT DEFAULT NULL,
    phoneNum VARCHAR(15) DEFAULT NULL,
    roleId BIGINT DEFAULT NULL,
    deptId BIGINT DEFAULT NULL
);

CREATE TABLE tb_attachments (
    id BIGINT PRIMARY KEY NOT NULL,
    parentId BIGINT DEFAULT NULL,
    version INT DEFAULT 1,
    code VARCHAR(50) DEFAULT NULL,
    name VARCHAR(50) DEFAULT NULL,
    remarks VARCHAR(150) DEFAULT NULL,
    sortNum INT DEFAULT 1,
    is_Enable INT DEFAULT 1,
    is_Delete INT DEFAULT 0,
    ownerId BIGINT DEFAULT NULL,
    createId BIGINT NOT NULL,
    updateId BIGINT DEFAULT NULL,
    createTime TIMESTAMP NOT NULL,
    updateTime TIMESTAMP DEFAULT NULL,
    fkType INT DEFAULT NULL,
    fkPkId BIGINT DEFAULT NULL,
    suffix VARCHAR(20) DEFAULT NULL,
    streamData BLOB DEFAULT NULL
);

INSERT INTO tb_userinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, passWord, is_Male, phoneNum, roleId, deptId, salt)
VALUES (1, 0, 1, 'admin', '超级用户', '超级管理员', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 'e97ab12c758201995e51635e2e78cd33', 1, '15812345678', 1, 1, '8b4757dc-99d6-446f-907a-d4b8f6060a8e');

INSERT INTO tb_roleinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime)
VALUES (1, 0, 1, '1', '超级管理员', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00');

INSERT INTO tb_roleinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime)
VALUES (2, 0, 1, '2', '管理员', '', 2, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00');

INSERT INTO tb_deptinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime)
VALUES (1, 0, 1, '1', '开发部', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00');

INSERT INTO tb_deptinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime)
VALUES (2, 0, 1, '2', '销售部', '', 2, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00');

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (1, 0, 1, '/index', '首页', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/index', 'el-icon-goods', 1);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (2, 0, 1, '2', '学生管理', '', 2, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '2', 'el-icon-message', 1);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (3, 0, 1, '3', '系统管理', '', 3, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '3', 'el-icon-service', 1);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (4, 3, 1, '/role', '角色管理', '', 4, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/role', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (5, 3, 1, '/user-index', '用户管理', '', 5, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/user-index', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (6, 3, 1, '/func', '功能管理', '', 6, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/func', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (7, 3, 1, '/const', '常量管理', '', 7, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/const', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (8, 3, 1, '/flow', '流程管理', '', 8, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/flow', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (9, 3, 1, '/syslog', '日志管理', '', 9, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/syslog', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (11, 3, 1, '/quartz', '定时任务', '', 11, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/quartz', '', 2);

INSERT INTO tb_funcinfo (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, routePath, styleName, levelVal)
VALUES (10, 2, 1, '/student', '学生列表', '', 10, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', '/student', '', 2);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (1, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 1, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (2, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 2, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (3, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 3, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (4, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 4, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (5, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 5, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (6, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 6, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (7, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 7, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (8, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 8, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (9, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 9, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (10, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 10, 1);

INSERT INTO tb_rolejoinfunc (id, parentId, version, code, name, remarks, sortNum, is_Enable, is_Delete, ownerId, createId, updateId, createTime, updateTime, roleId, funcId, levelVal)
VALUES (11, 0, 1, '', '', '', 1, 1, 0, 1, 1, 1, TIMESTAMP '2018-08-08 00:00:00', TIMESTAMP '2018-08-08 00:00:00', 1, 11, 1);
