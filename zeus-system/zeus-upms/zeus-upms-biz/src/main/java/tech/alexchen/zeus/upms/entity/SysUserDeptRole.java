package tech.alexchen.zeus.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.alexchen.zeus.common.data.mybatis.pojo.BaseEntity;

import java.io.Serializable;

/**
 * 用户部门角色关联记录
 *
 * @author alexchen
 * @since 2025-01-26 16:23
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_dept_role", autoResultMap = true)
public class SysUserDeptRole extends BaseEntity implements Serializable {

    /**
     * 记录自增ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 角色ID
     */
    private Long roleId;
}
