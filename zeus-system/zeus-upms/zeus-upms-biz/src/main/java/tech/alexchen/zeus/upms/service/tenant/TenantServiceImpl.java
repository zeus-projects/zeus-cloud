package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantRequestVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantDO;
import tech.alexchen.zeus.upms.mapper.tenant.TenantMapper;

/**
 * @author alexchen
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, TenantDO> implements TenantService {

    @Override
    public Page<TenantDO> page(Page page, TenantRequestVO param) {
        return this.getBaseMapper().page(page, param);
    }
}
