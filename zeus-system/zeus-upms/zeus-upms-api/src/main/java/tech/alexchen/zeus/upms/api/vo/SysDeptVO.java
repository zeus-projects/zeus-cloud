package tech.alexchen.zeus.upms.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptVO {

    /**
     * 部门 ID
     */
    @Schema(description = "部门 id")
    private Long id;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String name;

    /**
     * 上级部门 ID
     */
    @Schema(description = "上级部门 ID")
    private Long parentId;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer weight;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
