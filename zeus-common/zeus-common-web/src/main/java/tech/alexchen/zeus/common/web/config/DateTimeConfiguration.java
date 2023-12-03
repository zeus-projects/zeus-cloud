package tech.alexchen.zeus.common.web.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间格式转换配置
 * <p>
 * 参考 <a href="https://zhuanlan.zhihu.com/p/610989565">Spring Boot2中如何优雅地个性化定制Jackson</a>
 *
 * @author alexchen
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class DateTimeConfiguration {

    /**
     * 时区
     */
    private static final String ASIA_SHANGHAI = "Asia/Shanghai";
    /**
     * 默认日期时间格式
     */
    private static final String DateTimeFormatPattern = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    private static final String DateFormatPattern = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    private static final String TimeFormatPattern = "HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            // 设置java.util.Date时间类的序列化以及反序列化的格式
            builder.simpleDateFormat(DateTimeFormatPattern);
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ASIA_SHANGHAI));

            // JSR 310日期时间处理
            JavaTimeModule module = new JavaTimeModule();
            // LocalDateTime
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateTimeFormatPattern);
//            module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
//            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

            // LocalDate
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateFormatPattern);
            module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
            module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            // LocalTime
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TimeFormatPattern);
            module.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
            module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

            builder.modules(module);

            // 全局转化 Long 类型为String，解决序列化后传入前端 Long 类型精度丢失问题
            builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            builder.serializerByType(Long.class, ToStringSerializer.instance);

            // 设置 LocalDateTime 在序列化时，转换为时间戳
            builder.serializerByType(LocalDateTime.class, new TimestampLocalDateTimeSerializer());
            // 设置 LocalDateTime 在反序列化时，从时间戳转为 LocalDateTime
            builder.deserializerByType(LocalDateTime.class, new TimestampLocalDateTimeDeserializer());
        };
    }

    /**
     * 序列化时间戳
     */
    public static class TimestampLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                long timestamp = LocalDateTimeUtil.toEpochMilli(value);
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * 反序列化时间戳
     */
    public static class TimestampLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
            long timestamp = p.getValueAsLong();
            if (timestamp > 0) {
//                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
                return LocalDateTimeUtil.of(timestamp, ZoneOffset.of("+8"));
            } else {
                throw new IllegalArgumentException("Cannot deserialize value, receive only 13 bit timestamp");
            }
        }
    }

}
