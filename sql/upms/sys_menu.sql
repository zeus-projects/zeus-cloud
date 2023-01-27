USE zeus;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`   bigint not null  auto_increment comment '菜单id',
    `name` varchar(64) not null comment '菜单名称',
    `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `type` tinyint NOT NULL COMMENT '菜单类型',
    `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `permission` varchar(100) NOT NULL DEFAULT '' COMMENT '权限标识',
    `path` varchar(200) NULL DEFAULT '' COMMENT '路由地址',
    `icon` varchar(100) NULL DEFAULT '#' COMMENT '菜单图标',
    `component` varchar(255) NULL DEFAULT NULL COMMENT '组件路径',
    `status` tinyint NOT NULL COMMENT  '状态（0：正常 1：停用）',
    `visible` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可见',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by` varchar(64)  NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单'

