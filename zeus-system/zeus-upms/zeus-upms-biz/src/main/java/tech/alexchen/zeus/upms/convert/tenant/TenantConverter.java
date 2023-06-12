package tech.alexchen.zeus.upms.convert.tenant;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantResponseVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantSaveVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.entity.tenant.TenantDO;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantConverter extends BaseConverter<TenantDO, TenantSaveVO, TenantUpdateVO, TenantResponseVO> {

    TenantConverter INSTANCE = Mappers.getMapper(TenantConverter.class);

}
