package tech.alexchen.zeus.upms.controller.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserResponseVO extends UserBaseVO {

    /**
     * 用户ID
     */
    private Long id;
}
