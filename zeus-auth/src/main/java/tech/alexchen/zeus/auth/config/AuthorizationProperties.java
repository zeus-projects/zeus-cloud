package tech.alexchen.zeus.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author alexchen
 */
@Data
@ConfigurationProperties(prefix = AuthorizationProperties.PREFIX)
public class AuthorizationProperties {

    public final static String PREFIX = "security.oauth2";

    private String issuerUrl = "http://zeus-auth:9000";

}
