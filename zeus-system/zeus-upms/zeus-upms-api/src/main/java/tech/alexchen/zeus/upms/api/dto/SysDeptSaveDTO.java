package tech.alexchen.zeus.upms.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptSaveDTO implements Serializable {

    /**
     * 部门名称
     */
    @Schema(description = "部门名称", example = "平台部")
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 255, message = "长度不能超过255")
    private String name;

    /**
     * 上级部门 ID
     */
    @Schema(description = "上级部门 ID", example = "0")
    @NotNull(message = "上级部门 id 不能为空")
    @Min(value = 0, message = "上级部门 id 不能小于 0")
    private Long parentId;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    @NotNull(message = "排序不能为空")
    private Integer sort;
}
