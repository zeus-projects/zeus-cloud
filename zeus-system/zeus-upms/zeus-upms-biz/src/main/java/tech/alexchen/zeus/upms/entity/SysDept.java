package tech.alexchen.zeus.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.alexchen.zeus.common.data.mybatis.pojo.BaseEntity;

import java.io.Serializable;

/**
 * 部门 数据库实体
 *
 * @author alexchen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_dept")
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseEntity implements Serializable {

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
     * 排序
     */
    private Integer sort;

}
