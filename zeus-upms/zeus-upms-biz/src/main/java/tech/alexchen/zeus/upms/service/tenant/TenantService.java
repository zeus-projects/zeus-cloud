package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantRequestVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantDO;

/**
 * @author alexchen
 */
public interface TenantService extends IService<TenantDO> {


    Page<TenantDO> page(Page page, TenantRequestVO param);
}
