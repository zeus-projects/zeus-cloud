package tech.alexchen.zeus.upms.controller.dept.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author alexchen
 */
@ApiModel("系统管理 - 部门 - ResponseVO")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeptResponseVO extends DeptBaseVO {

    @ApiModelProperty(value = "部门 ID", required = true, example = "1024")
    private Long id;
}
