USE zeus;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id` bigint NOT NULL,
    `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
    `parent_id` bigint DEFAULT NULL,
    `level` int NOT NUlL COMMENT '部门等级；最高级部门为 1，依次递增',
    `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
    `status` tinyint NOT NULL COMMENT '部门状态（0正常 1停用）',
    `create_by`   varchar(64) not null comment '创建人',
    `create_time` datetime    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    `update_by` varchar(64) CHARACTER SET utf8  NOT NULL DEFAULT ' ' COMMENT '修改人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除（0：正常 1：删除）',
    `tenant_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门管理';