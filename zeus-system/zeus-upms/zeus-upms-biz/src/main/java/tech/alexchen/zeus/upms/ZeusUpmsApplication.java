package tech.alexchen.zeus.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tech.alexchen.zeus.common.security.resource.annotation.EnableOauth2ResourceServer;
import tech.alexchen.zeus.common.springdoc.annotation.EnableSpringDoc;

/**
 * @author alexchen
 */
@MapperScan("tech.alexchen.zeus.upms.mapper")
@EnableSpringDoc
@EnableDiscoveryClient
@SpringBootApplication
@EnableOauth2ResourceServer
public class ZeusUpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusUpmsApplication.class, args);
    }

}
