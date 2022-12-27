package tech.alexchen.zeus.upms.dal.entity.tenant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import tech.alexchen.zeus.starter.mybatis.entity.BaseDO;
import tech.alexchen.zeus.starter.mybatis.typehandler.JsonLongSetTypeHandler;

import java.io.Serializable;
import java.util.Set;

/**
 * 租户类型
 * @TableName sys_tenant_type
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_tenant_type")
public class TenantTypeDO extends BaseDO implements Serializable {
    /**
     * 租户类型id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户类型名称
     */
    private String name;

    /**
     * 租户类型状态
     */
    private Integer status;

    /**
     * 关联的菜单编码
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> menuIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}