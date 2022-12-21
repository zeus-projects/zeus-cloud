USE zeus;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `dept_id` bigint NOT NULL,
                            `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
                            `order` int NOT NULL DEFAULT '0' COMMENT '排序',
                            `create_by` varchar(64) CHARACTER SET utf8  NOT NULL DEFAULT ' ' COMMENT '创建人',
                            `update_by` varchar(64) CHARACTER SET utf8  NOT NULL DEFAULT ' ' COMMENT '修改人',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                            `del_flag` char(1) CHARACTER SET utf8mb4 DEFAULT '0',
                            `parent_id` bigint DEFAULT NULL,
                            `tenant_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门管理';