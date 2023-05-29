package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanRequestVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanSaveVO;
import tech.alexchen.zeus.upms.convert.tenant.TenantPlanConverter;
import tech.alexchen.zeus.upms.domain.tenant.TenantPlanDO;
import tech.alexchen.zeus.upms.mapper.tenant.TenantPlanMapper;

import javax.annotation.Resource;

/**
* @author alexchen
*/
@Service
public class TenantPlanServiceImpl extends ServiceImpl<TenantPlanMapper, TenantPlanDO> implements TenantPlanService{

    @Resource
    TenantPlanConverter converter;
    @Override
    @CacheEvict(value = "upms:tenant-type", allEntries = true)
    public Long saveTenantPlan(TenantPlanSaveVO vo) {
        TenantPlanDO tenantType = converter.convertFromSave(vo);
        this.save(tenantType);
        return tenantType.getId();
    }

    @Override
    public Page<TenantPlanDO> page(Page page, TenantPlanRequestVO request) {
        return this.page(page, request);
    }

}




