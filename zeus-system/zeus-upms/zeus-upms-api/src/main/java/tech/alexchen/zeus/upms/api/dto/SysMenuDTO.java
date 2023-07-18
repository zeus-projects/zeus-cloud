package tech.alexchen.zeus.upms.api.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author alexchen
 */
public class SysMenuDTO {

    @ApiModelProperty("菜单id")
    private Long id;
    @NotBlank(message="[菜单名称]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("菜单名称")
    @Length(max= 64,message="编码长度不能超过64")
    private String name;

    @NotNull(message="[父菜单ID]不能为空")
    @ApiModelProperty("父菜单ID")
    private Long parentId;

    @NotNull(message="[菜单类型]不能为空")
    @ApiModelProperty("菜单类型")
    private Integer type;

    @NotNull(message="[显示顺序]不能为空")
    @ApiModelProperty("显示顺序")
    private Integer sort;

    @NotBlank(message="[权限标识]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("权限标识")
    @Length(max= 100,message="编码长度不能超过100")
    private String permission;

    @Size(max= 200,message="编码长度不能超过200")
    @ApiModelProperty("路由地址")
    @Length(max= 200,message="编码长度不能超过200")
    private String path;

    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("菜单图标")
    @Length(max= 100,message="编码长度不能超过100")
    private String icon;

    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("组件路径")
    @Length(max= 255,message="编码长度不能超过255")
    private String component;

    @NotNull(message="[状态（0：正常 1：停用）]不能为空")
    @ApiModelProperty("状态（0：正常 1：停用）")
    private Integer status;

    @NotNull(message="[是否可见]不能为空")
    @ApiModelProperty("是否可见")
    private Boolean visible;
}
