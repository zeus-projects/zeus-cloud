package tech.alexchen.zeus.upms.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import tech.alexchen.zeus.common.data.mybatis.typehandler.JsonLongSetTypeHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 角色
 *
 * @author alexchen
 */
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role", autoResultMap = true)
public class SysRole implements Serializable {

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
     * 角色类型
     */
    private Integer type;

    /**
     * 角色权限编码
     */
    private String permission;

    /**
     * 数据权限类型（0:全部数据权限；1:自定数据权限；2:本部门数据权限；3:本部门及子部门权限；4:本人）
     */
    private Integer dataScopeType;

    /**
     * 数据范围(指定部门数组)
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> dataScope;


    /**
     * 菜单权限
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> menus;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT, jdbcType = JdbcType.VARCHAR)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.VARCHAR)
    private String updateBy;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}
