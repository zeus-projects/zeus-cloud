package tech.alexchen.zeus.common.data.mybatis.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class BaseTenantEntity extends BaseEntity implements Serializable {

    /**
     * 租户 id
     */
    @Schema(description = "租户 id")
    private Long tenantId;

}
