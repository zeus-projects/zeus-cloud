package tech.alexchen.zeus.upms.convert.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeResponseVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeSaveVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeUpdateVO;
import tech.alexchen.zeus.upms.dal.entity.tenant.TenantTypeDO;

import java.util.List;

/**
 * @author alexchen
 */
@Mapper
public interface TenantTypeConvert {

    TenantTypeConvert INSTANCE = Mappers.getMapper(TenantTypeConvert.class);

    TenantTypeDO convert(TenantTypeSaveVO vo);

    TenantTypeDO convert(TenantTypeUpdateVO vo);

    TenantTypeResponseVO convert(TenantTypeDO tenantType);

    Page<TenantTypeResponseVO> convertPage(Page<TenantTypeDO> page);

    List<TenantTypeResponseVO> convertList(List<TenantTypeDO> list);
}
