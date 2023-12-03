package tech.alexchen.zeus.common.data.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 默认字段自动填充设置
 *
 * @author alexchen
 */
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_BY = "admin";
    private static final String UPDATE_BY = "admin";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime current = LocalDateTime.now();
        // 先写死，之后需要从 token 中获取当前操作用户
        this.strictInsertFill(metaObject, "createBy", String.class, CREATE_BY);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, current);
        this.strictInsertFill(metaObject, "updateBy", String.class, UPDATE_BY);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, current);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateBy", String.class, UPDATE_BY);
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
