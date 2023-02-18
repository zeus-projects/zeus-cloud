package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantTypeDO;

/**
* @author alexchen
*/
public interface TenantTypeService extends IService<TenantTypeDO> {

    Page<TenantTypeDO> page(Page page, TenantTypeRequestVO pageVO);

}
