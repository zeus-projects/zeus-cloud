package tech.alexchen.zeus.common.data.mybatis.typehandler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author alexchen
 */
public class JsonLocalDateTimeTypeHandler extends AbstractJsonTypeHandler<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected LocalDateTime parse(String str) {
        return LocalDateTime.parse(str, FORMATTER);
    }

    @Override
    protected String toJson(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }
}
