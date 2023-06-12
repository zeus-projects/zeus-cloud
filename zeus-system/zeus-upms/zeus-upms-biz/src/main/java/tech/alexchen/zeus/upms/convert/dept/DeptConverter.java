package tech.alexchen.zeus.upms.convert.dept;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptResponseVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.entity.dept.DeptDO;

/**
 * @author alexchen
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeptConverter extends BaseConverter<DeptDO, DeptSaveVO, DeptUpdateVO, DeptResponseVO> {

}
