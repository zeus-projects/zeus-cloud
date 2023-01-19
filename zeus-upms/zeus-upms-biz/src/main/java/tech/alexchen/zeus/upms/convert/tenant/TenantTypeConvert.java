package tech.alexchen.zeus.upms.convert.tenant;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeResponseVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeSaveVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeUpdateVO;
import tech.alexchen.zeus.upms.convert.Convert;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantTypeDO;

/**
 * @author alexchen
 */
@Mapper
public interface TenantTypeConvert extends Convert<TenantTypeDO, TenantTypeSaveVO, TenantTypeUpdateVO, TenantTypeResponseVO> {

    TenantTypeConvert INSTANCE = Mappers.getMapper(TenantTypeConvert.class);
//
//    TenantTypeDO convert(TenantTypeSaveVO vo);
//
//    TenantTypeDO convert(TenantTypeUpdateVO vo);
//
//    TenantTypeResponseVO convert(TenantTypeDO tenantType);
//
//    Page<TenantTypeResponseVO> convertPage(Page<TenantTypeDO> page);
//
//    List<TenantTypeResponseVO> convertList(List<TenantTypeDO> list);
}
