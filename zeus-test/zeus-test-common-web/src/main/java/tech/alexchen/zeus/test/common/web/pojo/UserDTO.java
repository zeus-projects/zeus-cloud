package tech.alexchen.zeus.test.common.web.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Data
public class UserDTO {

    private Long id;

    private String name;

    private LocalDate birthday;

    private LocalDateTime createTime;

    private Role role;
}
