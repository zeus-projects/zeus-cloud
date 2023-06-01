package tech.alexchen.zeus.common.data.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 数据库实体的通用属性
 *
 * @author alexchen
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantBaseDO extends BaseDO implements Serializable {

    /**
     * 租户 id
     */
    private Long tenantId;

}
