package tech.alexchen.zeus.upms.mapper.tenant;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanRequestVO;
import tech.alexchen.zeus.upms.entity.tenant.TenantPlanDO;

/**
* @author alexchen
*/
public interface TenantPlanMapper extends BaseMapper<TenantPlanDO> {

    default Page<TenantPlanDO> page(Page page, TenantPlanRequestVO request) {
        return this.selectPage(page, Wrappers.<TenantPlanDO>lambdaQuery()
                .like(StrUtil.isNotBlank(request.getName()), TenantPlanDO::getName, request.getName())
                .eq(request.getStatus() != null, TenantPlanDO::getStatus, request.getStatus()));
    }
}




