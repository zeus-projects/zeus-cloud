package tech.alexchen.zeus.upms.controller.admin.tenant.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tech.alexchen.zeus.upms.dal.entity.tenant.TenantTypeDO;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantTypePageVO extends TenantTypeBaseVO implements Serializable {

}
