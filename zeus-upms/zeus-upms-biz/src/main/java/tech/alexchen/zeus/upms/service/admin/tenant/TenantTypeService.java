package tech.alexchen.zeus.upms.service.admin.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantTypeDO;

/**
* @author alexchen
*/
public interface TenantTypeService extends IService<TenantTypeDO> {

    Page<TenantTypeDO> page(Page page, TenantTypeRequestVO pageVO);

}