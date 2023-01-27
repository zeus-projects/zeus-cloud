package tech.alexchen.zeus.upms.domain.permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import tech.alexchen.zeus.starter.data.mybatis.entity.BaseDO;

/**
 * 角色
 *
 * @author alexchen
 * @TableName sys_role
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_role")
public class RoleDO extends BaseDO implements Serializable {

    /**
    * 角色 id
    */
    @NotNull(message="[角色 id]不能为空")
    @ApiModelProperty("角色 id")
    private Long id;

    /**
    * 角色名称
    */
    @NotBlank(message="[角色名称]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("角色名称")
    @Length(max= 64,message="编码长度不能超过64")
    private String name;

    /**
    * 角色权限编码
    */
    @NotBlank(message="[角色权限编码]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("角色权限编码")
    @Length(max= 64,message="编码长度不能超过64")
    private String code;

    /**
    * 角色类型
    */
    @NotNull(message="[角色类型]不能为空")
    @ApiModelProperty("角色类型")
    private Integer type;

    /**
    * 显示顺序
    */
    @NotNull(message="[显示顺序]不能为空")
    @ApiModelProperty("显示顺序")
    private Integer sort;

    /**
    * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
    */
    @NotNull(message="[数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）]不能为空")
    @ApiModelProperty("数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private Integer dataScope;

    /**
    * 数据范围(指定部门数组)
    */
    @NotBlank(message="[数据范围(指定部门数组)]不能为空")
    @Size(max= 1024,message="编码长度不能超过1024")
    @ApiModelProperty("数据范围(指定部门数组)")
    @Length(max= 1024,message="编码长度不能超过1,024")
    private String dataScopeDeptIds;

    /**
    * 状态（0：正常 1：停用）
    */
    @NotNull(message="[状态（0：正常 1：停用）]不能为空")
    @ApiModelProperty("状态（0：正常 1：停用）")
    private Integer status;

    /**
    * 备注
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("备注")
    @Length(max= 500,message="编码长度不能超过500")
    private String remark;

}
