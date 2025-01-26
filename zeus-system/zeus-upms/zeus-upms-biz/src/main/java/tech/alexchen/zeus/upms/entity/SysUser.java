package tech.alexchen.zeus.upms.entity;

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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user", autoResultMap = true)
public class SysUser extends BaseEntity implements Serializable {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
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
     * 真实姓名
     */
    private String fullname;

    /**
     * 拓展字段:昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthday;

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
     * 状态（0：正常 1：冻结）
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginDatetime;

}
