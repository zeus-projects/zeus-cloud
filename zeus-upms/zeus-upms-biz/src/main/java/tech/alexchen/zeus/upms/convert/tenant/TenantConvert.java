package tech.alexchen.zeus.upms.convert.tenant;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantResponseVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantSaveVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantUpdateVO;
import tech.alexchen.zeus.upms.convert.Convert;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantDO;

/**
 * @author alexchen
 */
@Mapper
public interface TenantConvert extends Convert<TenantDO, TenantSaveVO, TenantUpdateVO, TenantResponseVO> {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

}
