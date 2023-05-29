package tech.alexchen.zeus.upms.convert.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.alexchen.zeus.upms.controller.user.vo.UserResponseVO;
import tech.alexchen.zeus.upms.controller.user.vo.UserSaveVO;
import tech.alexchen.zeus.upms.controller.user.vo.UserUpdateVO;
import tech.alexchen.zeus.upms.convert.BaseConverter;
import tech.alexchen.zeus.upms.domain.user.UserDO;

/**
 * @author alexchen
 */
@Mapper
public interface UserConverter extends BaseConverter<UserDO, UserSaveVO, UserUpdateVO, UserResponseVO> {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

}