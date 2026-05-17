package tech.alexchen.zeus.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tech.alexchen.zeus.common.security.resource.annotation.EnableOauth2ResourceServer;
import tech.alexchen.zeus.common.springdoc.annotation.EnableSpringDoc;

/**
 * @author alexchen
 */
@MapperScan("tech.alexchen.zeus.system.mapper")
@EnableSpringDoc
@EnableDiscoveryClient
@SpringBootApplication
@EnableOauth2ResourceServer
public class ZeusSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusSystemApplication.class, args);
    }

}
