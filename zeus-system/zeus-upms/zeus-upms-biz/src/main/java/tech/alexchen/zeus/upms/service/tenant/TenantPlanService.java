package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanRequestVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanSaveVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantPlanDO;

/**
* @author alexchen
*/
public interface TenantPlanService extends IService<TenantPlanDO> {

    /**
     * 添加租户套餐
     * @param entity 租户套餐
     * @return 租户套餐 id
     */
    Long saveTenantPlan(TenantPlanSaveVO entity);

    /**
     * 分页查询
     * @param page
     * @param pageVO
     * @return
     */
    Page<TenantPlanDO> page(Page page, TenantPlanRequestVO pageVO);

}
