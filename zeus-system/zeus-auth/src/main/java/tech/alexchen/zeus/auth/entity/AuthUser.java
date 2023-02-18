package tech.alexchen.zeus.auth.entity;

import lombok.Data;

import java.util.List;

/**
 * @author alexchen
 * @date 2023/2/15
 */
@Data
public class AuthUser {

    private String username;

    private String password;

    private List<String> roles;
}
