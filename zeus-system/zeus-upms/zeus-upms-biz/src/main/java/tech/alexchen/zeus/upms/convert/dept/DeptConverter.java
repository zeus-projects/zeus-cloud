package tech.alexchen.zeus.upms.convert.dept;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptResponseVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.domain.dept.DeptDO;

/**
 * @author alexchen
 */
@Mapper(componentModel = "spring")
public interface DeptConverter extends BaseConverter<DeptDO, DeptSaveVO, DeptUpdateVO, DeptResponseVO> {

    DeptConverter INSTANCE = Mappers.getMapper(DeptConverter.class);

}
