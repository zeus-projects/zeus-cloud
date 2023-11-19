package tech.alexchen.zeus.upms.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.JdbcType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门 数据库实体
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_dept")
public class SysDept implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 部门 ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(name = "id", description = "部门 id（更新时必需）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    @NotNull(message = "更新部门时，id 不能为空")
    private Long id;

    /**
     * 部门名称
     */
    @Schema(name = "name", description = "部门名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "平台部")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    /**
     * 上级部门 ID
     */
    @Schema(name = "parentId", description = "上级部门 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "上级部门 id 不能为空")
    @Min(value = 0, message = "上级部门 id 不能小于 0")
    private Long parentId;

    /**
     * 排序
     */
    @Schema(name = "weight", description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "排序不能为空")
    private Integer weight;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Schema(name = "updateBy", description = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.VARCHAR)
    private String updateBy;

    /**
     * 最后更新时间
     */
    @Schema(name = "updateTime", description = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @Hidden
    @TableLogic
    private Integer deleted;
}
