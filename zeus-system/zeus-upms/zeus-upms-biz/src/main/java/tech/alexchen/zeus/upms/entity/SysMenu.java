package tech.alexchen.zeus.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.alexchen.zeus.common.data.mybatis.pojo.BaseEntity;

import java.io.Serializable;

/**
 * @author alexchen
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity implements Serializable {

    /**
     * 菜单id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单类型（0:目录；1:菜单；2:按钮；3:外链）
     */
    private Integer type;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否隐藏（0:显示；1:隐藏）
     */
    private Integer hide;

    /**
     * 排序权重
     */
    private Integer sort;
}

