package tech.alexchen.zeus.upms.service.admin.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantRequestVO;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantDO;
import tech.alexchen.zeus.upms.dal.mapper.tenant.TenantMapper;

import javax.annotation.Resource;

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
