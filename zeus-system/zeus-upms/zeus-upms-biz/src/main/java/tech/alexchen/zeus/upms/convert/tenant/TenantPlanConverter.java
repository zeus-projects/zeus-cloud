package tech.alexchen.zeus.upms.convert.tenant;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanResponseVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanSaveVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.domain.tenant.TenantPlanDO;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantPlanConverter extends BaseConverter<TenantPlanDO, TenantPlanSaveVO, TenantPlanUpdateVO, TenantPlanResponseVO> {

    TenantPlanConverter INSTANCE = Mappers.getMapper(TenantPlanConverter.class);
}
