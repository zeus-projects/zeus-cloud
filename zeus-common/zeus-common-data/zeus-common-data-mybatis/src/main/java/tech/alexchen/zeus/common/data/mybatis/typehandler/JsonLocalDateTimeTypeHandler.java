package tech.alexchen.zeus.common.data.mybatis.typehandler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author alexchen
 */
public class JsonLocalDateTimeTypeHandler extends AbstractJsonTypeHandler<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public JsonLocalDateTimeTypeHandler(Class<?> type) {
        super(type);
    }

    public JsonLocalDateTimeTypeHandler(Class<?> type, Field field) {
        super(type, field);
    }

    @Override
    public LocalDateTime parse(String str) {
        return LocalDateTime.parse(str, FORMATTER);
    }

    @Override
    public String toJson(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }
}
