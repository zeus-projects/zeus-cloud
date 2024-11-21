DROP DATABASE IF EXISTS `zeus_upms`;

CREATE DATABASE  `zeus_upms` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE zeus_upms;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
create table sys_dept
(
    id          bigint auto_increment comment '部门id'
        primary key,
    name        varchar(64)                        not null comment '部门名称',
    parent_id   bigint   default 0                 not null comment '父级部门id',
    weight      int      default 1                 not null comment '排序',
    create_by   varchar(64)                        not null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(64)                        not null comment '修改人',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit      default b'0'              not null comment '逻辑删除（0：正常 1：删除）',
    constraint uk_name
        unique (name)
)
    comment '部门';




-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint       not null auto_increment comment '菜单id',
    `name`        varchar(64)  not null comment '菜单名称',
    `parent_id`   bigint       NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `type`        tinyint      NOT NULL COMMENT '菜单类型',
    `sort`        int          NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `permission`  varchar(100) NOT NULL DEFAULT '' COMMENT '权限标识',
    `path`        varchar(200) NULL     DEFAULT '' COMMENT '路由地址',
    `icon`        varchar(100) NULL     DEFAULT '#' COMMENT '菜单图标',
    `component`   varchar(255) NULL     DEFAULT NULL COMMENT '组件路径',
    `status`      tinyint      NOT NULL COMMENT '状态（0：正常 1：停用）',
    `visible`     bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否可见',
    `create_by`   varchar(64)  not null comment '创建人',
    `create_time` datetime     not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64)  NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                  bigint        not null auto_increment comment '角色 id',
    `name`                varchar(64)   not null comment '角色名称',
    `code`                varchar(64)   not null comment '角色权限编码',
    `type`                tinyint       NOT NULL COMMENT '角色类型',
    `sort`                int           NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `data_scope`          tinyint       NOT NULL DEFAULT 1 COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `data_scope_dept_ids` varchar(1024) NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
    `status`              tinyint       NOT NULL COMMENT '状态（0：正常 1：停用）',
    `remark`              varchar(500)  NULL     DEFAULT NULL COMMENT '备注',
    `create_by`           varchar(64)   not null comment '创建人',
    `create_time`         datetime      not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`           varchar(64)   NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time`         datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`             bit(1)        NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `role_id`     bigint      NOT NULL COMMENT '角色ID',
    `menu_id`     bigint      NOT NULL COMMENT '菜单ID',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by`   varchar(64) NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)      NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
create table sys_user
(
    id          bigint                             not null comment '用户ID'
        primary key,
    username    varchar(64)                        not null comment '用户名称',
    fullname    varchar(64)                        null comment '真实姓名',
    nickname    varchar(64)                        null comment '昵称',
    password    varchar(255)                       null comment '密码',
    salt        varchar(255)                       null comment '盐值',
    gender      tinyint  default 1                 null comment '用户性别（0：女；1：男）',
    phone       char(11)                           null comment '手机号码',
    email       varchar(255)                       null comment '邮箱',
    avatar      varchar(255)                       null comment '头像',
    dept_id     bigint                             not null comment '部门ID',
    birthday    date                               null comment ' 生日',
    roles       varchar(255)                       not null comment '角色',
    status      tinyint                            not null comment '状态（0：正常 1：冻结）',
    login_ip    varchar(15)                        null comment '最后登录IP',
    login_date  datetime                           null comment '最后登录时间',
    create_by   varchar(64)                        not null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   varchar(64)                        not null comment '修改人',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit      default b'0'              not null comment '逻辑删除（0：正常 1：删除）',
    constraint uk_phone
        unique (phone),
    constraint uk_username
        unique (username)
)
    comment '用户表';

# init data

INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('前端开发组', 1, 0, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('部署组', 4, 0, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('研发部', 0, 0, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('测试中心', 0, 2, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('运维中心', 0, 3, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('后端开发组', 1, 1, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('UI 设计组', 1, 2, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('工具开发组', 4, 1, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('经理室', 0, 4, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('人事部', 0, 5, 'admin', 'admin');
INSERT INTO zeus_upms.sys_dept (name, parent_id, weight, create_by, update_by) VALUES ('产品部', 0, 1, 'admin', 'admin');

INSERT INTO zeus_upms.sys_user (id, username, fullname, nickname, password, salt, gender, phone, email, avatar, dept_id, birthday, roles, status, login_ip, login_date, create_by, create_time, update_by, update_time, deleted) VALUES (1, 'admin', '张有志', '小张', null, null, 1, '18312345678', 'zeusadmin@gmail.com', null, 1, '2023-10-11', '1', 0, null, null, 'admin', '2023-10-11 00:52:50', 'admin', '2023-10-11 00:52:50', false);
