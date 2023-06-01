package tech.alexchen.zeus.upms.domain.tenant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.*;
import tech.alexchen.zeus.starter.enums.CommonStatusEnum;
import tech.alexchen.zeus.starter.data.mybatis.entity.BaseDO;

/**
 * 租户
 * @author alexchen
 * @TableName sys_tenant
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_tenant")
public class TenantDO extends BaseDO implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户名称
     */
    private String name;

    /**
     * 租户套餐id
     */
    private Integer tenantPlanId;

    /**
     * 租户套餐名称
     */
    private String tenantPlanName;

    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}