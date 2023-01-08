package tech.alexchen.zeus.upms.service.admin.tenant;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypePageVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeResponseVO;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantTypeDO;

/**
* @author alexchen
*/
public interface TenantTypeService extends IService<TenantTypeDO> {

    Page<TenantTypeResponseVO> page(Page page, TenantTypePageVO pageVO);

}
