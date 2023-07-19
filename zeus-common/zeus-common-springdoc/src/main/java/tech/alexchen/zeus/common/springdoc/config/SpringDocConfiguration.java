package tech.alexchen.zeus.common.springdoc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * SpringDoc
 *
 * @author alexchen
 */
@AutoConfiguration
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SpringDocConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "springdoc.api-docs.info")
    public Info springDocInfo() {
        return new Info();
    }

    @Bean
    public OpenAPI openAPI(Info apiInfo) {
        return new OpenAPI().info(apiInfo);
    }

}
