package tech.alexchen.zeus.common.swagger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tech.alexchen.zeus.common.swagger.apiversion.ApiVersionRequestMappingHandlerMapping;

/**
 * @author alexchen
 */
@Configuration
@ConditionalOnProperty(value = "swagger.api-version", matchIfMissing = false)
public class ApiVersionConfiguration implements WebMvcRegistrations {

    @Override
    @NonNull
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }
}
