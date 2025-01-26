package tech.alexchen.zeus.common.data.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 默认字段自动填充设置
 *
 * @author alexchen
 */
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    private static final String SYSTEM_DEFAULT_USER = "system";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime current = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createBy", String.class, getUserName());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, current);
        this.strictInsertFill(metaObject, "updateBy", String.class, getUserName());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, current);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", String.class, getUserName());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 获取 spring security 当前的用户名
     * @return 当前用户名
     */
    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 匿名接口直接返回
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        if (Optional.ofNullable(authentication).isPresent()) {
            return authentication.getName();
        }
        return null;
    }
}
