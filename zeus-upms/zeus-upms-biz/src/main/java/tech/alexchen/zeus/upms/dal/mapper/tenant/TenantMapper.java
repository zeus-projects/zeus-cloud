package tech.alexchen.zeus.upms.dal.mapper.tenant;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantRequestVO;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantDO;

/**
* @author alexchen
*/
public interface TenantMapper extends BaseMapper<TenantDO> {

    default Page<TenantDO> page(Page page, TenantRequestVO param) {
        return this.selectPage(page, Wrappers.<TenantDO>lambdaQuery()
                .like(StrUtil.isNotBlank(param.getName()), TenantDO::getName, param.getName())
                .eq(StrUtil.isNotBlank(param.getStatus()), TenantDO::getStatus, param.getStatus())
                .eq(param.getTenantTypeId() != null, TenantDO::getTenantTypeId, param.getTenantTypeId())
        );
    }
}




