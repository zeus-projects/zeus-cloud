package tech.alexchen.zeus.upms.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysUserConverter;
import tech.alexchen.zeus.upms.entity.SysUser;
import tech.alexchen.zeus.upms.mapper.SysUserMapper;
import tech.alexchen.zeus.upms.service.SysRoleService;
import tech.alexchen.zeus.upms.service.SysUserService;

import java.util.Set;

/**
 * @author alexchen
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper mapper;
    private final SysUserConverter converter;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SysRoleService roleService;

    @Override
    public Long saveUser(@Valid SysUserSaveDTO dto) {
        checkDuplicateUserInfo(dto.getUsername(), dto.getPhone());
        SysUser entity = converter.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        mapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void updateUser(SysUserUpdateDTO dto) {
        SysUser entity = converter.toEntity(dto);
        mapper.updateById(entity);
    }

    @Override
    public void removeUserById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public SysUser getUserById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public SysUser getUserByName(String username) {
        return mapper.selectByUsername(username);
    }

    @Override
    public SysUserAuthDTO getUserAuthInfo(String username) {
        SysUser user = getUserByName(username);
        if (user == null) {
            return null;
        }

        Set<Long> roles = user.getRoles();
        Set<String> permissions = roleService.getRolePermissions(roles);

        SysUserAuthDTO dto = new SysUserAuthDTO();
        BeanUtil.copyProperties(user, dto);
        dto.setPermissions(permissions);
        return dto;
    }

    @Override
    public PageResult<SysUser> getUserPage(PageParam param) {
        return mapper.selectPage(param, null);
    }

    private void checkDuplicateUserInfo(String username, String phone) {
        SysUser userWithUsername = mapper.selectOne(SysUser::getUsername, username);
        Assert.isNull(userWithUsername, "User with username {} is exist", username);

        SysUser userWithPhone = mapper.selectOne(SysUser::getPhone, phone);
        Assert.isNull(userWithPhone, "User with phone {} is exist", phone);
    }

}
