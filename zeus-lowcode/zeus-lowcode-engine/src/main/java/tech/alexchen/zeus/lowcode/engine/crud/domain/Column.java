package tech.alexchen.zeus.lowcode.engine.crud.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tech.alexchen.zeus.starter.data.mybatis.entity.TenantBaseDO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class Column extends TenantBaseDO implements Serializable {

    /**
     * 属性唯一编码
     */
    @NotBlank(message = "[属性唯一编码]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("属性唯一编码")
    @Length(max = 255, message = "编码长度不能超过255")
    private String id;
    /**
     * 属性名称
     */
    @NotBlank(message = "[属性名称]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("属性名称")
    @Length(max = 255, message = "编码长度不能超过255")
    private String name;
    /**
     * 注释
     */
    @NotBlank(message = "[注释]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("注释")
    @Length(max = 255, message = "编码长度不能超过255")
    private String comment;
    /**
     * 数据类型
     */
    @NotBlank(message = "[数据类型]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("数据类型")
    @Length(max = 255, message = "编码长度不能超过255")
    private String dataType;
    /**
     * 是否非空
     */
    @NotNull(message = "[是否非空]不能为空")
    @ApiModelProperty("是否非空")
    private Integer notNull;
    /**
     * 默认值表达式
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("默认值表达式")
    @Length(max = 255, message = "编码长度不能超过255")
    private String defaultExpression;
    /**
     * 自动递增
     */
    @ApiModelProperty("自动递增")
    private Integer autoIncrement;
}
