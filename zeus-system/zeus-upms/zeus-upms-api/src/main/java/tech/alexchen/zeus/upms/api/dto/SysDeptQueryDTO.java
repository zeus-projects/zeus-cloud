package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptQueryDTO {

    /**
     * 部门 ID
     */
    @Schema(description = "部门 ID")
    private Long id;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String name;
}
