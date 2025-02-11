package tech.alexchen.zeus.upms.service.impl;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.alexchen.zeus.common.core.exception.ResponsiveRuntimeException;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;
import tech.alexchen.zeus.common.security.core.SecurityUtil;
import tech.alexchen.zeus.upms.api.bo.SysDeptRoleBO;
import tech.alexchen.zeus.upms.api.constant.UpmsResponseCode;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserSaveDTO;
import tech.alexchen.zeus.upms.api.dto.SysUserUpdateDTO;
import tech.alexchen.zeus.upms.convert.SysUserConverter;
import tech.alexchen.zeus.upms.entity.SysUser;
import tech.alexchen.zeus.upms.entity.SysUserDeptRole;
import tech.alexchen.zeus.upms.mapper.SysUserDeptRoleMapper;
import tech.alexchen.zeus.upms.mapper.SysUserMapper;
import tech.alexchen.zeus.upms.service.SysRoleService;
import tech.alexchen.zeus.upms.service.SysUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alexchen
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysUserDeptRoleMapper sysUserDeptRoleMapper;
    private final SysUserConverter converter;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SysRoleService sysRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveUser(@Valid SysUserSaveDTO dto) {
        if (sysUserMapper.isUsernameExists(dto.getUsername())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_USERNAME_DUPLICATE);
        }
        if (sysUserMapper.isUserPhoneExists(dto.getPhone())) {
            throw new ResponsiveRuntimeException(UpmsResponseCode.SYS_USER_PHONE_DUPLICATE);
        }
        // 保存用户信息
        SysUser entity = converter.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        sysUserMapper.insert(entity);

        // 插入用户部门角色信息
        List<SysUserDeptRole> userDeptRoleList = dto.getDeptRoles().stream().map(i -> {
            SysUserDeptRole userDeptRole = new SysUserDeptRole();
            userDeptRole.setUserId(entity.getId());
            userDeptRole.setDeptId(i.getDeptId());
            userDeptRole.setRoleId(i.getRoleId());
            return userDeptRole;
        }).collect(Collectors.toList());
        sysUserDeptRoleMapper.insert(userDeptRoleList);
        return entity.getId();
    }

    @Override
    public void updateUser(SysUserUpdateDTO dto) {
        SysUser entity = converter.toEntity(dto);
        sysUserMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUserById(Long id) {
        sysUserMapper.deleteById(id);
        sysUserDeptRoleMapper.deleteByUserId(id);
    }

    @Override
    public SysUser getUserById(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUserAuthDTO getUserAuthInfo(String username) {
        SysUser user = sysUserMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }
        SysUserAuthDTO authDTO = converter.toAuthDTO(user);

        // 补充部门角色信息
        List<SysUserDeptRole> sysUserDeptRoles = sysUserDeptRoleMapper.selectListByUserId(user.getId());
        Set<SysDeptRoleBO> deptRoleBOS = sysUserDeptRoles.stream().map(i -> {
            SysDeptRoleBO deptRoleBO = new SysDeptRoleBO();
            deptRoleBO.setDeptId(i.getDeptId());
            deptRoleBO.setRoleId(i.getRoleId());
            return deptRoleBO;
        }).collect(Collectors.toSet());
        authDTO.setDeptRoles(deptRoleBOS);

        // 补充权限信息
        Set<Long> roleIds = sysUserDeptRoles.stream().map(SysUserDeptRole::getRoleId).collect(Collectors.toSet());
        Set<String> permissions = sysRoleService.getRolePermissions(roleIds);
        authDTO.setPermissions(permissions);
        return authDTO;
    }

    @Override
    public PageResult<SysUser> getUserPage(PageParam param) {
        return sysUserMapper.selectPage(param, null);
    }

    @Override
    public SysUserAuthDTO getCurrentUserInfo() {
        return this.getUserAuthInfo(SecurityUtil.getUsername());
    }

    @Override
    public void updateLastLoginInfo(Long userId, String clientIp) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setLastLoginIp(clientIp);
        user.setLastLoginDatetime(LocalDateTime.now());
        sysUserMapper.updateById(user);
    }

}
