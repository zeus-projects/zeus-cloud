package tech.alexchen.zeus.starter.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLogInfo {
    /**
     * 编号
     */
    private Long id;

    /**
     * 日志类型
     */
    @NotBlank(message = "日志类型不能为空")
    private String logType;

    /**
     * 日志标题
     */
    @NotBlank(message = "日志标题不能为空")
    private String title;

    /**
     * 执行时间
     */
    private Long time;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 租户编号
     */
    private Long tenantId;

    /**
     * 创建时间区间 [开始时间，结束时间]
     */
    private LocalDateTime[] createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
