package tech.alexchen.zeus.common.security.resource.config;

import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tech.alexchen.zeus.common.security.resource.annotation.Inner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author alexchen
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = ResourceServerProperties.PREFIX)
public class ResourceServerProperties implements InitializingBean, Ordered {

    public final static String PREFIX = "spring.security.oauth2.resourceserver";

    private static final String[] DEFAULT_IGNORE_URLS = new String[] { "/actuator/**", "/error", "/v3/api-docs/**", "/swagger-ui/**" };

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private TokenProperties opaqueToken = new TokenProperties();

    private Set<String> permitUrls = new HashSet<>();

    @Override
    public void afterPropertiesSet() {
        // 添加默认不需要鉴权的 url
        permitUrls.addAll(Arrays.asList(DEFAULT_IGNORE_URLS));
        // 获取 @Inner 注解标注的方法请求路径
        RequestMappingHandlerMapping mapping = SpringUtil.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);
            // 获取方法上边的注解 替代path variable 为 *
            Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
            Optional.ofNullable(method)
                    .ifPresent(inner -> Objects.requireNonNull(info.getPathPatternsCondition())
                            .getPatternValues()
                            .forEach(url -> permitUrls.add(ReUtil.replaceAll(url, PATTERN, "*"))));

            // 获取类上边的注解, 替代path variable 为 *
            Inner controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
            Optional.ofNullable(controller)
                    .ifPresent(inner -> Objects.requireNonNull(info.getPathPatternsCondition())
                            .getPatternValues()
                            .forEach(url -> permitUrls.add(ReUtil.replaceAll(url, PATTERN, "*"))));
        });
        log.info("spring security oauth2 resourceserver permitUrls: {}", permitUrls);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Data
    public static class TokenProperties {
        private String clientId = "zeus";
        private String clientSecret = "zeus";
        private String introspectionUri = "http://zeus-auth:9000/oauth2/introspect";
    }
}
