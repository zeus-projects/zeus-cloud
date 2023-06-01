package tech.alexchen.zeus.starter.swagger.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义的 swagger 配置
 * 开启 knife4j 增强
 *
 * @author alexchen
 */
@Configuration
@EnableOpenApi
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SwaggerConfiguration {

    /**
     * 不生成文档的接口路径
     */
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

    @Bean
    public Docket openApi(Environment environment) {
        // 不在生产中使用
        Profiles profiles = Profiles.of("dev", "test");
        boolean enabled = environment.acceptsProfiles(profiles);

        // openapi 规范 3.0
        Docket docket = new Docket(DocumentationType.OAS_30)
                .enable(enabled)
                .groupName("ZeusBoot")
                .apiInfo(apiInfo())
                // 设置全局请求参数
                .globalRequestParameters(getGlobalRequestParameters());

        // 更细粒度的构造器
        ApiSelectorBuilder selectorBuilder = docket.select();
        // 设置为添加了 @ApiOperation 注解的接口生成文档
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        // 配置不生成文档的路径
        DEFAULT_EXCLUDE_PATH.forEach(path -> selectorBuilder.paths(PathSelectors.ant(path).negate()));
        selectorBuilder.build();

        // 如果需要使用 knife4j 增强的话，需要使用 extensions() 方法
        return docket;
    }

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("用户 token")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("ZeusBoot Api Documentation")
                .description("ZeusBoot Api Documentation")
                .contact(new Contact("AlexChen", "https://blog.alexchen.tech", "alexchen.tech@gmail.com"))
                .version("1.0")
                .build();
    }
}
