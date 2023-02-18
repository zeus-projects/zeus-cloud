package tech.alexchen.zeus.auth.service;

import tech.alexchen.zeus.auth.entity.AuthUser;

/**
 * @author alexchen
 * @date 2023/2/15
 */
public interface UserService {

    AuthUser getUserInfoByUsername(String username);
}
