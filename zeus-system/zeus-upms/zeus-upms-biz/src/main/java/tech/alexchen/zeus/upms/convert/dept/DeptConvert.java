package tech.alexchen.zeus.upms.convert.dept;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptResponseVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConvert;
import tech.alexchen.zeus.upms.domain.dept.DeptDO;

/**
 * @author alexchen
 */
@Mapper
public interface DeptConvert extends BaseConvert<DeptDO, DeptSaveVO, DeptUpdateVO, DeptResponseVO> {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

}
