package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantTypeSaveVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantTypeDO;

/**
* @author alexchen
*/
public interface TenantTypeService extends IService<TenantTypeDO> {

    /**
     * 添加租户类型
     * @param entity 租户类型
     * @return 租户类型 id
     */
    Long saveTenantType(TenantTypeSaveVO entity);
    Page<TenantTypeDO> page(Page page, TenantTypeRequestVO pageVO);

}
