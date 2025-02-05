DROP DATABASE IF EXISTS `zeus_upms`;

CREATE DATABASE  `zeus_upms` default CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE zeus_upms;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
create table sys_dept
(
    id          bigint                             not null auto_increment comment '部门id',
    name        varchar(255)                       not null comment '部门名称',
    parent_id   bigint   default 0                 not null comment '父级部门id',
    sort        int      default 1                 not null comment '排序',
    create_by   varchar(255)                       not null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by   varchar(255)                       not null comment '修改人',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    deleted     bit      default b'0'              not null comment '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE,
    CONSTRAINT uk_name UNIQUE (name)
) COMMENT '部门表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
create table `sys_menu`
(
    `id`          bigint       not null auto_increment comment '菜单id',
    `name`        varchar(255) not null comment '菜单名称',
    `parent_id`   bigint       not null default 0 comment '父菜单ID',
    `type`        tinyint      not null comment '菜单类型（0:目录；1:菜单；2:按钮；3:外链）',
    `permission`  varchar(255) not null default '' comment '权限标识',
    `path`        varchar(255) NULL     default '' comment '路由地址',
    `icon`        varchar(255) NULL     default '#' comment '菜单图标',
    `component`   varchar(255) NULL     default NULL comment '组件路径',
    `hide`        bit(1)       not null default b'1' comment '是否隐藏（0:显示；1:隐藏）',
    `sort`        int          not null default 0 comment '显示顺序',
    `create_by`   varchar(255) not null comment '创建人',
    `create_time` datetime     not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(255) not null default ' ' comment '修改人',
    `update_time` datetime     not null default CURRENT_TIMESTAMP comment '更新时间',
    `deleted`     bit(1)       not null default b'0' comment '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) comment = '菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
create table `sys_role`
(
    `id`          bigint       not null auto_increment comment '角色id',
    `name`        varchar(255) not null comment '角色名称',
    `description` varchar(255) null comment '角色描述',
    `permission`  varchar(255) not null comment '角色权限标识，需要以 ROLE_ 开头，大写英文字母，下划线分隔',
    `data_scope`  tinyint      not null comment '数据权限（0:全部数据权限;1:本部门及子部门数据权限;2:本部门数据权限;3:本人数据权限）',
    `status`      tinyint      not null comment '状态（0：正常 1：停用）',
    `sort`        int          not null default 0 comment '显示顺序',
    `create_by`   varchar(255) not null comment '创建人',
    `create_time` datetime     not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(255) not null default ' ' comment '修改人',
    `update_time` datetime     not null default CURRENT_TIMESTAMP comment '更新时间',
    `deleted`     bit(1)       not null default b'0' comment '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) comment = '角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
create table `sys_role_menu`
(
    `id`          bigint       not null AUTO_INCREMENT comment '自增编号',
    `role_id`     bigint       not null comment '角色ID',
    `menu_id`     bigint       not null comment '菜单ID',
    `create_by`   varchar(255) not null comment '创建人',
    `create_time` datetime     not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(255) not null default ' ' comment '修改人',
    `update_time` datetime     not null default CURRENT_TIMESTAMP comment '更新时间',
    `deleted`     bit(1)       not null default b'0' comment '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) comment = '角色菜单关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------

DROP TABLE IF EXISTS `sys_user`;
create table sys_user
(
    id                  bigint                             not null comment '用户ID',
    username            varchar(255)                       not null comment '用户名称',
    fullname            varchar(255)                       null comment '真实姓名',
    nickname            varchar(255)                       null comment '昵称',
    password            varchar(255)                       null comment '密码',
    gender              tinyint  default 1                 null comment '用户性别（0：女；1：男）',
    phone               varchar(15)                        null comment '手机号码',
    email               varchar(255)                       null comment '邮箱',
    avatar              varchar(255)                       null comment '头像',
    birthday            date                               null comment ' 生日',
    status              tinyint  default 0                 not null comment '状态（0：正常 1：冻结）',
    last_login_ip       varchar(15)                        null comment '最后登录IP',
    last_login_datetime datetime                           null comment '最后登录时间',
    create_by           varchar(64)                        not null comment '创建人',
    create_time         datetime default CURRENT_TIMESTAMP not null  comment '创建时间',
    update_by           varchar(64)                        not null comment '修改人',
    update_time         datetime default CURRENT_TIMESTAMP not null  comment '更新时间',
    deleted             bit      default b'0'              not null comment '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE,
    constraint uk_phone unique (phone),
    constraint uk_username unique (username)
) comment '用户表';

DROP TABLE IF EXISTS `sys_user_dept_role`;
create table sys_user_dept_role
(
    id          bigint                             not null comment '自增记录ID',
    user_id     bigint                             not null comment '用户ID',
    dept_id     bigint                             not null comment '部门ID',
    role_id     bigint                             not null comment '角色ID',
    create_by   varchar(255)                       not null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by   varchar(255)                       not null comment '修改人',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    deleted     bit      default b'0'              not null comment '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE,
    constraint uk_udr unique (user_id, dept_id, role_id)
) comment '用户部门角色关联表';

# init data

INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('前端开发组', 1, 0, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('部署组', 4, 0, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('研发部', 0, 0, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('测试中心', 0, 2, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('运维中心', 0, 3, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('后端开发组', 1, 1, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('UI 设计组', 1, 2, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('工具开发组', 4, 1, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('经理室', 0, 4, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('人事部', 0, 5, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, sort, create_by, update_by) VALUES ('产品部', 0, 1, 'admin', 'admin');

INSERT INTO zeus_upms.sys_user (id, username, fullname, nickname, password, gender, phone, email, avatar, birthday, status, last_login_ip, last_login_datetime, create_by, create_time, update_by, update_time, deleted)
VALUES (1, 'admin', 'AlexChen', '小陈', '$2a$10$r1dGczOWKoSxCMGBvXDwSOwp8aTe9Kgf/Nz7jRkHOEuWghAXUEvA6', 1, '18342212760', 'alexchen.tech@gmail.com', '', '1997-10-01', '1', null, null, 'admin', '2025-01-01 00:00:00', 'admin', '2025-01-01 00:00:00', false);
