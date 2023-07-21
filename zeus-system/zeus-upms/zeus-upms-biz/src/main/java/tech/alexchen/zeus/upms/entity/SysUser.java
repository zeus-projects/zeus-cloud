package tech.alexchen.zeus.upms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.alexchen.zeus.common.data.mybatis.entity.TenantBaseDO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author alexchen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends TenantBaseDO implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 拓展字段:昵称
     */
    private String nickname;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 拓展字段:邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 角色
     */
    private List<String> role_ids;

    /**
     * 锁定标记：0(未锁定)、1(已锁定)
     */
    private String lockFlag;

    /**
     * 状态（0：正常 1：停用）
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

}
