package tech.alexchen.zeus.lowcode.engine.crud.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tech.alexchen.zeus.common.data.mybatis.entity.TenantBaseDO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class Model extends TenantBaseDO implements Serializable {

    /**
     * 模型唯一编码
     */
    @NotBlank(message="[模型唯一编码]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("模型唯一编码")
    @Length(max= 64,message="编码长度不能超过64")
    private String id;

    /**
     * 模型名称
     */
    @NotBlank(message="[模型名称]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("模型名称")
    @Length(max= 64,message="编码长度不能超过64")
    private String name;

    /**
     * 0：基础模型；1：关联模型
     */
    @NotNull(message="[0：基础模型；1：关联模型]不能为空")
    @ApiModelProperty("0：基础模型；1：关联模型")
    private Integer type;

    /**
     * 业务分类 id
     */
    @NotBlank(message="[业务分类 id]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("业务分类 id")
    @Length(max= 64,message="编码长度不能超过64")
    private String categoryId;

    /**
     * 业务分类名称
     */
    @NotBlank(message="[业务分类名称]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("业务分类名称")
    @Length(max= 64,message="编码长度不能超过64")
    private String categoryName;

}
