package tech.alexchen.zeus.upms.convert.dept;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.admin.dept.vo.DeptResponseVO;
import tech.alexchen.zeus.upms.controller.admin.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.admin.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.convert.Convert;
import tech.alexchen.zeus.upms.dal.dataobject.dept.DeptDO;

/**
 * @author alexchen
 */
@Mapper
public interface DeptConvert extends Convert<DeptDO, DeptSaveVO, DeptUpdateVO, DeptResponseVO> {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

}
