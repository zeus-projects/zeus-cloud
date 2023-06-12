package tech.alexchen.zeus.upms.entity.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import tech.alexchen.zeus.common.core.enums.CommonStatusEnum;
import tech.alexchen.zeus.common.data.mybatis.entity.BaseDO;

import java.io.Serializable;

/**
 * 部门 数据库实体
 * @author alexchen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dept")
public class DeptDO extends BaseDO implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 部门 ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门 ID
     */
    private Long parentId;

    /**
     * 部门等级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 租户 ID
     */
    private Long tenantId;

}
