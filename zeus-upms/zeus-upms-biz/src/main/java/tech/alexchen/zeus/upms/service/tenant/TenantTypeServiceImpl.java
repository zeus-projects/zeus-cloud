package tech.alexchen.zeus.upms.service.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantTypeDO;
import tech.alexchen.zeus.upms.mapper.tenant.TenantTypeMapper;

/**
* @author alexchen
*/
@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantTypeDO> implements TenantTypeService{

    @Override
    public Page<TenantTypeDO> page(Page page, TenantTypeRequestVO request) {
        return this.getBaseMapper().page(page, request);
    }
}




