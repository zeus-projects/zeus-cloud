package tech.alexchen.zeus.upms.mapper.tenant;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.domain.tenant.TenantTypeDO;

/**
* @author alexchen
*/
public interface TenantTypeMapper extends BaseMapper<TenantTypeDO> {

    default Page<TenantTypeDO> page(Page page, TenantTypeRequestVO request) {
        return this.selectPage(page, Wrappers.<TenantTypeDO>lambdaQuery()
                .like(StrUtil.isNotBlank(request.getName()), TenantTypeDO::getName, request.getName())
                .eq(request.getStatus() != null, TenantTypeDO::getStatus, request.getStatus()));
    }
}




