package tech.alexchen.zeus.upms.convert.tenant;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantResponseVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantSaveVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConvert;
import tech.alexchen.zeus.upms.domain.tenant.TenantDO;

/**
 * @author alexchen
 */
@Mapper
public interface TenantConvert extends BaseConvert<TenantDO, TenantSaveVO, TenantUpdateVO, TenantResponseVO> {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

}
