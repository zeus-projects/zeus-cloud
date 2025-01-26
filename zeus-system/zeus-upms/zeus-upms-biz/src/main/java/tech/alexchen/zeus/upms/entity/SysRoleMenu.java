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
 * 角色菜单关联记录
 *
 * @author alexchen
 * @since 2025-01-26 16:23
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_menu", autoResultMap = true)
public class SysRoleMenu extends BaseEntity implements Serializable {

    /**
     * 记录自增ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
