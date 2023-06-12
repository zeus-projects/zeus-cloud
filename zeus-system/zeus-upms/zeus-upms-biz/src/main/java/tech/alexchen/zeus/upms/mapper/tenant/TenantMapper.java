package tech.alexchen.zeus.upms.mapper.tenant;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantRequestVO;
import tech.alexchen.zeus.upms.entity.tenant.TenantDO;

/**
* @author alexchen
*/
public interface TenantMapper extends BaseMapper<TenantDO> {

    /**
     * 分页条件查询部门
     * @param page 分页参数
     * @param param 过滤条件
     * @return 部门分页数据
     */
    default Page<TenantDO> page(Page page, TenantRequestVO param) {
        return this.selectPage(page, Wrappers.<TenantDO>lambdaQuery()
                .like(StrUtil.isNotBlank(param.getName()), TenantDO::getName, param.getName())
                .eq(param.getStatus() != null, TenantDO::getStatus, param.getStatus())
                .eq(param.getTenantPlanId() != null, TenantDO::getTenantPlanId, param.getTenantPlanId())
        );
    }
}




