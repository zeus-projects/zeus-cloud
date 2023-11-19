package tech.alexchen.zeus.upms.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.JdbcType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色
 *
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    /**
     * 角色 id
     */
    @NotNull(message = "[角色 id]不能为空")
    @Schema(name = "id",description = "角色 id")
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "[角色名称]不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @Length(max = 64, message = "编码长度不能超过64")
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色类型
     */
    @NotNull(message = "[角色类型]不能为空")
    @Schema(name = "角色类型")
    private Integer type;

    /**
     * 角色权限编码
     */
    @NotBlank(message = "[角色权限编码]不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @Length(max = 64, message = "编码长度不能超过64")
    @Schema
    private String permission;

    /**
     * 描述
     */
    @Size(max = 500, message = "编码长度不能超过500")
    @Schema(name = "描述")
    @Length(max = 500, message = "编码长度不能超过500")
    private String description;

    /**
     * 数据权限范围（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）
     */
    @NotNull(message = "[数据权限范围（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）]不能为空")
    @Schema(name = "数据权限范围（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）")
    private Integer dataScopeType;

    /**
     * 数据范围(指定部门数组)
     */
    @Size(max = 1024, message = "编码长度不能超过1024")
    @Schema(name = "数据范围(指定部门数组)")
    @Length(max = 1024, message = "编码长度不能超过1,024")
    private String dataScope;


    @Schema(name = "菜单权限")
    private String menus;

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
