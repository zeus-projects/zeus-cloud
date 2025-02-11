package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author alexchen
 */
@Data
@Schema
public class SysUserUpdateDTO implements Serializable {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Long id;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String fullname;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @Schema(description = "性别")
    private Integer gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 状态（0：正常 1：冻结）
     */
    @Schema(description = "状态（0：正常 1：冻结）", defaultValue = "0")
    private Integer status;

}
