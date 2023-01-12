package tech.alexchen.zeus.upms.dal.dataobject.tenant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import tech.alexchen.zeus.starter.mybatis.entity.BaseDO;

/**
 * 租户
 * @TableName sys_tenant
 */
@TableName(value ="sys_tenant")
@Data
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
     * 租户类型id
     */
    private Integer tenantTypeId;

    /**
     * 租户类型名称
     */
    private String tenantTypeName;

    /**
     * 状态
     */
    private String status;

}