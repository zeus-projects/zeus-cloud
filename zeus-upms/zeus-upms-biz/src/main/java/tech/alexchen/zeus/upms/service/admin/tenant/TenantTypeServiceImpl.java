package tech.alexchen.zeus.upms.service.admin.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantTypeDO;
import tech.alexchen.zeus.upms.dal.mapper.tenant.TenantTypeMapper;

import javax.annotation.Resource;

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




