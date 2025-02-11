package tech.alexchen.zeus.upms.api.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门角色关联对象
 *
 * @author alexchen
 * @since 2025-02-07 09:05
 */
@Data
@Schema
public class SysDeptRoleBO {

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "角色ID")
    private Long roleId;

}
