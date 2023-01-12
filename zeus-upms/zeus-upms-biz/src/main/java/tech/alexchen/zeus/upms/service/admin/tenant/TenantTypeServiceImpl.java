package tech.alexchen.zeus.upms.service.admin.tenant;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.dal.entity.tenant.TenantTypeDO;
import tech.alexchen.zeus.upms.dal.mapper.tenant.TenantTypeMapper;

import javax.annotation.Resource;

/**
* @author alexchen
*/
@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantTypeDO> implements TenantTypeService{

    @Resource
    private TenantTypeMapper tenantTypeMapper;

    @Override
    public Page<TenantTypeDO> page(Page page, TenantTypeRequestVO request) {
        return tenantTypeMapper.page(page, request);
    }
}




