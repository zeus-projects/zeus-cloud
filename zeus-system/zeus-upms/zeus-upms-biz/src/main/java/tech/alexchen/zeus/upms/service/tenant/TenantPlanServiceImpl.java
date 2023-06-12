package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanRequestVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanSaveVO;
import tech.alexchen.zeus.upms.convert.tenant.TenantPlanConverter;
import tech.alexchen.zeus.upms.entity.tenant.TenantPlanDO;
import tech.alexchen.zeus.upms.mapper.tenant.TenantPlanMapper;

/**
* @author alexchen
*/
@Service
@RequiredArgsConstructor
public class TenantPlanServiceImpl extends ServiceImpl<TenantPlanMapper, TenantPlanDO> implements TenantPlanService{

    private final TenantPlanConverter converter;

    @Override
    @CacheEvict(value = "upms:tenant-plan", allEntries = true)
    public Long saveTenantPlan(TenantPlanSaveVO vo) {
        TenantPlanDO tenantPlan = converter.convertFromSave(vo);
        this.save(tenantPlan);
        return tenantPlan.getId();
    }

    @Override
    public Page<TenantPlanDO> page(Page page, TenantPlanRequestVO request) {
        return this.page(page, request);
    }

}




