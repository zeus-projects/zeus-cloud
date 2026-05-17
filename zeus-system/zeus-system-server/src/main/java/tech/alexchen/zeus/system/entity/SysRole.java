package tech.alexchen.zeus.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tech.alexchen.zeus.common.data.mybatis.pojo.BaseEntity;

import java.io.Serializable;

/**
 * 角色
 *
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role", autoResultMap = true)
public class SysRole extends BaseEntity implements Serializable {

    /**
     * 角色 id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色权限编码
     */
    private String permission;

    /**
     * 数据权限（0:全部数据权限;1:本部门及子部门数据权限;2:本部门数据权限;3:本人数据权限）
     */
    private Integer dataScope;

    /**
     * 状态（0：正常 1：停用）
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;
}
